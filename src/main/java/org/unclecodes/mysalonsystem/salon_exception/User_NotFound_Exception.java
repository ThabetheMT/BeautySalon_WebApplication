package org.unclecodes.mysalonsystem.salon_exception;

public class User_NotFound_Exception extends RuntimeException{

    public User_NotFound_Exception() {
    }

    public User_NotFound_Exception(String message) {
        super(message);
    }

    public User_NotFound_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public User_NotFound_Exception(Throwable cause) {
        super(cause);
    }

    public User_NotFound_Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
