package ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Items")
public class ItemDtoMongoDB {
    public String itemId;
    public String sellerId;
    public String name;
    public String description;
    public String initialPrice;
    public String currentPrice;
    public String startTime;
    public String duration;
    @Id String mongoId;
}
