package ca.ulaval.glo2003.ebaie.offer.application;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.offer.application.assembler.OfferAssembler;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferDto;
import ca.ulaval.glo2003.ebaie.offer.entities.Offer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class OfferAssemblerTest {
    OfferAssembler offerAssembler = new OfferAssembler();

    @Test
    public void givenOffer_whenCallingToDto_returnValidDtoObject() {
        Offer offer = fillOfferObject();

        OfferDto dto = offerAssembler.toDto(offer);

        assertThat(dto.id).isEqualTo(offer.getId());
        assertThat(dto.productId).isEqualTo(offer.getProductId());
        assertThat(dto.buyerId).isEqualTo(offer.getBuyerId());
        assertThat(dto.amount).isEqualTo(offer.getAmount());
        assertThat(dto.createdAt).isEqualTo(offer.getCreatedAt());
        assertThat(dto.biddingStatus).isEqualTo(offer.getBiddingStatus());
    }

    @Test
    public void givenListOfOfferDto_whenCallingToDtosFunction_returnValidNumberOfObjectInList() {
        List<Offer> offerList = fillOfferArrayList();

        List<OfferDto> offerDtos = offerAssembler.toDtos(offerList);

        assertThat(offerDtos.size()).isEqualTo(2);
    }

    private Offer fillOfferObject() {

        return new Offer(Constant.OFFER_ID, Constant.MY_ITEM_ID, Constant.MY_BUYER_ID,
                Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.BIDDING_STATUS);
    }

    private List<Offer> fillOfferArrayList() {
        List<Offer> offerArrayList = new ArrayList<>();

        Offer offer1 = new Offer(Constant.OFFER_ID, Constant.MY_ITEM_ID, Constant.MY_BUYER_ID,
                Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.BIDDING_STATUS);
        Offer offer2 = new Offer(Constant.NEW_OFFER_ID, Constant.MY_ITEM_ID, Constant.MY_BUYER_ID,
                Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.BIDDING_STATUS);

        offerArrayList.add(offer2);
        offerArrayList.add(offer1);

        return offerArrayList;
    }
}
