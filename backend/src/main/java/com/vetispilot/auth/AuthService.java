package com.vetispilot.auth;

import com.vetispilot.audit.AuditLogger;
import java.time.Clock;
import java.time.Instant;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The login rules (US-01, US-02, US-22):
 * <ol>
 *   <li>same answer for "unknown email" and "wrong password", so nobody can find out which accounts exist</li>
 *   <li>a deactivated account cannot log in</li>
 *   <li>the 5th wrong password in a row locks the account for 15 minutes; also the right password is refused then</li>
 *   <li>every success, failure and lock is written to the audit log</li>
 * </ol>
 */
@Service
public class AuthService {

    /** A valid BCrypt hash of a random text: checked for unknown emails so the answer takes as long as for real ones. */
    private static final String DUMMY_HASH = "$2a$10$1bsoMuPosW.sd55hbvOh1.rxD3UpfakZ7tnBflAsVe.5P6HkNecM2";

    private final AppUserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final AuditLogger audit;
    private final SecurityProperties props;
    private final Clock clock;

    public AuthService(AppUserRepository users, PasswordEncoder encoder, JwtService jwt,
                       AuditLogger audit, SecurityProperties props, Clock clock) {
        this.users = users;
        this.encoder = encoder;
        this.jwt = jwt;
        this.audit = audit;
        this.props = props;
        this.clock = clock;
    }

    public record LoginResult(JwtService.Token token, AppUser user) { }

    @Transactional(noRollbackFor = LoginFailedException.class)   // failed counts and audit rows must be saved
    public LoginResult login(String email, String password, String ip) {
        Instant now = clock.instant();
        AppUser user = users.findByEmailIgnoreCase(email.trim()).orElse(null);

        if (user == null || !user.isActive()) {
            encoder.matches(password, DUMMY_HASH);                // same timing as a real check
            audit.log(user == null ? null : user.getId(), "LOGIN_FAILED", "USER", user == null ? null : user.getId(), ip);
            throw new LoginFailedException(LoginFailedException.Reason.WRONG_CREDENTIALS);
        }
        if (user.isLockedAt(now)) {
            audit.log(user.getId(), "LOGIN_WHILE_LOCKED", "USER", user.getId(), ip);
            throw new LoginFailedException(LoginFailedException.Reason.LOCKED);
        }
        if (!encoder.matches(password, user.getPasswordHash())) {
            boolean locked = user.registerFailedLogin(now, props.maxFailedLogins(), props.lockMinutes());
            audit.log(user.getId(), locked ? "ACCOUNT_LOCKED" : "LOGIN_FAILED", "USER", user.getId(), ip);
            throw new LoginFailedException(locked ? LoginFailedException.Reason.LOCKED
                                                  : LoginFailedException.Reason.WRONG_CREDENTIALS);
        }

        user.registerSuccessfulLogin(now);
        audit.log(user.getId(), "LOGIN_SUCCESS", "USER", user.getId(), ip);
        return new LoginResult(jwt.create(user, now), user);
    }
}
