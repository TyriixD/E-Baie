package ca.ulaval.glo2003.ebaie.seller.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;
import java.util.UUID;

public class SellerFactory {

    public SellerFactory() {
    }

    static UUID generateUUID() {

        return UUID.randomUUID();
    }

    public Seller create(String name, String description, Instant createdAt) {
        UUID id = generateUUID();
        SellerId idString = new SellerId(id.toString());

        return new Seller(name, description, idString, createdAt);
    }
}