package ca.ulaval.glo2003.ebaie.review.entities;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidReviewIdException;

import java.util.List;

public interface ReviewRepository {
    List<Review> findAll();

    Review findById(String id) throws InvalidReviewIdException;

    void save(Review review);
    List<Review> findSellerReviews(String id);
}
