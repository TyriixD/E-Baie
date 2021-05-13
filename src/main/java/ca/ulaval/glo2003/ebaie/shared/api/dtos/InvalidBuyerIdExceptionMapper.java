package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidBuyerIdException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidBuyerIdExceptionMapper implements ExceptionMapper<InvalidBuyerIdException> {
    @Override
    public Response toResponse(InvalidBuyerIdException e) {
        InvalidBuyerIdReponse invalidBuyerIdReponse = new InvalidBuyerIdReponse();
        invalidBuyerIdReponse.code = e.getCode();
        invalidBuyerIdReponse.message = e.getMessage();
        return Response.status(404).entity(invalidBuyerIdReponse).build();

    }


}
