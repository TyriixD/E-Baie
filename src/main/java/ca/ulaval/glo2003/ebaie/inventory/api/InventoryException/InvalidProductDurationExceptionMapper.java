package ca.ulaval.glo2003.ebaie.inventory.api.InventoryException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidProductDurationExceptionMapper
        implements ExceptionMapper<InvalidProductDurationException> {
    @Override
    public Response toResponse(InvalidProductDurationException e) {
        InvalidProductDurationResponse invalidProductDurationResponse =
                new InvalidProductDurationResponse();
        invalidProductDurationResponse.code = e.getCode();
        invalidProductDurationResponse.message = e.getMessage();
        return Response.status(400).entity(invalidProductDurationResponse).build();
    }
}
