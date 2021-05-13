package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.RatingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RatingExceptionMapper implements ExceptionMapper<RatingException> {

    @Override
    public Response toResponse(RatingException e) {
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.code = e.getCode();
        ratingResponse.message = e.getMessage();
        return Response.status(400).entity(ratingResponse).build();

    }

}
