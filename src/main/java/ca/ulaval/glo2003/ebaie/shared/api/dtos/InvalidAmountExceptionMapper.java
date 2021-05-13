package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidAmountException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidAmountExceptionMapper implements ExceptionMapper<InvalidAmountException> {

    @Override
    public Response toResponse(InvalidAmountException e) {
        InvalidAmountResponse invalidAmountResponse = new InvalidAmountResponse();
        invalidAmountResponse.code = e.getCode();
        invalidAmountResponse.message = e.getMessage();

        return Response.status(400).entity(invalidAmountResponse).build();
    }
}
