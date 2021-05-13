package ca.ulaval.glo2003.ebaie.review.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.ReviewId;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;
import java.util.List;

public class Review {
    private final ReviewId reviewId;
    public SellerId sellerId;
    private final String title;
    private final String description;
    private final float rating;
    private final Instant createdAt;

    public Review(ReviewId reviewId, SellerId sellerId, String title,
                  String description, float rating, Instant startTime) {
        this.reviewId = reviewId;
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.createdAt = startTime;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public ReviewId getReviewId() {
        return reviewId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Instant getcreatedAt() {
        return createdAt;
    }

    public float getRating() {
        return rating;
    }
}
