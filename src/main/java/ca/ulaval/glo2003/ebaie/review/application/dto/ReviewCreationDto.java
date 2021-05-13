package ca.ulaval.glo2003.ebaie.review.application.dto;

import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;
import java.util.List;

public class ReviewCreationDto {
    public SellerId sellerId;
    public String title;
    public String description;
    public float rating;
    public Instant createdAt;
}
