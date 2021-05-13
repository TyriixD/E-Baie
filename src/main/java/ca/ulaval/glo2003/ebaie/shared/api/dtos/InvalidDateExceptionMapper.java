package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidDateExceptionMapper implements ExceptionMapper<InvalidDateException> {

    @Override
    public Response toResponse(InvalidDateException e) {
        InvalidDateResponse invalidDateResponse = new InvalidDateResponse();
        invalidDateResponse.code = e.getCode();
        invalidDateResponse.message = e.getMessage();
        return Response.status(500).entity(invalidDateResponse).build();

    }
}
