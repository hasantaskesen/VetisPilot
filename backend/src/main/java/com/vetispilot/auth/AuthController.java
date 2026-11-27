package com.vetispilot.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * POST /api/v1/auth/login – email + password in, token + user out (US-01)
 * GET  /api/v1/auth/me    – who am I? (needs the token in the header "Authorization: Bearer ...")
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService auth;
    private final AppUserRepository users;

    public AuthController(AuthService auth, AppUserRepository users) {
        this.auth = auth;
        this.users = users;
    }

    public record LoginRequest(@NotBlank String email, @NotBlank String password) { }

    public record UserView(long id, String email, String fullName, String role, Long branchId,
                           String language, boolean mustChangePassword) {
        static UserView of(AppUser u) {
            return new UserView(u.getId(), u.getEmail(), u.getFullName(), u.getRole().name(), u.getBranchId(),
                    u.getLanguage(), u.isMustChangePassword());
        }
    }

    public record LoginResponse(String token, Instant expiresAt, UserView user) { }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest body, HttpServletRequest request) {
        AuthService.LoginResult r = auth.login(body.email(), body.password(), request.getRemoteAddr());
        return new LoginResponse(r.token().value(), r.token().expiresAt(), UserView.of(r.user()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserView> me(@AuthenticationPrincipal JwtService.TokenUser who) {
        return users.findById(who.userId())
                .filter(AppUser::isActive)
                .map(u -> ResponseEntity.ok(UserView.of(u)))
                .orElse(ResponseEntity.status(401).build());
    }
}
