package ecureuill.medapi.infra.error;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
