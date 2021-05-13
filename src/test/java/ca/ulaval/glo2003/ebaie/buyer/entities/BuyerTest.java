package ca.ulaval.glo2003.ebaie.buyer.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class BuyerTest {

    @Test
    public void givenValidAttribute_whenCallingConstructor_thenReturnValidBuyer() {
        Buyer buyer = new Buyer(Constant.MY_NAME, Constant.MY_BIRTHDAY, Constant.MY_BUYER_ID);

        assertThat(buyer.getName()).isEqualTo(Constant.MY_NAME);
        assertThat(buyer.getBirthDate()).isEqualTo(Constant.MY_BIRTHDAY);
        assertThat(buyer.getId()).isEqualTo(Constant.MY_BUYER_ID);
    }

    @Test
    public void givenValidBuyer_whenCallingDescriptionSetter_returnTheGivenDayOfBirth() {
        Buyer buyer = new Buyer(Constant.MY_NAME, Constant.MY_BIRTHDAY, Constant.MY_BUYER_ID);

        assertThat(buyer.getBirthDate()).isEqualTo(Constant.MY_BIRTHDAY);
    }
}
