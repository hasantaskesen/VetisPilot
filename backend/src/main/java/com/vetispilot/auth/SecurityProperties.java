package com.vetispilot.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Settings under {@code vetispilot.security} in application.yml.
 *
 * @param jwtSecret       key that signs the tokens (from .env, at least 32 characters)
 * @param tokenHours      how long a login stays valid (US-01: 8 hours)
 * @param maxFailedLogins wrong passwords in a row that lock the account (US-02: 5)
 * @param lockMinutes     how long the account stays locked (US-02: 15)
 */
@ConfigurationProperties("vetispilot.security")
public record SecurityProperties(String jwtSecret, int tokenHours, int maxFailedLogins, int lockMinutes) {

    public SecurityProperties {
        if (jwtSecret == null || jwtSecret.length() < 32) {
            throw new IllegalStateException("JWT_SECRET must be set in .env and be at least 32 characters long");
        }
    }
}
