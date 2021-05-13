package ca.ulaval.glo2003.ebaie.seller.api;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.seller.api.assemblers.SellerDtoAssembler;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerRequest;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerResponse;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerResponseSeller;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerCreationDto;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class SellerDtoAssemblerTest {
    public SellerDtoAssembler sellerDtoAssembler = new SellerDtoAssembler();
    public SellerDto sellerDto;
    public SellerRequest sellerRequest;

    @Test
    public void givenSellerRequestObject_whenCallingFromRequest_returnValidDtoObject() {
        SellerCreationDto dto;
        sellerRequest = remplirObjet_sellerRequest();

        dto = sellerDtoAssembler.fromRequest(sellerRequest);

        assertThat(dto.description).isEqualTo(sellerRequest.description);
        assertThat(dto.name).isEqualTo(sellerRequest.name);
    }

    @Test
    public void givenSellerRequestObject_whenCallingFromRequest_returnDtoObjectNotNull() {
        SellerCreationDto dto;
        sellerRequest = remplirObjet_sellerRequest();

        dto = sellerDtoAssembler.fromRequest(sellerRequest);

        assertThat(dto).isNotNull();
    }

    @Test

    public void givenSellerDtoObject_whenCallingToResponse_returnValidSellerResponseSellerObjet() {
        SellerResponseSeller response;
        sellerDto = remplirObjet_sellerResponseSeller();

        response = sellerDtoAssembler.toResponse(sellerDto);

        assertThat(response.id).isEqualTo((sellerDto.id).getValue());
        assertThat(response.name).isEqualTo(sellerDto.name);
        assertThat(response.description).isEqualTo(sellerDto.description);
        assertThat(response.createdAt).isEqualTo(sellerDto.createdAt);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenNameFieldEmpty_whenCallingFromRequest_throwFieldEmptyException() {
        sellerRequest = remplirObjet_sellerRequest();
        sellerRequest.name = Constant.EMPTY_NAME;

        sellerDtoAssembler.fromRequest(sellerRequest);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenNullName_whenCallingFromRequest_throwFieldEmptyException() {
        sellerRequest = remplirObjet_sellerRequest();
        sellerRequest.name = null;

        sellerDtoAssembler.fromRequest(sellerRequest);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenNullDescription_whenCallingFromRequest_throwFieldEmptyException() {
        sellerRequest = remplirObjet_sellerRequest();
        sellerRequest.description = null;

        sellerDtoAssembler.fromRequest(sellerRequest);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenEmptyFieldDescription_whenCallingFromRequest_throwFieldEmptyException() {
        sellerRequest = remplirObjet_sellerRequest();
        sellerRequest.description = Constant.EMPTY_DESCRIPTION;

        sellerDtoAssembler.fromRequest(sellerRequest);
    }


    @Test
    public void givenListeSellerDtos_whenCallingToResponse_returnValidResposne() {
        List<SellerDto> sellerDtos = remplirObjet_listeResponse();

        SellerResponse response = sellerDtoAssembler.toResponse(sellerDtos);

        assertThat(response.sellerList.size()).isEqualTo(2);
    }


    @Test
    public void givenListeSellerDtos_whenCallingToResponse_returnValidResposneId() {
        List<SellerDto> sellerDtos = remplirObjet_listeResponse();

        SellerResponse response = sellerDtoAssembler.toResponse(sellerDtos);

        assertThat(response.sellerList.get(0).id).isEqualTo((sellerDtos.get(0).id).getValue());
        assertThat(response.sellerList.get(1).id).isEqualTo((sellerDtos.get(1).id).getValue());
    }

    @Test
    public void givenListeSellerDtos_whenCallingToResponse_returnValidResposneCreatedAt() {
        List<SellerDto> sellerDtos = remplirObjet_listeResponse();

        SellerResponse response = sellerDtoAssembler.toResponse(sellerDtos);

        assertThat(response.sellerList.get(1).createdAt).isEqualTo(sellerDtos.get(1).createdAt);
        assertThat(response.sellerList.get(0).createdAt).isEqualTo(sellerDtos.get(0).createdAt);
    }

    @Test
    public void givenListeSellerDtos_whenCallingToResponse_returnValidResposneDescription() {
        List<SellerDto> sellerDtos = remplirObjet_listeResponse();

        SellerResponse response = sellerDtoAssembler.toResponse(sellerDtos);

        assertThat(response.sellerList.get(0).description).isEqualTo(sellerDtos.get(0).description);
        assertThat(response.sellerList.get(1).description).isEqualTo(sellerDtos.get(1).description);
    }

    @Test
    public void givenListeSellerDtos_whenCallingToResponse_returnValidResposneName() {
        List<SellerDto> sellerDtos = remplirObjet_listeResponse();

        SellerResponse response = sellerDtoAssembler.toResponse(sellerDtos);

        assertThat(response.sellerList.get(0).name).isEqualTo(sellerDtos.get(0).name);
        assertThat(response.sellerList.get(1).name).isEqualTo(sellerDtos.get(1).name);
    }

    private List<SellerDto> remplirObjet_listeResponse() {
        List<SellerDto> sellerDtos = new ArrayList<>();
        SellerDto sellerDto = new SellerDto();
        SellerDto sellerDto2 = new SellerDto();
        sellerDto.id = Constant.MY_SELLER_ID;
        sellerDto.name = Constant.MY_NAME;
        sellerDto.description = Constant.MY_DESCRIPTION;
        sellerDto.createdAt = Constant.CREATED_AT;
        sellerDto2.id = Constant.MY_NEW_SELLER_ID;
        sellerDto2.name = Constant.MY_NEW_NAME;
        sellerDto2.description = Constant.MY_NEW_DESCRIPTION;
        sellerDto2.createdAt = Constant.CREATED_AT;
        sellerDtos.add(sellerDto);
        sellerDtos.add(sellerDto2);

        return sellerDtos;
    }

    private SellerRequest remplirObjet_sellerRequest() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = Constant.MY_NAME;
        sellerRequest.description = Constant.MY_DESCRIPTION;

        return sellerRequest;
    }

    private SellerDto remplirObjet_sellerResponseSeller() {
        SellerDto sellerDto = new SellerDto();
        sellerDto.createdAt = Constant.CREATED_AT;
        sellerDto.id = Constant.MY_SELLER_ID;
        sellerDto.name = Constant.MY_NAME;
        sellerDto.description = Constant.MY_DESCRIPTION;

        return sellerDto;
    }
}
