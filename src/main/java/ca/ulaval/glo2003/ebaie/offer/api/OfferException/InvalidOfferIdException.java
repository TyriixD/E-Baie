package ca.ulaval.glo2003.ebaie.offer.api.OfferException;


public class InvalidOfferIdException extends RuntimeException {
    public InvalidOfferIdException(String message) {
        super(message);
    }

    public String getCode() {
        return "OFFER_NOT_FOUND";
    }
}
