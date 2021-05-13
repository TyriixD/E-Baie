package ca.ulaval.glo2003.ebaie.seller.application.dto;

import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;

public class SellerDto {
    public String name;
    public String description;
    public SellerId id;
    public Instant createdAt;
}
