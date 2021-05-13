package ca.ulaval.glo2003.ebaie.inventory.api.InventoryException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidProductInitialPriceExceptionMapper
        implements ExceptionMapper<InvalidProductInitialPriceException> {
    @Override
    public Response toResponse(InvalidProductInitialPriceException e) {
        InvalidProductInitialPriceResponse invalidProductInitialPriceResponse =
                new InvalidProductInitialPriceResponse();
        invalidProductInitialPriceResponse.code = e.getCode();
        invalidProductInitialPriceResponse.message = e.getMessage();

        return Response.status(400).entity(invalidProductInitialPriceResponse).build();
    }
}
