package ca.ulaval.glo2003.ebaie.seller.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;

public class Seller {
    private String name;
    private String description;
    private SellerId id;
    private Instant createdAt;


    public Seller(String name, String description, SellerId id, Instant createdAt) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(SellerId id) {
        this.id = id;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public SellerId getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
