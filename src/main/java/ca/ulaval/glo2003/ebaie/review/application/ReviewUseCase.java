package ca.ulaval.glo2003.ebaie.review.application;

import ca.ulaval.glo2003.ebaie.review.application.assembler.ReviewAssembler;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewCreationDto;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewDto;
import ca.ulaval.glo2003.ebaie.review.entities.Review;
import ca.ulaval.glo2003.ebaie.review.entities.ReviewFactory;
import ca.ulaval.glo2003.ebaie.review.entities.ReviewRepository;
import ca.ulaval.glo2003.ebaie.valueobjects.ReviewId;

import java.util.List;

public class ReviewUseCase {
    private final ReviewFactory reviewFactory;
    private final ReviewRepository reviewRepository;
    private final ReviewAssembler reviewAssembler;

    public ReviewUseCase(ReviewFactory reviewFactory, ReviewRepository reviewRepository, ReviewAssembler reviewAssembler) {
        this.reviewFactory = reviewFactory;
        this.reviewAssembler = reviewAssembler;
        this.reviewRepository = reviewRepository;
    }

    public ReviewId createReview(ReviewCreationDto dto) {
        Review review = reviewFactory.create(dto.sellerId, dto.title, dto.description, dto.rating, dto.createdAt);
        reviewRepository.save(review);
        return review.getReviewId();
    }

    public List<ReviewDto> getSellerReviews(String id) {
        List<Review> reviewList = reviewRepository.findSellerReviews(id);
        return reviewAssembler.toDtos( reviewList);
    }
}
