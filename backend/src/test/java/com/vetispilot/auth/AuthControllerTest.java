package com.vetispilot.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vetispilot.common.ApiErrorHandler;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/** The web layer of the login: JSON in and out, status codes, and that /me needs a valid token. */
@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, JwtService.class, ApiErrorHandler.class})
@TestPropertySource(properties = {
        "vetispilot.security.jwt-secret=test-secret-that-is-long-enough-for-hmac-256",
        "vetispilot.security.token-hours=8",
        "vetispilot.security.max-failed-logins=5",
        "vetispilot.security.lock-minutes=15"})
class AuthControllerTest {

    private static final AppUser NADIA = new AppUser(1L, "nadia@x.example", "hash", "Nadia Brenner",
            AppUser.Role.BRANCH_EMPLOYEE, 1L, 1L, "tr", true);

    @Autowired private MockMvc mvc;
    @Autowired private JwtService jwt;
    @MockitoBean private AuthService auth;
    @MockitoBean private AppUserRepository users;

    @Test
    void loginReturnsTokenAndUser() throws Exception {
        when(auth.login(any(), any(), any())).thenReturn(new AuthService.LoginResult(
                new JwtService.Token("abc", Instant.parse("2026-11-27T18:00:00Z")), NADIA));

        mvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"nadia@x.example\",\"password\":\"Nadia#2026\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("abc"))
                .andExpect(jsonPath("$.user.fullName").value("Nadia Brenner"))
                .andExpect(jsonPath("$.user.role").value("BRANCH_EMPLOYEE"))
                .andExpect(jsonPath("$.user.passwordHash").doesNotExist());
    }

    @Test
    void wrongPasswordGives401WithNeutralMessage() throws Exception {
        when(auth.login(any(), any(), any()))
                .thenThrow(new LoginFailedException(LoginFailedException.Reason.WRONG_CREDENTIALS));

        mvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"nadia@x.example\",\"password\":\"wrong\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Email or password is incorrect."));
    }

    @Test
    void lockedAccountGives429() throws Exception {
        when(auth.login(any(), any(), any())).thenThrow(new LoginFailedException(LoginFailedException.Reason.LOCKED));

        mvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"nadia@x.example\",\"password\":\"x\"}"))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.error").value("LOCKED"));
    }

    @Test
    void emptyFieldsGive400() throws Exception {
        mvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"\",\"password\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("INVALID_INPUT"));
    }

    @Test
    void meWithoutTokenGives401() throws Exception {
        mvc.perform(get("/api/v1/auth/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void meWithBrokenTokenGives401() throws Exception {
        mvc.perform(get("/api/v1/auth/me").header("Authorization", "Bearer not.a.token"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void meWithValidTokenReturnsTheUser() throws Exception {
        when(users.findById(1L)).thenReturn(Optional.of(NADIA));
        String token = jwt.create(NADIA, Instant.now()).value();

        mvc.perform(get("/api/v1/auth/me").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("nadia@x.example"));
    }
}
