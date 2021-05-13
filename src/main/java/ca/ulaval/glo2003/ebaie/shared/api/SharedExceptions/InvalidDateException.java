package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
        super(message);
    }

    public String getCode() {
        return "INVALID_DATE";
    }

}
