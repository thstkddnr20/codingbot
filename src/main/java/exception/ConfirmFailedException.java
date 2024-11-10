package exception;

public class ConfirmFailedException extends RuntimeException {

    private String message;

    public ConfirmFailedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
