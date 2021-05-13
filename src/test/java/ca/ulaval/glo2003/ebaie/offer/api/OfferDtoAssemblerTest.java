package ca.ulaval.glo2003.ebaie.offer.api;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.buyer.application.BuyerUseCase;
import ca.ulaval.glo2003.ebaie.buyer.application.assembler.BuyerAssembler;
import ca.ulaval.glo2003.ebaie.buyer.entities.Buyer;
import ca.ulaval.glo2003.ebaie.buyer.entities.BuyerFactory;
import ca.ulaval.glo2003.ebaie.buyer.entities.BuyerRepository;
import ca.ulaval.glo2003.ebaie.buyer.infrastructure.persistance.BuyerRepositoryImplementation;
import ca.ulaval.glo2003.ebaie.inventory.application.ItemUseCase;
import ca.ulaval.glo2003.ebaie.inventory.application.assemblers.ItemAssembler;
import ca.ulaval.glo2003.ebaie.inventory.entities.InventoryRepository;
import ca.ulaval.glo2003.ebaie.inventory.entities.Item;
import ca.ulaval.glo2003.ebaie.inventory.entities.ItemFactory;
import ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence.InMemoryInventoryRepositoryImp;
import ca.ulaval.glo2003.ebaie.offer.api.assemblers.OfferDtoAssembler;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OfferRequest;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OfferResponseOffer;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OffersResponse;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferCreationDto;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferDto;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.InMemorySellerRepositoryImp;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.BiddingEndedException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidAmountException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidBuyerIdException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class OfferDtoAssemblerTest {
    private final ItemFactory itemFactory = new ItemFactory();
    private final InventoryRepository inventoryRepository = new InMemoryInventoryRepositoryImp();
    private final ItemAssembler itemAssembler = new ItemAssembler();
    private final ItemUseCase itemUseCase = new ItemUseCase(itemFactory, inventoryRepository, itemAssembler);
    private final BuyerFactory buyerFactory = new BuyerFactory();
    private final BuyerRepository buyerRepository = new BuyerRepositoryImplementation();
    private final BuyerAssembler buyerAssembler = new BuyerAssembler();
    private final BuyerUseCase buyerUseCase = new BuyerUseCase(buyerFactory, buyerRepository, buyerAssembler);
    private final InMemorySellerRepositoryImp sellerRepository = new InMemorySellerRepositoryImp();

    private OfferDtoAssembler validOfferDtoAssembler;

    @Before
    public void SetUp() {
        Seller seller = new Seller(Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_SELLER_ID, Constant.CREATED_AT);
        sellerRepository.save(seller);
        Item item = new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION,
                Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);
        inventoryRepository.save(item);
        Buyer buyer = new Buyer(Constant.MY_NAME, Constant.MY_BIRTHDAY, Constant.MY_BUYER_ID);
        buyerRepository.save(buyer);
        validOfferDtoAssembler = new OfferDtoAssembler(itemUseCase, buyerUseCase);
    }

    @Test
    public void givenOfferRequest_whenCallingFromRequest_returnValidDto() {
        OfferRequest offerRequest = fillOfferRequestObject();

        OfferCreationDto offerCreationDto = validOfferDtoAssembler.fromRequest(offerRequest, Constant.MY_ITEM_ID.getValue());

        assertThat(offerCreationDto.amount).isEqualTo(Constant.MY_OFFER_AMOUNT);
        assertThat(offerCreationDto.buyerId).isEqualTo(Constant.MY_BUYER_ID);
    }

    @Test
    public void givenOfferRequest_whenCallingFromRequest_returnNotNullDtoObject() {
        OfferRequest offerRequest = fillOfferRequestObject();

        OfferCreationDto offerCreationDto = validOfferDtoAssembler.fromRequest(offerRequest, Constant.MY_ITEM_ID.getValue());

        assertThat(offerCreationDto).isNotNull();
    }

    @Test
    public void givenOfferDto_whenCallingToResponse_returnValidOfferResponseItem() {
        OfferDto offerDto = fillOfferDtoObject();

        OfferResponseOffer response = validOfferDtoAssembler.toResponse(offerDto);

        assertThat(response.id).isEqualTo((offerDto.id.getValue()));
    }

    @Test
    public void givenAListOfDto_whenCallingToResponse_thenReturnAnOfferResponse() {
        List<OfferDto> offerDtoList = fillOfferDtoArrayList();

        OffersResponse response = validOfferDtoAssembler.toResponse(offerDtoList);

        assertThat(response.offerList.get(0).createdAt).isEqualTo(offerDtoList.get(0).createdAt);
        assertThat(response.offerList.get(1).createdAt).isEqualTo(offerDtoList.get(1).createdAt);
    }

    @Test(expected = FieldEmptyException.class)
    public void givenAnInvalidBuyerId_whenCallingFromRequest_throwFieldEmptyException() {
        OfferRequest offerRequest = fillOfferRequestObject();
        offerRequest.buyerId = Constant.EMPTY_BUYER_ID;

        validOfferDtoAssembler.fromRequest(offerRequest, Constant.MY_ITEM_ID.getValue());
    }

    @Test(expected = InvalidBuyerIdException.class)
    public void givenAnInvalidBuyerId_whenCallingFromRequest_throwInvalidBuyerIdException() {
        OfferRequest offerRequest = fillOfferRequestObject();
        offerRequest.buyerId = Constant.INVALID_BUYER_ID.getValue();

        validOfferDtoAssembler.fromRequest(offerRequest, Constant.MY_ITEM_ID.getValue());
    }

    @Test(expected = InvalidAmountException.class)
    public void givenAnInvalidAmount_whenCallingFromRequest_throwInvalidAmountException() {
        OfferRequest offerRequest = fillOfferRequestObject();
        offerRequest.amount = Constant.INVALID_OFFER_AMOUNT.getAmount();

        validOfferDtoAssembler.fromRequest(offerRequest, Constant.MY_ITEM_ID.getValue());
    }

    @Test(expected = BiddingEndedException.class)
    public void givenAnInvalidProductEndTime_whenCallingFromRequest_throwBiddingEndedException() {
        saveExpiredBidding();
        OfferRequest offerRequest = fillOfferRequestObject();

        validOfferDtoAssembler.fromRequest(offerRequest, Constant.MY_ITEM_ID.getValue());
    }

    private List<OfferDto> fillOfferDtoArrayList() {
        List<OfferDto> offerDtoArrayList = new ArrayList<>();

        OfferDto offerDto = new OfferDto();
        OfferDto offerDto2 = new OfferDto();

        offerDto.id = Constant.OFFER_ID;
        offerDto.productId = Constant.MY_ITEM_ID;
        offerDto.buyerId = Constant.MY_BUYER_ID;
        offerDto.amount = Constant.MY_OFFER_AMOUNT;
        offerDto.createdAt = Constant.CREATED_AT;
        offerDto.biddingStatus = Constant.BIDDING_STATUS;

        offerDto2.id = Constant.OFFER_ID;
        offerDto2.productId = Constant.MY_ITEM_ID;
        offerDto2.buyerId = Constant.MY_BUYER_ID;
        offerDto2.amount = Constant.MY_NEW_OFFER_AMOUNT;
        offerDto2.createdAt = Constant.CREATED_AT;
        offerDto2.biddingStatus = Constant.BIDDING_STATUS;

        offerDtoArrayList.add(offerDto);
        offerDtoArrayList.add(offerDto2);

        return offerDtoArrayList;
    }

    private OfferRequest fillOfferRequestObject() {
        OfferRequest offerRequest = new OfferRequest();

        offerRequest.buyerId = Constant.MY_BUYER_ID.getValue();
        offerRequest.amount = Constant.MY_OFFER_AMOUNT.getAmount();

        return offerRequest;
    }

    private OfferDto fillOfferDtoObject() {
        OfferDto offerDto = new OfferDto();
        offerDto.id = Constant.OFFER_ID;
        offerDto.productId = Constant.MY_ITEM_ID;
        offerDto.buyerId = Constant.MY_BUYER_ID;
        offerDto.amount = Constant.MY_OFFER_AMOUNT;
        offerDto.createdAt = Constant.CREATED_AT;
        offerDto.biddingStatus = Constant.BIDDING_STATUS;

        return offerDto;
    }

    private void saveExpiredBidding() {
        Item item = new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION,
                Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.EXPIRED_START_TIME, Constant.MY_DURATION);
        inventoryRepository.save(item);
        Buyer buyer = new Buyer(Constant.MY_NAME, Constant.MY_BIRTHDAY, Constant.MY_BUYER_ID);
        buyerRepository.save(buyer);
        validOfferDtoAssembler = new OfferDtoAssembler(itemUseCase, buyerUseCase);
    }
}

