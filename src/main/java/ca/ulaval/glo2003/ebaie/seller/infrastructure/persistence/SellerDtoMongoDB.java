package ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.time.Instant;

@Entity("Sellers")
public class SellerDtoMongoDB {
    public String sellerId;
    public String name;
    public String description;
    public String createdAt;
    @Id String mongoId;
}
