package ca.ulaval.glo2003.ebaie.buyer.api;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.buyer.api.assemblers.BuyerDtoAssembler;
import ca.ulaval.glo2003.ebaie.buyer.api.dto.BuyerRequest;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerCreationDto;
import ca.ulaval.glo2003.ebaie.inventory.application.ItemUseCase;
import ca.ulaval.glo2003.ebaie.offer.application.OfferUseCase;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateTimeException;
import org.junit.Test;
import org.mockito.Mock;

import static com.google.common.truth.Truth.assertThat;

public class BuyerDtoAssemblerTest {

    @Mock
    OfferUseCase offerUseCase;
    ItemUseCase itemUseCase;
    public BuyerDtoAssembler buyerDtoAssembler = new BuyerDtoAssembler(offerUseCase, itemUseCase);

    @Test
    public void givenBuyerRequestObject_whenCallingFromRequest_returnValidDtoObject() {
        BuyerRequest buyerRequest = fillBuyerRequestObject();

        BuyerCreationDto dto = buyerDtoAssembler.fromRequest(buyerRequest);

        assertThat(dto.name).isEqualTo(buyerRequest.name);
        assertThat(dto.birthDate.toString()).isEqualTo(buyerRequest.birthDate);
    }

    @Test(expected = InvalidDateException.class)
    public void givenInvalidBirthday_whenCallingFromRequest_throwInvalidDateException() {
        BuyerRequest buyerRequest = fillBuyerRequestObject();
        buyerRequest.birthDate = Constant.INVALID_BIRTHDAY.toString();

        buyerDtoAssembler.fromRequest(buyerRequest);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenEmptyName_whenCallingFromRequest_throwFieldEmptyException() {
        BuyerRequest buyerRequest = fillBuyerRequestObject();
        buyerRequest.name = Constant.EMPTY_NAME;

        buyerDtoAssembler.fromRequest(buyerRequest);
    }

    @Test(expected = InvalidDateTimeException.class)
    public void givenEmptyDate_whenCallingFromRequest_throwInvalidDateTimeException() {
        BuyerRequest buyerRequest = fillBuyerRequestObject();
        buyerRequest.birthDate = Constant.EMPTY_DATE_FIELD;

        buyerDtoAssembler.fromRequest(buyerRequest);
    }

    @Test(expected = InvalidDateTimeException.class)
    public void givenInvalidDate_whenCallingFromRequest_throwInvalidDateTimeException() {
        BuyerRequest buyerRequest = fillBuyerRequestObject();
        buyerRequest.birthDate = Constant.BAD_DATE_FIELD;

        buyerDtoAssembler.fromRequest(buyerRequest);
    }

    private BuyerRequest fillBuyerRequestObject() {
        BuyerRequest buyerRequest = new BuyerRequest();
        buyerRequest.name = Constant.MY_NAME;
        buyerRequest.birthDate = Constant.MY_BIRTHDAY.toString();

        return buyerRequest;
    }


}
