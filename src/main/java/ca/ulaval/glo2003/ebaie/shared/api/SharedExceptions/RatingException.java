package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class RatingException  extends RuntimeException{
    public RatingException(String message) {
        super(message);
    }

    public String getCode() {
        return "INVALID_RATING";
    }
}

