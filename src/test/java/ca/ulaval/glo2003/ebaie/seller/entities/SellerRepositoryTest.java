package ca.ulaval.glo2003.ebaie.seller.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.seller.api.SellerException.InvalidSellerIdException;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.InMemorySellerRepositoryImp;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class SellerRepositoryTest {
    public InMemorySellerRepositoryImp sellerRepository = new InMemorySellerRepositoryImp();

    @Test
    public void givenASellerToAdd_whenCallingSaveMethod_returnTheRightSeller() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);

        sellerRepository.save(seller);

        assertThat(sellerRepository.findAll().size()).isEqualTo(1);
        assertThat(sellerRepository.findById(Constant.MY_SELLER_ID.getValue())).isEqualTo(seller);

    }

    @Test
    public void givenASellerMap_whenCallingFindAllMethod_returnAllTheSeller() {
        Map<String, Seller> sellerMap = fillSellerHashMap();
        sellerRepository.setSellerHashMap(sellerMap);

        List<Seller> sellerList = sellerRepository.findAll();

        assertThat(sellerList.get(0)).isEqualTo(sellerMap.get("1"));
        assertThat(sellerList.get(1)).isEqualTo(sellerMap.get("2"));
        assertThat(sellerList.size()).isEqualTo(2);

    }

    @Test
    public void givenASellerMap_whenCallingFindBy_returnListWithSeller() {
        Map<String, Seller> sellerMap = fillSellerHashMap();
        sellerRepository.setSellerHashMap(sellerMap);

        Seller seller = sellerRepository.findById(Constant.MY_SELLER_ID.getValue());

        assertThat(seller.getName()).isEqualTo(Constant.MY_NAME);
        assertThat(seller.getDescription()).isEqualTo(Constant.MY_DESCRIPTION);
    }

    @Test(expected = InvalidSellerIdException.class)
    public void givenANonExistingId_whenCallingFindBy_throwAnException() {
        Map<String, Seller> sellerMap = fillSellerHashMap();
        sellerRepository.setSellerHashMap(sellerMap);

        SellerId InvalidSellerId = new SellerId(Constant.INV_ID.toString());

        sellerRepository.findById(InvalidSellerId.toString());
    }

    private Map<String, Seller> fillSellerHashMap() {
        Map<String, Seller> sellerHashMap = new HashMap<>();
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);
        Seller seller2 = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);
        sellerHashMap.put("1", seller);
        sellerHashMap.put("2", seller2);

        return sellerHashMap;
    }

}
