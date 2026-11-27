package com.vetispilot.audit;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Writes rows into {@code audit_log} (rule R4). The table only accepts INSERT – a database trigger
 * refuses UPDATE and DELETE, so nobody can hide what happened.
 */
@Component
public class AuditLogger {

    private final JdbcTemplate jdbc;

    public AuditLogger(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * @param userId     who did it (NULL for an unknown email)
     * @param action     e.g. LOGIN_SUCCESS, LOGIN_FAILED, ACCOUNT_LOCKED
     * @param entityType e.g. USER
     * @param entityId   the row it is about
     * @param ip         the caller's IP address
     */
    public void log(Long userId, String action, String entityType, Long entityId, String ip) {
        jdbc.update("INSERT INTO audit_log (user_id, action, entity_type, entity_id, ip_address) VALUES (?, ?, ?, ?, ?)",
                userId, action, entityType, entityId, ip);
    }
}
