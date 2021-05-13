package ca.ulaval.glo2003.ebaie.offer.api;

import ca.ulaval.glo2003.ebaie.CreationUrl;
import ca.ulaval.glo2003.ebaie.offer.api.assemblers.OfferDtoAssembler;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OfferRequest;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OfferResponseOffer;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OffersResponse;
import ca.ulaval.glo2003.ebaie.offer.application.OfferUseCase;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferCreationDto;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/inventory")
public class OfferResource {
    private final OfferUseCase offerUseCase;
    private final OfferDtoAssembler offerDtoAssembler;
    private final CreationUrl url ;
    public OfferResource(OfferUseCase offerUseCase,
                         OfferDtoAssembler offerDtoAssembler,CreationUrl url) {
        this.offerUseCase = offerUseCase;
        this.offerDtoAssembler = offerDtoAssembler;
        this.url = url;
    }

    @GET
    @Path("/offers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOffer() {
        List<OfferDto> offer = offerUseCase.getAllOffers();
        OffersResponse response = offerDtoAssembler.toResponse(offer);

        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/offers/{offerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffer(@PathParam("offerId") String id) {
        OfferDto offer = offerUseCase.getOffer(id);
        OfferResponseOffer response = offerDtoAssembler.toResponse(offer);

        return Response.ok().entity(response).build();
    }

    @POST
    @Path("/{productId}/offer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object createItem(OfferRequest offerRequest, @PathParam("productId") String productId) throws URISyntaxException {

        OfferCreationDto dto = offerDtoAssembler.fromRequest(offerRequest, productId);
        String id = offerUseCase.createOffer(dto);
        URI uri = new URI(
                url.getHeaderHostName()+"/inventory/" + productId + "/" + id);

        return Response.status(Response.Status.CREATED).location(uri).build();

    }

}

