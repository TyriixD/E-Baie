package ca.ulaval.glo2003.ebaie.offer.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.offer.api.OfferException.InvalidOfferIdException;
import ca.ulaval.glo2003.ebaie.offer.infrastructure.persistence.OfferRepositoryImp;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class
OfferRepositoryTest {
    public OfferRepositoryImp offerRepository = new OfferRepositoryImp();

    @Test
    public void givenAnOfferToAdd_whenCallingSaveMethod_returnTheRightOfferInMemory() {
        Offer offer = new Offer(Constant.OFFER_ID, Constant.MY_ITEM_ID, Constant.MY_BUYER_ID,
                Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.BIDDING_STATUS);

        offerRepository.save(offer);

        assertThat(offerRepository.findAll().size()).isEqualTo(1);
        assertThat(offerRepository.findById(Constant.OFFER_ID.getValue())).isEqualTo(offer);
    }

    @Test
    public void givenAnOfferHashMap_WhenCallingFindAllMethod_ReturnAllTheOffers() {
        Map<String, Offer> offerHashMap = fillOfferHashMap();
        offerRepository.setOfferHashMap(offerHashMap);

        List<Offer> offerList = offerRepository.findAll();

        assertThat(offerList.get(0)).isEqualTo(offerHashMap.get("1"));
        assertThat(offerList.get(1)).isEqualTo(offerHashMap.get("2"));
        assertThat(offerList.size()).isEqualTo(2);

    }

    @Test
    public void givenAnOfferHashMap_WhenCallingFindById_ReturnTheOffer() {
        Map<String, Offer> offerHashMap = fillOfferHashMap();
        offerRepository.setOfferHashMap(offerHashMap);

        Offer offer = offerRepository.findById(Constant.OFFER_ID.getValue());

        assertThat(offer.getProductId()).isEqualTo(Constant.MY_ITEM_ID);
        assertThat(offer.getBuyerId()).isEqualTo(Constant.MY_BUYER_ID);
        assertThat(offer.getAmount()).isEqualTo(Constant.MY_CURRENT_PRICE);
        assertThat(offer.getCreatedAt()).isEqualTo(Constant.START_TIME);
        assertThat(offer.getBiddingStatus()).isEqualTo(Constant.BIDDING_STATUS);
    }

    @Test(expected = InvalidOfferIdException.class)
    public void givenANonExistingId_WhenCallingFindById_ThrowAnException() {
        Map<String, Offer> offerHashMap = fillOfferHashMap();
        offerRepository.setOfferHashMap(offerHashMap);

        offerRepository.findById(Constant.INV_ID.getValue());
    }

    private Map<String, Offer> fillOfferHashMap() {
        Map<String, Offer> offerHashMap = new HashMap<>();

        Offer offer1 = new Offer(Constant.OFFER_ID, Constant.MY_ITEM_ID, Constant.MY_BUYER_ID,
                Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.BIDDING_STATUS);
        Offer offer2 = new Offer(Constant.NEW_OFFER_ID, Constant.MY_ITEM_ID, Constant.MY_BUYER_ID,
                Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.BIDDING_STATUS);

        offerHashMap.put("1", offer1);
        offerHashMap.put("2", offer2);

        return offerHashMap;
    }
}
