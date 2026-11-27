package com.vetispilot.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

/**
 * Creates and checks login tokens (JSON Web Tokens).
 * A token is like a signed visitor badge: it says who you are and until when it is valid.
 * Nobody can change it without the secret key, so the server does not need to remember sessions.
 */
@Service
public class JwtService {

    private final SecretKey key;
    private final long validSeconds;

    public JwtService(SecurityProperties props) {
        this.key = Keys.hmacShaKeyFor(props.jwtSecret().getBytes(StandardCharsets.UTF_8));
        this.validSeconds = props.tokenHours() * 3600L;
    }

    public record Token(String value, Instant expiresAt) { }

    /** What the server reads back from a valid token. */
    public record TokenUser(long userId, String role, Long branchId) { }

    public Token create(AppUser user, Instant now) {
        Instant expires = now.plusSeconds(validSeconds);
        String value = Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("role", user.getRole().name())
                .claim("branchId", user.getBranchId())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expires))
                .signWith(key)
                .compact();
        return new Token(value, expires);
    }

    /** Empty if the token is missing, changed, signed with another key or expired. */
    public Optional<TokenUser> read(String token) {
        try {
            Claims c = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            Number branch = c.get("branchId", Number.class);
            return Optional.of(new TokenUser(Long.parseLong(c.getSubject()), c.get("role", String.class),
                    branch == null ? null : branch.longValue()));
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
