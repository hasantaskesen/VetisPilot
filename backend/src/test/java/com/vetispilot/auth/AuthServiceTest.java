package com.vetispilot.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vetispilot.audit.AuditLogger;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The login rules without a database: users come from a mock, the clock is fixed.
 * US-01 (same message for every failure), US-02 (lock after 5 wrong passwords), US-22 (deactivated accounts).
 */
class AuthServiceTest {

    private static final Instant NOW = Instant.parse("2026-11-27T10:00:00Z");
    private static final String SECRET = "test-secret-that-is-long-enough-for-hmac-256";

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);   // fast for tests
    private final AppUserRepository users = mock(AppUserRepository.class);
    private final AuditLogger audit = mock(AuditLogger.class);
    private final SecurityProperties props = new SecurityProperties(SECRET, 8, 5, 15);
    private AuthService service;
    private AppUser nadia;

    @BeforeEach
    void setUp() {
        service = new AuthService(users, encoder, new JwtService(props), audit, props, Clock.fixed(NOW, ZoneOffset.UTC));
        nadia = new AppUser(1L, "nadia@x.example", encoder.encode("Nadia#2026"), "Nadia Brenner",
                AppUser.Role.BRANCH_EMPLOYEE, 1L, 1L, "tr", true);
        when(users.findByEmailIgnoreCase(any())).thenReturn(Optional.empty());
        when(users.findByEmailIgnoreCase("nadia@x.example")).thenReturn(Optional.of(nadia));
    }

    @Test
    void rightPasswordGivesTokenForEightHours() {
        var result = service.login("nadia@x.example", "Nadia#2026", "127.0.0.1");

        assertThat(result.token().value()).isNotBlank();
        assertThat(result.token().expiresAt()).isEqualTo(NOW.plusSeconds(8 * 3600));
        assertThat(nadia.getLastLoginAt()).isEqualTo(NOW);
        verify(audit).log(1L, "LOGIN_SUCCESS", "USER", 1L, "127.0.0.1");
    }

    @Test
    void emailIsNotCaseSensitiveAndSpacesAreIgnored() {
        when(users.findByEmailIgnoreCase("NADIA@x.example")).thenReturn(Optional.of(nadia));
        assertThat(service.login(" NADIA@x.example ", "Nadia#2026", "ip").user()).isSameAs(nadia);
    }

    @Test
    void unknownEmailAndWrongPasswordGiveTheSameMessage() {
        String unknown = messageOf(() -> service.login("nobody@x.example", "x", "ip"));
        String wrong = messageOf(() -> service.login("nadia@x.example", "wrong", "ip"));

        assertThat(unknown).isEqualTo(wrong).isEqualTo("Email or password is incorrect.");
        verify(audit).log(isNull(), eq("LOGIN_FAILED"), eq("USER"), isNull(), eq("ip"));
    }

    @Test
    void fifthWrongPasswordLocksTheAccountForFifteenMinutes() {
        for (int i = 1; i <= 4; i++) {
            assertReason("wrong", LoginFailedException.Reason.WRONG_CREDENTIALS);
        }
        assertReason("wrong", LoginFailedException.Reason.LOCKED);

        assertThat(nadia.getLockedUntil()).isEqualTo(NOW.plusSeconds(15 * 60));
        verify(audit).log(1L, "ACCOUNT_LOCKED", "USER", 1L, "ip");
    }

    @Test
    void lockedAccountRefusesEvenTheRightPassword() {
        for (int i = 1; i <= 5; i++) {
            messageOf(() -> service.login("nadia@x.example", "wrong", "ip"));
        }
        assertReason("Nadia#2026", LoginFailedException.Reason.LOCKED);
    }

    @Test
    void afterTheLockTimeTheRightPasswordWorksAgain() {
        for (int i = 1; i <= 5; i++) {
            messageOf(() -> service.login("nadia@x.example", "wrong", "ip"));
        }
        AuthService later = new AuthService(users, encoder, new JwtService(props), audit, props,
                Clock.fixed(NOW.plusSeconds(15 * 60 + 1), ZoneOffset.UTC));

        assertThat(later.login("nadia@x.example", "Nadia#2026", "ip").user()).isSameAs(nadia);
        assertThat(nadia.getLockedUntil()).isNull();
        assertThat(nadia.getFailedLoginCount()).isZero();
    }

    @Test
    void successResetsTheCounterOfWrongPasswords() {
        for (int i = 1; i <= 4; i++) {
            messageOf(() -> service.login("nadia@x.example", "wrong", "ip"));
        }
        service.login("nadia@x.example", "Nadia#2026", "ip");
        assertThat(nadia.getFailedLoginCount()).isZero();
    }

    @Test
    void deactivatedAccountCannotLogIn() {
        AppUser former = new AppUser(2L, "former@x.example", encoder.encode("pw"), "Former", AppUser.Role.BRANCH_EMPLOYEE,
                1L, 1L, "de", false);
        when(users.findByEmailIgnoreCase("former@x.example")).thenReturn(Optional.of(former));

        assertThatThrownBy(() -> service.login("former@x.example", "pw", "ip"))
                .isInstanceOf(LoginFailedException.class)
                .hasMessage("Email or password is incorrect.");
    }

    private void assertReason(String password, LoginFailedException.Reason reason) {
        assertThatThrownBy(() -> service.login("nadia@x.example", password, "ip"))
                .isInstanceOfSatisfying(LoginFailedException.class, e -> assertThat(e.getReason()).isEqualTo(reason));
    }

    private static String messageOf(Runnable login) {
        try {
            login.run();
            return null;
        } catch (LoginFailedException e) {
            return e.getMessage();
        }
    }
}
