package ca.ulaval.glo2003.ebaie.inventory.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;

public class Item {
    private final ItemId id;
    public SellerId sellerId;
    private final String name;
    private final String description;
    private final PriceAmount initialPrice;
    private PriceAmount currentPrice;
    private final Instant startTime;
    private final long duration;


    public Item(ItemId id, SellerId sellerId, String name, String description, PriceAmount initialPrice, PriceAmount currentPrice,
                Instant startTime, long duration) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.startTime = startTime;
        this.duration = duration;
    }

    public ItemId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SellerId getSellerId() {
        return sellerId;
    }


    public String getDescription() {
        return description;
    }

    public PriceAmount getInitialPrice() {
        return initialPrice;
    }

    public PriceAmount getCurrentPrice() {
        return currentPrice;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setCurrentPrice(PriceAmount currentPrice) {
        this.currentPrice = currentPrice;
    }
}

