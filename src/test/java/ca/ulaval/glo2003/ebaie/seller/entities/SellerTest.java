package ca.ulaval.glo2003.ebaie.seller.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SellerTest {


    @Test
    public void givenValidAttribute_whenCallingConstructor_thenReturnValidSeller() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);

        assertThat(seller.getName()).isEqualTo(Constant.MY_NAME);
        assertThat(seller.getDescription()).isEqualTo(Constant.MY_DESCRIPTION);
        assertThat(seller.getCreatedAt()).isEqualTo(Constant.CREATED_AT);
        assertThat(seller.getId()).isEqualTo(Constant.MY_SELLER_ID);

    }


    @Test
    public void givenValidSeller_whenCallingNameSetter_returnTheGivenName() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);

        seller.setName(Constant.MY_NEW_NAME);

        assertThat(seller.getName()).isEqualTo(Constant.MY_NEW_NAME);
    }

    @Test
    public void givenValidSeller_whenCallingDescriptionSetter_returnTheGivenDescription() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);

        seller.setDescription(Constant.MY_NEW_DESCRIPTION);

        assertThat(seller.getDescription()).isEqualTo(Constant.MY_NEW_DESCRIPTION);

    }

    @Test
    public void givenValidSeller_whenCallingIdSetter_returnTheGivenId() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);

        seller.setId(Constant.MY_NEW_SELLER_ID);

        assertThat(seller.getId()).isEqualTo(Constant.MY_NEW_SELLER_ID);
    }
}
