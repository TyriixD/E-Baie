package ca.ulaval.glo2003.ebaie.inventory.application.dtos;

import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;

public class ItemDto {
    public ItemId id;
    public SellerId sellerId;
    public String name;
    public String description;
    public PriceAmount initialPrice;
    public PriceAmount currentPrice;
    public Instant startTime;
    public long duration;
}
