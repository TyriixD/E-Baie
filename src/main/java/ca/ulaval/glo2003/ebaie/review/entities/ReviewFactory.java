package ca.ulaval.glo2003.ebaie.review.entities;

import ca.ulaval.glo2003.ebaie.valueobjects.ReviewId;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class ReviewFactory {
    public ReviewFactory(){

    }

    static UUID generateUUID() {
        return UUID.randomUUID();
    }

    public Review create(SellerId sellerId, String title, String description ,
                         float rating, Instant createdAt){
        UUID id = generateUUID();
        ReviewId idString = new ReviewId(id.toString());

        return new Review(idString, sellerId, title, description,rating, createdAt);
    }


}
