package com.vetispilot.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * One row of the {@code users} table (created by Flyway V1/V2).
 * Only the columns the login needs are mapped; Hibernate checks them against the table at start-up.
 */
@Entity
@Table(name = "users")
public class AppUser {

    public enum Role { ADMIN, SELLER, BRANCH_EMPLOYEE }

    @Id
    private Long id;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "branch_id")
    private Long branchId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 2)
    private String language;

    @Column(name = "must_change_password")
    private boolean mustChangePassword;

    @Column(name = "failed_login_count")
    private int failedLoginCount;

    @Column(name = "locked_until")
    private Instant lockedUntil;

    private boolean active;

    @Column(name = "last_login_at")
    private Instant lastLoginAt;

    protected AppUser() {
        // for JPA
    }

    /** Used by tests to build a user without a database. */
    public AppUser(Long id, String email, String passwordHash, String fullName, Role role,
                   Long sellerId, Long branchId, String language, boolean active) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
        this.sellerId = sellerId;
        this.branchId = branchId;
        this.language = language;
        this.active = active;
    }

    /** US-02: is the account locked at this moment? */
    public boolean isLockedAt(Instant now) {
        return lockedUntil != null && lockedUntil.isAfter(now);
    }

    /** US-02: count a wrong password; the n-th one in a row locks the account. Returns true if it is now locked. */
    public boolean registerFailedLogin(Instant now, int maxFailed, int lockMinutes) {
        failedLoginCount++;
        if (failedLoginCount >= maxFailed) {
            lockedUntil = now.plusSeconds(lockMinutes * 60L);
            failedLoginCount = 0;
            return true;
        }
        return false;
    }

    public void registerSuccessfulLogin(Instant now) {
        failedLoginCount = 0;
        lockedUntil = null;
        lastLoginAt = now;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }
    public Role getRole() { return role; }
    public Long getSellerId() { return sellerId; }
    public Long getBranchId() { return branchId; }
    public String getLanguage() { return language == null ? null : language.trim(); }
    public boolean isMustChangePassword() { return mustChangePassword; }
    public int getFailedLoginCount() { return failedLoginCount; }
    public Instant getLockedUntil() { return lockedUntil; }
    public boolean isActive() { return active; }
    public Instant getLastLoginAt() { return lastLoginAt; }
}
