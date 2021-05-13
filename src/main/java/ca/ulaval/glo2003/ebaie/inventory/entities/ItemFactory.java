package ca.ulaval.glo2003.ebaie.inventory.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;
import java.util.UUID;

public class ItemFactory {

    public ItemFactory() {
    }

    static UUID generateUUID() {

        return UUID.randomUUID();
    }

    public Item create(SellerId sellerId, String name, String description, PriceAmount initialPrice,
                       PriceAmount currentPrice,
                       Instant startTime, long duration) {

        UUID id = generateUUID();
        ItemId idString = new ItemId(id.toString());

        return new Item(idString, sellerId, name, description, initialPrice, currentPrice, startTime,
                duration);

    }
}

