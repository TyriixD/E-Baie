package ca.ulaval.glo2003.ebaie.seller.api.SellerException;

public class InvalidSellerIdException extends RuntimeException {
    public InvalidSellerIdException(String message) {
        super(message);
    }

    public String getCode() {
        return "SELLER_NOT_FOUND";
    }
}
