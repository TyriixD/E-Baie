package ca.ulaval.glo2003.ebaie.review.api;

import ca.ulaval.glo2003.ebaie.CreationUrl;
import ca.ulaval.glo2003.ebaie.review.api.assembler.ReviewDtoAssembler;
import ca.ulaval.glo2003.ebaie.review.api.dtos.ReviewRequest;
import ca.ulaval.glo2003.ebaie.review.api.dtos.ReviewResponseReview;
import ca.ulaval.glo2003.ebaie.review.application.ReviewUseCase;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewCreationDto;
import ca.ulaval.glo2003.ebaie.review.application.dto.ReviewDto;
import ca.ulaval.glo2003.ebaie.review.entities.Review;
import ca.ulaval.glo2003.ebaie.valueobjects.ReviewId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/reviews")
public class ReviewResources {
        private final ReviewUseCase reviewUseCase;
        private final ReviewDtoAssembler reviewDtoAssembler;
        private final CreationUrl url;

        public ReviewResources(ReviewUseCase reviewUseCase,
                               ReviewDtoAssembler reviewDtoAssembler, CreationUrl url) {
            this.reviewUseCase = reviewUseCase;
            this.reviewDtoAssembler = reviewDtoAssembler;
            this.url = url;
        }

    @GET
    @Path("/review/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSellerReview(@PathParam("sellerId") String id) {
        List<ReviewDto> sellerReviews = reviewUseCase.getSellerReviews(id);
        ReviewResponseReview response = reviewDtoAssembler.toResponse(sellerReviews);

        return Response.ok().entity(response).build();
    }
    @POST
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(ReviewRequest reviewRequest, @PathParam("sellerId")String sellerId) throws URISyntaxException {
        ReviewCreationDto dto = reviewDtoAssembler.fromRequest(reviewRequest, sellerId);
        ReviewId id = reviewUseCase.createReview(dto);
        URI uri = new URI(
                url.getHeaderHostName() + "/review/" + id.getValue());

        return Response.status(Response.Status.CREATED).location(uri).build();

        }
    }



