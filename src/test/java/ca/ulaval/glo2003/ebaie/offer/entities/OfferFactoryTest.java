package ca.ulaval.glo2003.ebaie.offer.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class OfferFactoryTest {
    public OfferFactory offerFactory = new OfferFactory();

    @Test
    public void givenValidParameter_whenCallingCreate_returnValidOffer() {
        Offer offer = offerFactory.create(Constant.MY_ITEM_ID, Constant.MY_BUYER_ID, Constant.MY_CURRENT_PRICE,
                Constant.START_TIME, Constant.BIDDING_STATUS);

        assertThat(offer.getId()).isNotNull();
        assertThat(offer.getProductId()).isEqualTo(Constant.MY_ITEM_ID);
        assertThat(offer.getBuyerId()).isEqualTo(Constant.MY_BUYER_ID);
        assertThat(offer.getAmount()).isEqualTo(Constant.MY_CURRENT_PRICE);
        assertThat(offer.getCreatedAt()).isEqualTo(Constant.START_TIME);
        assertThat(offer.getBiddingStatus()).isEqualTo(Constant.BIDDING_STATUS);
    }
}
