package ca.ulaval.glo2003.ebaie.buyer.api;

import ca.ulaval.glo2003.ebaie.CreationUrl;
import ca.ulaval.glo2003.ebaie.buyer.api.assemblers.BuyerDtoAssembler;
import ca.ulaval.glo2003.ebaie.buyer.api.dto.BuyerRequest;
import ca.ulaval.glo2003.ebaie.buyer.api.dto.BuyerResponseBuyer;
import ca.ulaval.glo2003.ebaie.buyer.application.BuyerUseCase;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerCreationDto;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerDto;
import ca.ulaval.glo2003.ebaie.valueobjects.BuyerId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/buyer")
public class BuyerRessource {

    private final BuyerUseCase buyerUseCase;
    private final BuyerDtoAssembler buyerDtoAssembler;
    private CreationUrl url;

    public BuyerRessource(BuyerUseCase buyerUseCase, BuyerDtoAssembler buyerDtoAssembler,CreationUrl url) {
        this.buyerUseCase = buyerUseCase;
        this.url = url;
        this.buyerDtoAssembler = buyerDtoAssembler;
    }

    @GET
    @Path("/{buyerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("buyerId") String id) {
        BuyerDto buyer = buyerUseCase.getBuyerById(id);
        BuyerResponseBuyer response = buyerDtoAssembler.toResponse(buyer);
        return Response.ok().entity(response).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object createItem(BuyerRequest buyerRequest) throws URISyntaxException {

        BuyerCreationDto dto = buyerDtoAssembler.fromRequest(buyerRequest);

        BuyerId id = buyerUseCase.createBuyer(dto);
        URI uri = new URI(
                url.getHeaderHostName()+"/buyer/" + id.getValue());

        return Response.status(Response.Status.CREATED).location(uri).build();

    }
}