package ca.ulaval.glo2003.ebaie.shared.api.dtos;

import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.BiddingEndedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BiddingEndedExceptionMapper implements ExceptionMapper<BiddingEndedException> {

    @Override
    public Response toResponse(BiddingEndedException e) {
        BiddingEndedResponse biddingEndedResponse = new BiddingEndedResponse();
        biddingEndedResponse.code = e.getCode();
        biddingEndedResponse.message = e.getMessage();

        return Response.status(400).entity(biddingEndedResponse).build();
    }
}
