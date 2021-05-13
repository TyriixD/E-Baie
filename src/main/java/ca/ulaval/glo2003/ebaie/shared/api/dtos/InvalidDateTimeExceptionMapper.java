package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateTimeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidDateTimeExceptionMapper implements ExceptionMapper<InvalidDateTimeException> {
    @Override
    public Response toResponse(InvalidDateTimeException e) {
        InvalidDateTimeResponse invalidDateTimeResponse = new InvalidDateTimeResponse();
        invalidDateTimeResponse.code = e.getCode();
        invalidDateTimeResponse.message = e.getMessage();
        return Response.status(400).entity(invalidDateTimeResponse).build();

    }
}
