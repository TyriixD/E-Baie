package ca.ulaval.glo2003.ebaie.review.application.assembler;

import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewDto;
import ca.ulaval.glo2003.ebaie.review.entities.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewAssembler {
    public List<ReviewDto> toDtos(List<Review> reviewList) {
        return reviewList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.reviewId = review.getReviewId();
        dto.sellerId = review.getSellerId();

        dto.description = review.getDescription();
        dto.rating = review.getRating();

        return dto;
    }
}
