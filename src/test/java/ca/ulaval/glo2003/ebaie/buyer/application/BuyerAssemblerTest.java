package ca.ulaval.glo2003.ebaie.buyer.application;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.buyer.application.assembler.BuyerAssembler;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerDto;
import ca.ulaval.glo2003.ebaie.buyer.entities.Buyer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class BuyerAssemblerTest {
    BuyerAssembler buyerAssembler = new BuyerAssembler();

    @Test
    public void givenValidBuyer_whenCallingToDto_returnValidDtoObject() {
        Buyer buyer = fillBuyerObject();

        BuyerDto dto = buyerAssembler.toDto(buyer);

        assertThat(dto.name).isEqualTo(buyer.getName());
        assertThat(dto.birthDate).isEqualTo(buyer.getBirthDate());
        assertThat(dto.id).isEqualTo(buyer.getId());
    }

    @Test
    public void givenListOfBuyerDto_whenCallingToDtosFunction_returnValidListOfBuyers() {
        List<Buyer> buyerList = fillBuyerArrayList();

        List<BuyerDto> buyerDtoList = buyerAssembler.toDtos(buyerList);

        assertThat(buyerDtoList.get(0).name).isEqualTo(buyerList.get(0).getName());
        assertThat(buyerDtoList.get(1).name).isEqualTo(buyerList.get(1).getName());
        assertThat(buyerDtoList.get(0).id).isEqualTo(buyerList.get(0).getId());
        assertThat(buyerDtoList.get(1).id).isEqualTo(buyerList.get(1).getId());
        assertThat(buyerDtoList.size()).isEqualTo(2);
    }

    private Buyer fillBuyerObject() {

        return new Buyer(Constant.MY_NAME, Constant.MY_BIRTHDAY, Constant.MY_BUYER_ID);
    }

    private List<Buyer> fillBuyerArrayList() {
        List<Buyer> buyerArrayList = new ArrayList<>();

        Buyer buyer = fillBuyerObject();
        Buyer buyer2 = new Buyer(Constant.MY_NEW_NAME, Constant.MY_BIRTHDAY, Constant.MY_NEW_BUYER_ID);

        buyerArrayList.add(buyer);
        buyerArrayList.add(buyer2);

        return buyerArrayList;
    }
}
