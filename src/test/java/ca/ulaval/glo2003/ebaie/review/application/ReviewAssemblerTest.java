package ca.ulaval.glo2003.ebaie.review.application;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.review.application.assembler.ReviewAssembler;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewDto;
import ca.ulaval.glo2003.ebaie.review.entities.Review;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ReviewAssemblerTest {

    private ReviewAssembler reviewAssembler = new ReviewAssembler();

    @Test
    public void givenValidReview_whenCallingToDto_returnValidDtoObject() {
        Review review = fillReviewObject();

        ReviewDto dto = reviewAssembler.toDto(review);

        assertThat(dto.reviewId).isEqualTo(review.getReviewId());
        assertThat(dto.description).isEqualTo(review.getDescription());
        assertThat(dto.rating).isEqualTo(review.getRating());
        assertThat(dto.sellerId).isEqualTo(review.getSellerId());

    }

    @Test
    public void givenListOfReviewDto_whenCallingToDtosFunction_returnValidListOfReviews() {
        List<Review> reviewList = fillReviewArrayList();

        List<ReviewDto> reviewDtoList = reviewAssembler.toDtos(reviewList);

        assertThat(reviewDtoList.get(0).rating).isEqualTo(reviewList.get(0).getRating());
        assertThat(reviewDtoList.get(1).rating).isEqualTo(reviewList.get(1).getRating());
        assertThat(reviewDtoList.get(0).reviewId).isEqualTo(reviewList.get(0).getReviewId());
        assertThat(reviewDtoList.get(1).reviewId).isEqualTo(reviewList.get(1).getReviewId());
        assertThat(reviewDtoList.get(0).sellerId).isEqualTo(reviewList.get(0).getSellerId());
        assertThat(reviewDtoList.get(1).sellerId).isEqualTo(reviewList.get(1).getSellerId());
        assertThat(reviewDtoList.get(0).description).isEqualTo(reviewList.get(0).getDescription());
        assertThat(reviewDtoList.get(1).description).isEqualTo(reviewList.get(1).getDescription());
        assertThat(reviewDtoList.size()).isEqualTo(2);
    }

    private Review fillReviewObject() {

        return new Review
                (Constant.REVIEW_ID, Constant.MY_SELLER_ID, Constant.REVIEW_TITLE, Constant.REVIEW_DESCRIPTION,
                        Constant.REVIEW_RATING, Constant.CREATED_AT);
    }

    private List<Review> fillReviewArrayList() {
        List<Review> reviewList = new ArrayList<>();

        Review review = fillReviewObject();
        Review review1 = new Review
                (Constant.NEW_REVIEW_ID, Constant.MY_SELLER_ID, Constant.REVIEW_TITLE, Constant.REVIEW_DESCRIPTION,
                        Constant.REVIEW_RATING, Constant.CREATED_AT);
        reviewList.add(review);
        reviewList.add(review1);

        return reviewList;
    }


}
