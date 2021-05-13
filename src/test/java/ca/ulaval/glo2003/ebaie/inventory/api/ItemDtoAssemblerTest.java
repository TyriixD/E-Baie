package ca.ulaval.glo2003.ebaie.inventory.api;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductDurationException;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductInitialPriceException;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemRequest;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemResponseItem;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemsResponse;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemCreationDto;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.seller.application.SellerUseCase;
import ca.ulaval.glo2003.ebaie.seller.application.assembler.SellerAssembler;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerFactory;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.InMemorySellerRepositoryImp;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateTimeException;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;


public class ItemDtoAssemblerTest {
    private final SellerFactory sellerFactory = new SellerFactory();
    private final SellerAssembler sellerAssembler = new SellerAssembler();
    private final InMemorySellerRepositoryImp sellerRepository = new InMemorySellerRepositoryImp();
    private final SellerUseCase sellerUseCase = new SellerUseCase(sellerFactory, sellerRepository, sellerAssembler);

    private ItemDtoAssembler validItemDtoAssembler;

    @Before
    public void setUp() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);
        sellerRepository.save(seller);
        validItemDtoAssembler = new ItemDtoAssembler(sellerUseCase);
    }

    @Test
    public void givenItemRequest_whenCallingFromRequest_returnValidDto() {
        ItemRequest itemRequest = fillItemRequestObject();

        ItemCreationDto itemCreationDto = validItemDtoAssembler.fromRequest(itemRequest);

        assertThat(itemCreationDto.description).isEqualTo(itemRequest.description);
    }

    @Test
    public void givenItemRequest_whenCallingFromRequest_returnNotNullDtoObject() {
        ItemRequest itemRequest = fillItemRequestObject();

        ItemCreationDto itemCreationDto = validItemDtoAssembler.fromRequest(itemRequest);

        assertThat(itemCreationDto).isNotNull();
    }

    @Test
    public void givenItemDto_whenCallingToResponse_returnValidItemResponseItem() {
        ItemDto itemDto = fillItemDtoObject();

        ItemResponseItem response = validItemDtoAssembler.toResponse(itemDto);
        Instant endTime = response.startTime.plus(itemDto.duration, ChronoUnit.DAYS);

        assertThat(response.id).isEqualTo((itemDto.id).getValue());
        assertThat(response.name).isEqualTo(itemDto.name);
        assertThat(response.sellerId).isEqualTo((itemDto.sellerId).getValue());
        assertThat(response.description).isEqualTo(itemDto.description);
        assertThat(response.initialPrice).isEqualTo((itemDto.initialPrice).getAmount());
        assertThat(response.currentPrice).isEqualTo((itemDto.currentPrice).getAmount());
        assertThat(response.startTime).isEqualTo(itemDto.startTime);
        assertThat(response.endTime).isEqualTo(endTime.toString());
    }

    @Test
    public void givenAListOfDto_whenCallingToResponse_thenReturnAnItemResponse() {
        List<ItemDto> itemDtoList = fillItemDtoArrayList();

        ItemsResponse response = validItemDtoAssembler.toResponse(itemDtoList);

        assertThat(response.itemList.get(0).name).isEqualTo(itemDtoList.get(0).name);
        assertThat(response.itemList.get(1).name).isEqualTo(itemDtoList.get(1).name);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenAnInvalidName_whenCallingFromRequest_throwFieldEmptyException() {
        ItemRequest itemRequest = fillItemRequestObject();
        itemRequest.name = Constant.EMPTY_NAME;

        validItemDtoAssembler.fromRequest(itemRequest);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenAnEmptyDescription_whenCallingFromRequest_throwFieldEmptyException() {
        ItemRequest itemRequest = fillItemRequestObject();
        itemRequest.description = Constant.EMPTY_DESCRIPTION;

        validItemDtoAssembler.fromRequest(itemRequest);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenAnEmptySellerId_whenCallingFromRequest_throwFieldEmptyException() {
        ItemRequest itemRequest = fillItemRequestObject();
        itemRequest.sellerId = Constant.EMPTY_SELLER_ID;

        validItemDtoAssembler.fromRequest(itemRequest);
    }

    @Test(expected = InvalidProductInitialPriceException.class)
    public void givenAnInvalidInitialPrice_whenCallingFromRequest_throwInvalidProductionInitialPriceException() {
        ItemRequest itemRequest = fillItemRequestObject();
        itemRequest.initialPrice = Constant.INVALID_PRICE.getAmount();

        validItemDtoAssembler.fromRequest(itemRequest);
    }


    @Test(expected = InvalidProductDurationException.class)
    public void givenAnInvalidLowDuration_whenCallingFromRequest_throwInvalidProductDurationException() {
        ItemRequest itemRequest = fillItemRequestObject();
        itemRequest.duration = 0;

        validItemDtoAssembler.fromRequest(itemRequest);
    }

    @Test(expected = InvalidProductDurationException.class)
    public void givenAnInvalidHighDuration_whenCallingFromRequest_throwInvalidProductDurationException() {
        ItemRequest itemRequest = fillItemRequestObject();
        itemRequest.duration = Constant.INVALID_DURATION;

        validItemDtoAssembler.fromRequest(itemRequest);
    }

    @Test(expected = InvalidDateTimeException.class)
    public void givenAnInvalidStartTime_whenCallingFromRequest_throwInvalidDateTimeException() {
        ItemRequest itemRequest = fillItemRequestObject();
        itemRequest.startTime = Constant.INVALID_START_TIME;

        validItemDtoAssembler.fromRequest(itemRequest);
    }

    private List<ItemDto> fillItemDtoArrayList() {
        List<ItemDto> itemDtoArrayList = new ArrayList<>();

        ItemDto itemDto = new ItemDto();
        ItemDto itemDto2 = new ItemDto();

        itemDto.id = Constant.MY_ITEM_ID;
        itemDto.sellerId = Constant.MY_SELLER_ID;
        itemDto.name = Constant.MY_NAME;
        itemDto.description = Constant.MY_DESCRIPTION;
        itemDto.initialPrice = Constant.MY_INITIAL_PRICE;
        itemDto.currentPrice = Constant.MY_CURRENT_PRICE;
        itemDto.startTime = Constant.START_TIME;
        itemDto.duration = Constant.MY_DURATION;

        itemDto2.id = Constant.MY_ITEM_ID;
        itemDto2.sellerId = Constant.MY_NEW_SELLER_ID;
        itemDto2.name = Constant.MY_NAME;
        itemDto2.description = Constant.MY_DESCRIPTION;
        itemDto2.initialPrice = Constant.MY_INITIAL_PRICE;
        itemDto2.currentPrice = Constant.MY_CURRENT_PRICE;
        itemDto2.startTime = Constant.START_TIME;
        itemDto2.duration = Constant.MY_DURATION;

        itemDtoArrayList.add(itemDto);
        itemDtoArrayList.add(itemDto2);

        return itemDtoArrayList;
    }

    private ItemRequest fillItemRequestObject() {
        ItemRequest itemRequest = new ItemRequest();

        itemRequest.sellerId = Constant.MY_SELLER_ID.getValue();
        itemRequest.name = Constant.MY_NAME;
        itemRequest.description = Constant.MY_DESCRIPTION;
        itemRequest.initialPrice = Constant.MY_INITIAL_PRICE.getAmount();
        itemRequest.startTime = Constant.START_TIME.toString();
        itemRequest.duration = Constant.MY_DURATION;

        return itemRequest;
    }

    private ItemDto fillItemDtoObject() {
        ItemDto itemDto = new ItemDto();
        itemDto.id = Constant.MY_ITEM_ID;
        itemDto.sellerId = Constant.MY_SELLER_ID;
        itemDto.name = Constant.MY_NAME;
        itemDto.description = Constant.MY_DESCRIPTION;
        itemDto.initialPrice = Constant.MY_INITIAL_PRICE;
        itemDto.currentPrice = Constant.MY_CURRENT_PRICE;
        itemDto.startTime = Constant.START_TIME;
        itemDto.duration = Constant.MY_DURATION;

        return itemDto;
    }
}