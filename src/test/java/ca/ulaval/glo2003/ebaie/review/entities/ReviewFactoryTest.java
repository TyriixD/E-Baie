package ca.ulaval.glo2003.ebaie.review.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ReviewFactoryTest {

    public ReviewFactory reviewFactory = new ReviewFactory();

    @Test
    public void givenValidParameter_whenCallingCreate_returnValidReview() {
        Review review = reviewFactory.create(Constant.MY_SELLER_ID, Constant.REVIEW_TITLE,
                Constant.REVIEW_DESCRIPTION, Constant.REVIEW_RATING, Constant.CREATED_AT);

        assertThat(review.getReviewId()).isNotNull();
        assertThat(review.getSellerId()).isEqualTo(Constant.MY_SELLER_ID);
        assertThat(review.getTitle()).isEqualTo(Constant.REVIEW_TITLE);
        assertThat(review.getDescription()).isEqualTo(Constant.REVIEW_DESCRIPTION);
        assertThat(review.getRating()).isEqualTo(Constant.REVIEW_RATING);
        assertThat(review.getcreatedAt()).isEqualTo(Constant.CREATED_AT);
    }


}
