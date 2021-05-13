package ca.ulaval.glo2003.ebaie.review.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.review.infrastructure.persistence.ReviewRepositoryImp;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidReviewIdException;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class ReviewRepositoryTest {

    public ReviewRepositoryImp reviewRepositoryImp = new ReviewRepositoryImp();

    @Test
    public void givenReviewToAdd_whenCallingSaveMethod_returnRightReviewInMemory() {
        Review review = new Review
                (Constant.REVIEW_ID, Constant.MY_SELLER_ID, Constant.REVIEW_TITLE, Constant.REVIEW_DESCRIPTION,
                        Constant.REVIEW_RATING, Constant.CREATED_AT);

        reviewRepositoryImp.save(review);

        assertThat(reviewRepositoryImp.findAll().size()).isEqualTo(1);
        assertThat(reviewRepositoryImp.findById(Constant.REVIEW_ID.getValue())).isEqualTo(review);
    }


    @Test(expected = InvalidReviewIdException.class)
    public void givenANonExistingId_WhenCallingFindById_ThrowAnException() {
        Map<String, Review> reviewHashMap = fillReviewHashMap();
        reviewRepositoryImp.setReviewMap(reviewHashMap);

        reviewRepositoryImp.findById(Constant.INVALID_REVIEW_ID.getValue());
    }

    @Test
    public void givenAReviewHashMap_WhenCallingFindAllMethod_ReturnAllTheReviews() {
        Map<String, Review> reviewHashMap = fillReviewHashMap();
        reviewRepositoryImp.setReviewMap(reviewHashMap);

        List<Review> reviewList = reviewRepositoryImp.findAll();

        assertThat(reviewList.get(0)).isEqualTo(reviewHashMap.get("1"));
        assertThat(reviewList.get(1)).isEqualTo(reviewHashMap.get("2"));
        assertThat(reviewList.size()).isEqualTo(2);

    }

    @Test
    public void givenAReviewHashMap_WhenCallingFindById_ReturnTheReview() {
        Map<String, Review> reviewHashMap = fillReviewHashMap();
        reviewRepositoryImp.setReviewMap(reviewHashMap);

        Review review = reviewRepositoryImp.findById(Constant.REVIEW_ID.getValue());

        assertThat(review.getTitle()).isEqualTo(Constant.REVIEW_TITLE);
        assertThat(review.getReviewId()).isEqualTo(Constant.REVIEW_ID);
        assertThat(review.getDescription()).isEqualTo(Constant.REVIEW_DESCRIPTION);
        assertThat(review.getcreatedAt()).isEqualTo(Constant.CREATED_AT);
        assertThat(review.getRating()).isEqualTo(Constant.REVIEW_RATING);

    }


    private Map<String, Review> fillReviewHashMap() {
        Map<String, Review> reviewHashMap = new HashMap<>();

        Review review = new Review
                (Constant.REVIEW_ID, Constant.MY_SELLER_ID, Constant.REVIEW_TITLE, Constant.REVIEW_DESCRIPTION,
                        Constant.REVIEW_RATING, Constant.CREATED_AT);
        Review review1 = new Review
                (Constant.NEW_REVIEW_ID, Constant.MY_SELLER_ID, Constant.REVIEW_TITLE, Constant.REVIEW_DESCRIPTION,
                        Constant.REVIEW_RATING, Constant.CREATED_AT);

        reviewHashMap.put("1", review);
        reviewHashMap.put("2", review1);

        return reviewHashMap;
    }


}
