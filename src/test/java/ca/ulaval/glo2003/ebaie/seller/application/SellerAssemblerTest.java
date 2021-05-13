package ca.ulaval.glo2003.ebaie.seller.application;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.seller.application.assembler.SellerAssembler;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class SellerAssemblerTest {
    SellerAssembler sellerAssembler = new SellerAssembler();

    @Test
    public void givenSeller_whenCallingToDto_returnValidDtoObject() {
        Seller seller = fillSellerObject();

        SellerDto dto = sellerAssembler.toDto(seller);

        assertThat(dto.createdAt).isEqualTo(seller.getCreatedAt());
        assertThat(dto.id).isEqualTo(seller.getId());
        assertThat(dto.name).isEqualTo(seller.getName());
        assertThat(dto.description).isEqualTo(seller.getDescription());
    }

    @Test
    public void givenListOfSellerDto_whenCallingToDtosFunction_returnValidNumberOfObjectInList() {
        List<Seller> sellerList = fillSellerArrayList();

        List<SellerDto> sellerDtos = sellerAssembler.toDtos(sellerList);

        assertThat(sellerDtos.size()).isEqualTo(2);
    }

    private Seller fillSellerObject() {

        return new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);
    }

    private List<Seller> fillSellerArrayList() {
        List<Seller> sellerArrayList = new ArrayList<>();

        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);
        Seller seller2 = new Seller(Constant.MY_NEW_NAME, Constant.MY_NEW_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);

        sellerArrayList.add(seller2);
        sellerArrayList.add(seller);

        return sellerArrayList;
    }
}
