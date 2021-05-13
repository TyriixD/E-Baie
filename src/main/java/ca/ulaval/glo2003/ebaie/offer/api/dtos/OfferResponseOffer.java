package ca.ulaval.glo2003.ebaie.offer.api.dtos;

import java.time.Instant;

public class OfferResponseOffer {
    public String id;
    public String productId;
    public String buyerId;
    public double amount;
    public Instant createdAt;
    public String biddingStatus;
}
