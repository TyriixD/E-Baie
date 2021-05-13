package ca.ulaval.glo2003.ebaie.inventory.api.InventoryException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidProductIdExceptionMapper implements ExceptionMapper<InvalidProductIdException> {
    @Override
    public Response toResponse(InvalidProductIdException e) {
        InvalidProductIdResponse invalidProductIdResponse = new InvalidProductIdResponse();
        invalidProductIdResponse.code = e.getCode();
        invalidProductIdResponse.message = e.getMessage();

        return Response.status(404).entity(invalidProductIdResponse).build();
    }

}
