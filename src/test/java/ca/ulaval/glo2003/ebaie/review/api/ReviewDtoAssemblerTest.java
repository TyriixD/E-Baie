package ca.ulaval.glo2003.ebaie.review.api;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.review.api.assembler.ReviewDtoAssembler;
import ca.ulaval.glo2003.ebaie.review.api.dtos.ReviewRequest;
import ca.ulaval.glo2003.ebaie.review.api.dtos.ReviewResponseReview;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewCreationDto;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewDto;
import ca.ulaval.glo2003.ebaie.seller.application.SellerUseCase;
import ca.ulaval.glo2003.ebaie.seller.application.assembler.SellerAssembler;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerFactory;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.InMemorySellerRepositoryImp;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.RatingException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ReviewDtoAssemblerTest {

    private final SellerFactory sellerFactory = new SellerFactory();
    private final InMemorySellerRepositoryImp sellerRepository = new InMemorySellerRepositoryImp();
    private final SellerAssembler sellerAssembler = new SellerAssembler();

    SellerUseCase sellerUseCase = new SellerUseCase(sellerFactory, sellerRepository, sellerAssembler);
    ReviewDtoAssembler reviewDtoAssembler;

    @Before
    public void set_up() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);
        sellerRepository.save(seller);
        reviewDtoAssembler = new ReviewDtoAssembler(sellerUseCase);

    }
//TODO VERFIER LE TIME

    @Test
    public void givenReveiwRequest_whenCallingFromRequest_returnValidDto() {
        ReviewRequest reviewRequest = fillReviewRequestObject();

        ReviewCreationDto reviewCreationDto = reviewDtoAssembler.fromRequest(reviewRequest, Constant.MY_SELLER_ID.getValue());

        assertThat(reviewCreationDto.description).isEqualTo(Constant.REVIEW_DESCRIPTION);
        assertThat(reviewCreationDto.title).isEqualTo(Constant.REVIEW_TITLE);
        assertThat(reviewCreationDto.sellerId).isEqualTo(Constant.MY_SELLER_ID);
        assertThat(reviewCreationDto.title).isEqualTo(Constant.REVIEW_TITLE);

    }

    @Test(expected = FieldEmptyException.class)
    public void givenAnInvalidTitel_whenCallingFromRequest_throwFieldEmptyException() {
        ReviewRequest reviewRequest = fillReviewRequestObject();
        reviewRequest.title = "";

        reviewDtoAssembler.fromRequest(reviewRequest, Constant.MY_SELLER_ID.getValue());

    }

    @Test(expected = RatingException.class)
    public void givenAnInvalidRating_whenCallingFromRequest_throwFieldEmptyException() {
        ReviewRequest reviewRequest = fillReviewRequestObject();
        reviewRequest.rating = 6;

        reviewDtoAssembler.fromRequest(reviewRequest, Constant.MY_SELLER_ID.getValue());

    }

    @Test
    public void givenReviewResponse_whenCallingToResponse_returnValidReviewResponse() {
        List<ReviewDto> reviews = fillReviewDtoObject();
        ReviewResponseReview response = reviewDtoAssembler.toResponse(reviews);
        List<String> description = response.description;

        assertThat(response.rating).isEqualTo((reviews.get(0).rating));
        assertThat(response.description).containsExactlyElementsIn(description);

    }

    private ReviewRequest fillReviewRequestObject() {
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.rating = Constant.REVIEW_RATING;
        reviewRequest.title = Constant.REVIEW_TITLE;
        reviewRequest.description = Constant.REVIEW_DESCRIPTION;


        return reviewRequest;
    }


    private List<ReviewDto> fillReviewDtoObject() {
        List<ReviewDto> reviewList = new ArrayList<>();
        ReviewDto dto = new ReviewDto();
        dto.reviewId = Constant.REVIEW_ID;
        dto.sellerId = Constant.MY_SELLER_ID;

        dto.description = Constant.REVIEW_DESCRIPTION;
        dto.rating = Constant.REVIEW_RATING;

        reviewList.add(dto);
        return reviewList;
    }

}
