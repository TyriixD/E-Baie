package ca.ulaval.glo2003.ebaie.offer.api.assemblers;

import ca.ulaval.glo2003.ebaie.buyer.application.BuyerUseCase;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerDto;
import ca.ulaval.glo2003.ebaie.inventory.application.ItemUseCase;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OfferRequest;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OfferResponseOffer;
import ca.ulaval.glo2003.ebaie.offer.api.dtos.OffersResponse;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferCreationDto;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferDto;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.BiddingEndedException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidAmountException;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class OfferDtoAssembler {
    private final ItemUseCase itemUseCase;
    private final BuyerUseCase buyerUseCase;

    public OfferDtoAssembler(ItemUseCase itemUseCase, BuyerUseCase buyerUseCase) {
        this.itemUseCase = itemUseCase;
        this.buyerUseCase = buyerUseCase;
    }

    public OfferCreationDto fromRequest(OfferRequest request, String productId) throws RuntimeException {
        OfferCreationDto dto = new OfferCreationDto();
        if (request.buyerId == null || request.buyerId.isEmpty() || request.amount == 0.0) {
            throw new FieldEmptyException(
                    "le buyerId ou l'amount ne devraient pas être vides");
        }
        ItemDto itemDto = itemUseCase.getItem(productId);
        if (request.amount < itemDto.currentPrice.getAmount()) {
            throw new InvalidAmountException("l'amount fourni n'est pas assez élevé");
        }

        BuyerDto buyerDto = buyerUseCase.getBuyerById(request.buyerId);
        Instant productEndTime = itemDto.startTime.plus(itemDto.duration, ChronoUnit.DAYS);

        Instant nowInstant = Instant.now();

        if (nowInstant.compareTo(productEndTime) < 0) {
            dto.biddingStatus = "ongoing";
        } else {
            throw new BiddingEndedException("Enchère terminée");
        }

        itemUseCase.updateCurrentPrice(productId, request.amount);

        dto.productId = itemDto.id;
        dto.buyerId = buyerDto.id;
        dto.amount = new PriceAmount(request.amount);
        dto.createdAt = nowInstant;

        return dto;
    }

    public OfferResponseOffer toResponse(OfferDto offerDto) {
        OfferResponseOffer response = new OfferResponseOffer();
        response.id = offerDto.id.getValue();
        response.productId = offerDto.productId.getValue();
        response.buyerId = offerDto.buyerId.getValue();
        response.amount = offerDto.amount.getAmount();
        response.createdAt = offerDto.createdAt;
        response.biddingStatus = offerDto.biddingStatus;

        return response;
    }

    public OffersResponse toResponse(List<OfferDto> offerDtos) {
        OffersResponse response = new OffersResponse();
        response.offerList = offerDtos.stream().map(this::toResponse).collect(Collectors.toList());

        return response;
    }
}


