package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.GlobalException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GlobalExceptionMapper implements ExceptionMapper<GlobalException> {
    @Override
    public Response toResponse(GlobalException e) {
        e.printStackTrace();
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse();
        globalExceptionResponse.code = e.getCode();
        globalExceptionResponse.message = e.getMessage();
        return Response.status(500).entity(globalExceptionResponse).build();

    }
}
