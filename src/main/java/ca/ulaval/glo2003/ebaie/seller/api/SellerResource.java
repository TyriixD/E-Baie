package ca.ulaval.glo2003.ebaie.seller.api;

import ca.ulaval.glo2003.ebaie.CreationUrl;
import ca.ulaval.glo2003.ebaie.seller.api.assemblers.SellerDtoAssembler;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerRequest;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerResponse;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerResponseSeller;
import ca.ulaval.glo2003.ebaie.seller.application.SellerUseCase;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerCreationDto;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/seller")
public class SellerResource {
    private final SellerUseCase sellerUseCase;
    private final SellerDtoAssembler sellerDtoAssembler;
    private final CreationUrl url;

    public SellerResource(SellerUseCase sellerUseCase,
                          SellerDtoAssembler sellerDtoAssembler, CreationUrl url) {
        this.sellerUseCase = sellerUseCase;
        this.sellerDtoAssembler = sellerDtoAssembler;
        this.url = url;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSeller() {
        List<SellerDto> seller = sellerUseCase.getAllSellers();
        SellerResponse response = sellerDtoAssembler.toResponse(seller);

        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String id) {
        SellerDto seller = sellerUseCase.getSeller(id);
        SellerResponseSeller response = sellerDtoAssembler.toResponse(seller);

        return Response.ok().entity(response).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object createItem(SellerRequest sellerRequest) throws URISyntaxException {
        SellerCreationDto dto = sellerDtoAssembler.fromRequest(sellerRequest);
        SellerId id = sellerUseCase.createSeller(dto);
        URI uri = new URI(
                url.getHeaderHostName() + "/seller/" + id.getValue());

        return Response.status(Response.Status.CREATED).location(uri).build();

    }

}

