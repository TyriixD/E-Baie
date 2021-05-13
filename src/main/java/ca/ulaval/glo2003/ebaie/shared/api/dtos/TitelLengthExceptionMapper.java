package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.TitleLengthInvalidException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class TitelLengthExceptionMapper implements ExceptionMapper<TitleLengthInvalidException> {

    @Override
    public Response toResponse(TitleLengthInvalidException e) {
        TitelLengthResponse titelLengthResponse = new TitelLengthResponse();
        titelLengthResponse.code = e.getCode();
        titelLengthResponse.message = e.getMessage();
        return Response.status(400).entity(titelLengthResponse).build();
    }
}
