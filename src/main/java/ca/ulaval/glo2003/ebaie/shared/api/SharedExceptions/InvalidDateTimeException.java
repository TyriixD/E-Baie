package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class InvalidDateTimeException extends RuntimeException {
    public InvalidDateTimeException(String message) {
        super(message);
    }

    public String getCode() {
        return "INVALID_DATETIME";
    }
}
