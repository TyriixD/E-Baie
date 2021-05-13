package ca.ulaval.glo2003.ebaie.inventory.api;

import ca.ulaval.glo2003.ebaie.CreationUrl;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemRequest;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemResponseItem;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemsResponse;
import ca.ulaval.glo2003.ebaie.inventory.application.ItemUseCase;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemCreationDto;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/inventory")
public class ItemResource {
    private final ItemUseCase itemUseCase;
    private final ItemDtoAssembler itemDtoAssembler;
    private final CreationUrl url;

    public ItemResource(ItemUseCase itemUseCase,
                        ItemDtoAssembler itemDtoAssembler, CreationUrl url) {
        this.itemUseCase = itemUseCase;
        this.itemDtoAssembler = itemDtoAssembler;
        this.url = url;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<ItemDto> items = itemUseCase.getAllItems
                ();
        ItemsResponse response = itemDtoAssembler.toResponse(items);

        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("productId") String id) {
        ItemDto item = itemUseCase.getItem(id);
        ItemResponseItem response = itemDtoAssembler.toResponse(item);

        return Response.ok().entity(response).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(ItemRequest itemRequest) throws URISyntaxException {
        ItemCreationDto dto = itemDtoAssembler.fromRequest(itemRequest);
        ItemId id = itemUseCase.createItem(dto);
        URI uri = new URI(
                url.getHeaderHostName() + "/inventory/" + id.getValue());

        return Response.status(Response.Status.CREATED).location(uri).build();
    }
}
