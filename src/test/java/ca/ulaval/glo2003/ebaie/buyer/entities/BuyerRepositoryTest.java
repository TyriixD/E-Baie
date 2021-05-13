package ca.ulaval.glo2003.ebaie.buyer.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.buyer.infrastructure.persistance.BuyerRepositoryImplementation;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidBuyerIdException;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;


public class BuyerRepositoryTest {
    public BuyerRepositoryImplementation buyerRepository = new BuyerRepositoryImplementation();

    @Test
    public void givenBuyerToAdd_whenCallingSaveMethod_returnRightBuyerInMemory() {
        Buyer buyer = new Buyer(Constant.MY_NAME, Constant.MY_BIRTHDAY, Constant.MY_BUYER_ID);

        buyerRepository.save(buyer);

        assertThat(buyerRepository.findAll().size()).isEqualTo(1);
        assertThat(buyerRepository.findById(Constant.MY_BUYER_ID.getValue())).isEqualTo(buyer);
    }


    @Test
    public void givenABuyerHashMap_WhenCallingFindAllMethod_ReturnAllTheBuyers() {
        Map<String, Buyer> buyerHashMap = fillBuyerHashMap();
        buyerRepository.setBuyerHashMap(buyerHashMap);

        List<Buyer> buyerList = buyerRepository.findAll();

        assertThat(buyerList.get(0)).isEqualTo(buyerHashMap.get("1"));
        assertThat(buyerList.get(1)).isEqualTo(buyerHashMap.get("2"));
        assertThat(buyerList.size()).isEqualTo(2);

    }

    @Test
    public void givenABuyerHashMap_WhenCallingFindById_ReturnTheBuyer() {
        Map<String, Buyer> buyerMap = fillBuyerHashMap();
        buyerRepository.setBuyerHashMap(buyerMap);

        Buyer buyer = buyerRepository.findById(Constant.MY_BUYER_ID.getValue());

        assertThat(buyer.getName()).isEqualTo(Constant.MY_NAME);
        assertThat(buyer.getBirthDate()).isEqualTo(Constant.MY_BIRTHDAY);
    }


    @Test(expected = InvalidBuyerIdException.class)
    public void givenANonExistingId_WhenCallingFindBy_ThrowAnException() {
        Map<String, Buyer> buyerMap = fillBuyerHashMap();
        buyerRepository.setBuyerHashMap(buyerMap);

        buyerRepository.findById(Constant.INV_ID.getValue());
    }

    private Map<String, Buyer> fillBuyerHashMap() {
        Map<String, Buyer> buyerHashMap = new HashMap<>();

        Buyer buyer1 = new Buyer(Constant.MY_NAME, Constant.MY_BIRTHDAY, Constant.MY_BUYER_ID);
        Buyer buyer2 = new Buyer(Constant.MY_NEW_NAME, Constant.MY_BIRTHDAY, Constant.MY_NEW_BUYER_ID);

        buyerHashMap.put("1", buyer1);
        buyerHashMap.put("2", buyer2);

        return buyerHashMap;
    }
}
