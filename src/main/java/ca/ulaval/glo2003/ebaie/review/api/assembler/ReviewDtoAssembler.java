package ca.ulaval.glo2003.ebaie.review.api.assembler;

import ca.ulaval.glo2003.ebaie.review.api.dtos.ReviewRequest;
import ca.ulaval.glo2003.ebaie.review.api.dtos.ReviewResponseReview;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewCreationDto;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewDto;
import ca.ulaval.glo2003.ebaie.seller.application.SellerUseCase;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.RatingException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.TitleLengthInvalidException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ReviewDtoAssembler {
    private final SellerUseCase sellerUseCase;

    public ReviewDtoAssembler(SellerUseCase sellerUseCase) {
        this.sellerUseCase = sellerUseCase;
    }

    public ReviewCreationDto fromRequest(ReviewRequest request, String sellerId) {
        if ((request.title.isEmpty()) || (request.rating == 0)) {
            throw new FieldEmptyException("le champ titre ou rating ne doit pas etre vide");
        }
        if ((request.title.length() > 100)) {
            throw new TitleLengthInvalidException("le commentaire ne doit pas depasser 100 caractéres");
        }
        if ((request.rating < 1 || request.rating > 5)) {
            throw new RatingException("la note doit etre entre 1 et 5 ");
        }
        ReviewCreationDto dto = new ReviewCreationDto();
        SellerDto sellerDto = sellerUseCase.getSeller(sellerId);
        Instant nowInstant = Instant.now();
        dto.sellerId = sellerDto.id;
        dto.title = request.title;
        dto.description = request.description;

        dto.rating = request.rating;
        dto.createdAt = nowInstant;

        return dto;

    }

    public ReviewResponseReview toResponse(List<ReviewDto> reviewList) {
        ReviewResponseReview response = new ReviewResponseReview();
        float somme = 0;
        List<String> description = new ArrayList<>();
        for (ReviewDto reviewDto : reviewList) {
            somme = (somme + reviewDto.rating);
            description.add(reviewDto.description);
        }
        somme = somme / reviewList.size();
        response.rating = somme;
        response.description = description;

        return response;

    }
/*
    public ReviewResponse toResponse(List<ReviewDto> reviewDto) {
        ReviewResponse response = new ReviewResponse();
        response.reviewList = reviewDto.stream().map(this::toResponse).collect(Collectors.toList());


        return response;

    }

 */
}
