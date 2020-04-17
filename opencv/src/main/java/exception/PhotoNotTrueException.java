package exception;

public class PhotoNotTrueException extends Exception {
    public PhotoNotTrueException(String message) {
        super(message);
    }

    public PhotoNotTrueException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoNotTrueException(Throwable cause) {
        super(cause);
    }

    public PhotoNotTrueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PhotoNotTrueException() {
    }
}
