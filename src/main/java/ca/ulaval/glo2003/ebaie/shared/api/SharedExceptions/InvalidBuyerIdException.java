package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class InvalidBuyerIdException extends RuntimeException {
    public InvalidBuyerIdException(String message) {
        super(message);
    }

    public String getCode() {
        return "BUYER_NOT_FOUND";
    }
}
