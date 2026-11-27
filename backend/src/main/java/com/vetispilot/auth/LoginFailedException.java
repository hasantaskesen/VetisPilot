package com.vetispilot.auth;

/** A login was refused. The message for the user never says whether the email or the password was wrong (US-01). */
public class LoginFailedException extends RuntimeException {

    public enum Reason { WRONG_CREDENTIALS, LOCKED }

    private final Reason reason;

    public LoginFailedException(Reason reason) {
        super(reason == Reason.LOCKED
                ? "Too many failed attempts. Please try again in 15 minutes."
                : "Email or password is incorrect.");
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
