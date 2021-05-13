package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }

    public String getCode() {
        return "INVALID_AMOUNT";
    }
}
