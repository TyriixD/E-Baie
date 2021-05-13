package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class InvalidReviewIdException extends RatingException {
    public InvalidReviewIdException(String message) {
        super(message);
    }

    public String getCode() {
        return "Review_NOT_FOUND";
    }
}