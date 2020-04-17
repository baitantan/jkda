package exception;

public class ServerErrorException extends Exception {
    public ServerErrorException() {
    }

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerErrorException(Throwable cause) {
        super(cause);
    }

    public ServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
