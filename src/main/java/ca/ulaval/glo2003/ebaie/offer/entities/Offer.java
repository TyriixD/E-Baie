package ca.ulaval.glo2003.ebaie.offer.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.BuyerId;
import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;
import ca.ulaval.glo2003.ebaie.valueobjects.OfferId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;

import java.time.Instant;

public class Offer {
    private final OfferId id;
    private final ItemId productId;
    private final BuyerId buyerId;
    private final PriceAmount amount;
    private final Instant createdAt;
    private final String biddingStatus;

    public Offer(OfferId id, ItemId productId, BuyerId buyerId, PriceAmount amount, Instant createdAt, String biddingStatus) {
        this.id = id;
        this.productId = productId;
        this.buyerId = buyerId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.biddingStatus = biddingStatus;
    }

    public OfferId getId() {
        return id;
    }

    public ItemId getProductId() {
        return productId;
    }

    public BuyerId getBuyerId() {
        return buyerId;
    }

    public PriceAmount getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getBiddingStatus() {
        return biddingStatus;
    }
}
