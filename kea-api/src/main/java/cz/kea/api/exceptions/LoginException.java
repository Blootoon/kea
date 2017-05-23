package cz.kea.api.exceptions;

import java.time.LocalDateTime;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public class LoginException extends Exception {

    private boolean locked;
    private LocalDateTime lockReleaseTime;

    public LoginException() {
        this.locked = false;
    }

    public LoginException(boolean locked, LocalDateTime lockReleaseTime) {
        this.locked = locked;
        this.lockReleaseTime = lockReleaseTime;
    }

    public LoginException(String message, boolean locked, LocalDateTime lockReleaseTime) {
        super(message);
        this.locked = locked;
        this.lockReleaseTime = lockReleaseTime;
    }

    public LoginException(String message, Throwable cause, boolean locked, LocalDateTime lockReleaseTime) {
        super(message, cause);
        this.locked = locked;
        this.lockReleaseTime = lockReleaseTime;
    }

    public LoginException(Throwable cause, boolean locked, LocalDateTime lockReleaseTime) {
        super(cause);
        this.locked = locked;
        this.lockReleaseTime = lockReleaseTime;
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, boolean locked, LocalDateTime lockReleaseTime) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.locked = locked;
        this.lockReleaseTime = lockReleaseTime;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getLockReleaseTime() {
        return lockReleaseTime;
    }

    public void setLockReleaseTime(LocalDateTime lockReleaseTime) {
        this.lockReleaseTime = lockReleaseTime;
    }

    @Override
    public String toString() {
        return "LoginException{" +
            "locked=" + locked +
            ", lockReleaseTime=" + lockReleaseTime +
            "} " + super.toString();
    }
}
