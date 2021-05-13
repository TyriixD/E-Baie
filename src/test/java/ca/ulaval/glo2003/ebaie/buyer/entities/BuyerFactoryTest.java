package ca.ulaval.glo2003.ebaie.buyer.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class BuyerFactoryTest {
    public BuyerFactory buyerFactory = new BuyerFactory();

    @Test
    public void givenValidParameter_whenCallingCreate_returnValidBuyer() {
        Buyer buyer = buyerFactory.create(Constant.MY_NAME, Constant.MY_BIRTHDAY);

        assertThat(buyer.getName()).isNotNull();
        assertThat(buyer.getBirthDate()).isEqualTo(Constant.MY_BIRTHDAY);
    }
}
