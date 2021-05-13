package ca.ulaval.glo2003.ebaie.seller.api.dtos;

import ca.ulaval.glo2003.ebaie.seller.api.SellerException.InvalidSellerIdException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidSellerIdExceptionMapper implements ExceptionMapper<InvalidSellerIdException> {
    @Override
    public Response toResponse(InvalidSellerIdException e) {
        InvalidSellerIdResponse invalidSellerIdResponse = new InvalidSellerIdResponse();
        invalidSellerIdResponse.code = e.getCode();
        invalidSellerIdResponse.message = e.getMessage();
        return Response.status(404).entity(invalidSellerIdResponse).build();

    }
}
