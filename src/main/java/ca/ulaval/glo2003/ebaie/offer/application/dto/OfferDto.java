package ca.ulaval.glo2003.ebaie.offer.application.dto;

import ca.ulaval.glo2003.ebaie.valueobjects.BuyerId;
import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;
import ca.ulaval.glo2003.ebaie.valueobjects.OfferId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;

import java.time.Instant;

public class OfferDto {
    public OfferId id;
    public ItemId productId;
    public BuyerId buyerId;
    public PriceAmount amount;
    public Instant createdAt;
    public String biddingStatus;
}
