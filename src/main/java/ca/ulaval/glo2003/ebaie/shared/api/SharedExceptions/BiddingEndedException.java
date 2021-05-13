package ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions;

public class BiddingEndedException extends RuntimeException {
    public BiddingEndedException(String message) {
        super(message);
    }

    public String getCode() {
        return "BIDDING_ENDED";
    }
}
