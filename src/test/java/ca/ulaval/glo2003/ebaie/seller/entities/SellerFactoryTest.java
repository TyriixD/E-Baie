package ca.ulaval.glo2003.ebaie.seller.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SellerFactoryTest {
    public SellerFactory sellerFactory = new SellerFactory();

    @Test
    public void givenValidParameter_whenCallingCreate_returnValidSeller() {
        Seller seller = sellerFactory.create(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.CREATED_AT);

        assertThat(seller.getName()).isNotNull();
        assertThat(seller.getDescription()).isEqualTo(Constant.MY_DESCRIPTION);
        assertThat(seller.getCreatedAt()).isEqualTo(Constant.CREATED_AT);
    }


}
