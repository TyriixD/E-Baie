package ca.ulaval.glo2003.ebaie.offer.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.BuyerId;
import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;
import ca.ulaval.glo2003.ebaie.valueobjects.OfferId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;

import java.time.Instant;
import java.util.UUID;

public class OfferFactory {

    public OfferFactory() {
    }

    static UUID generateUUID() {
        return UUID.randomUUID();
    }

    public Offer create(ItemId productId, BuyerId buyerId, PriceAmount amount, Instant createdAt, String biddingStatus) {
        UUID id = generateUUID();
        OfferId idString = new OfferId(id.toString());

        return new Offer(idString, productId, buyerId, amount, createdAt, biddingStatus);
    }
}