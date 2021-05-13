package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FieldEmptyExceptionMapper implements ExceptionMapper<FieldEmptyException> {

    @Override
    public Response toResponse(FieldEmptyException e) {
        FieldEmptyResponse fieldEmptyResponse = new FieldEmptyResponse();
        fieldEmptyResponse.code = e.getCode();
        fieldEmptyResponse.message = e.getMessage();

        return Response.status(400).entity(fieldEmptyResponse).build();
    }
}
