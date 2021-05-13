package ca.ulaval.glo2003.ebaie.inventory.api.dtos;

import java.time.Instant;

public class ItemResponseItem {
    public String id;
    public String name;
    public String sellerId;
    public String description;
    public double initialPrice;
    public double currentPrice;
    public Instant startTime;
    public String endTime;
}
