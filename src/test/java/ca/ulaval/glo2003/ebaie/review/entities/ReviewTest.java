package ca.ulaval.glo2003.ebaie.review.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.review.entities.Review;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ReviewTest {

    @Test
    public void givenValidAttributes_whenCallingConstructor_thenReturnValidReview(){
        Review review = new Review
                (Constant.REVIEW_ID,Constant.MY_SELLER_ID, Constant.REVIEW_TITLE,Constant.REVIEW_DESCRIPTION,
                        Constant.REVIEW_RATING, Constant.CREATED_AT);
        assertThat(review.getReviewId()).isEqualTo(Constant.REVIEW_ID);
        assertThat(review.getSellerId()).isEqualTo(Constant.MY_SELLER_ID);
        assertThat(review.getTitle()).isEqualTo(Constant.REVIEW_TITLE);
        assertThat(review.getDescription()).isEqualTo(Constant.REVIEW_DESCRIPTION);
        assertThat(review.getRating()).isEqualTo(Constant.REVIEW_RATING);
        assertThat(review.getcreatedAt()).isEqualTo(Constant.CREATED_AT);
    }


}
