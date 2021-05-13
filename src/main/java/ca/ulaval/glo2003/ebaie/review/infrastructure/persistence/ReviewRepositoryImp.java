package ca.ulaval.glo2003.ebaie.review.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.review.entities.Review;
import ca.ulaval.glo2003.ebaie.review.entities.ReviewRepository;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidReviewIdException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewRepositoryImp implements ReviewRepository {
    private Map<String, Review> reviewMap = new HashMap<>();

    @Override
    public Review findById(String id) throws InvalidReviewIdException {
        for (Review review : reviewMap.values()){
            if(review.getReviewId().getValue().equals(id)){
                return review;
            }
        }
        throw new InvalidReviewIdException("L'id du review n'existe pas");
    }

    @Override
    public void save(Review review) {
        reviewMap.put(review.getReviewId().getValue(), review);
    }

    @Override
    public List<Review> findAll(){
        return new ArrayList<>(reviewMap.values());
    }

    @Override
    public List<Review> findSellerReviews(String id){
        List<Review> liseSellerReviews = new ArrayList<>();
        for (Review review : reviewMap.values()){
            if(review.getSellerId().getValue().equals(id)){
               liseSellerReviews.add(review);
            }
        }
        return liseSellerReviews;
    }

    public void setReviewMap(Map<String, Review> reviewMap){
        this.reviewMap = reviewMap;
    }
}
