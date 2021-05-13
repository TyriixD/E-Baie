package ca.ulaval.glo2003.ebaie.buyer.api.dto;

import java.util.List;

public class BuyerResponseBuyer {
    public String id;
    public String name;
    public List<BuyerBiddingResponse> biddingOffers;
    public List<BuyerObtainedResponse> obtainedProducts;

}