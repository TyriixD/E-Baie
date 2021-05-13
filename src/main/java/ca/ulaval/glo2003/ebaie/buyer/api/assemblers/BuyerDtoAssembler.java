package ca.ulaval.glo2003.ebaie.buyer.api.assemblers;

import ca.ulaval.glo2003.ebaie.buyer.api.dto.*;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerCreationDto;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerDto;
import ca.ulaval.glo2003.ebaie.inventory.application.ItemUseCase;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.offer.application.OfferUseCase;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferDto;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuyerDtoAssembler {
    public static int THIS_YEAR = 2021;
    private final OfferUseCase offerUseCase;
    private final ItemUseCase itemUseCase;

    public BuyerDtoAssembler(OfferUseCase offerUseCase, ItemUseCase itemUseCase) {
        this.offerUseCase = offerUseCase;
        this.itemUseCase = itemUseCase;
    }

    public BuyerCreationDto fromRequest(BuyerRequest request) {
        BuyerCreationDto dto = new BuyerCreationDto();
        if ((request.name == null || request.name.isEmpty())) {
            throw new FieldEmptyException("name should not be empty");
        }
        if ((request.birthDate == null || request.birthDate.isEmpty())) {
            throw new InvalidDateTimeException("Le champs de la date me doit pas etre vide ");
        }
        if (!checkDateFormat(request.birthDate)) {
            throw new InvalidDateTimeException("Le format de la date est invalide ");
        }
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(request.birthDate);
        if (THIS_YEAR - (localDate.getYear()) < 18) {
            throw new InvalidDateException("L'acheteur doit avoir 18 ans");
        }
        dto.name = request.name;
        dto.birthDate = localDate;

        return dto;
    }

    public BuyerResponseBuyer toResponse(BuyerDto buyerDto) {
        BuyerResponseBuyer response = new BuyerResponseBuyer();
        List<OfferDto> offers = offerUseCase.getAllOffers();
        response.id = buyerDto.id.getValue();
        response.name = buyerDto.name;
        response.biddingOffers = new ArrayList<>();
        response.obtainedProducts = new ArrayList<>();

        List<BuyerBiddingResponse> listeBiddding = new ArrayList<>();
        List<BuyerObtainedResponse> listeObtained = new ArrayList<>();

        for (OfferDto offerDto : offers) {
            if (offerDto.buyerId.getValue().equals(buyerDto.id.getValue())) {
                BuyerBiddingResponse responseBidding = toReponseBidding(offerDto);
                listeBiddding.add(responseBidding);
            }
        }

        for (OfferDto offerDto : offers) {
            if (offerDto.buyerId.getValue().equals(buyerDto.id.getValue()) && offerDto.biddingStatus.equals("ended")) {
                BuyerObtainedResponse responseObtained = toResponseObtained(offerDto);
                listeObtained.add(responseObtained);
            }
        }
        response.biddingOffers = listeBiddding;
        response.obtainedProducts = listeObtained;

        return response;
    }

    public BuyerResponse toResponse(List<BuyerDto> buyerDtos) {
        BuyerResponse response = new BuyerResponse();
        response.buyerList = buyerDtos.stream().map(this::toResponse).collect(Collectors.toList());

        return response;
    }

    public BuyerBiddingResponse toReponseBidding(OfferDto offerDto) {
        BuyerBiddingResponse response = new BuyerBiddingResponse();
        response.amount = offerDto.amount.getAmount();
        response.biddingStatus = offerDto.biddingStatus;
        response.createdAt = offerDto.createdAt.toString();
        response.productId = offerDto.id.getValue();

        return response;
    }

    public BuyerObtainedResponse toResponseObtained(OfferDto offerDto) {
        BuyerObtainedResponse response = new BuyerObtainedResponse();
        ItemDto item = itemUseCase.getItem(offerDto.productId.getValue());
        response.obtainedAt = item.startTime.plus(item.duration, ChronoUnit.DAYS).toString();
        response.productId = offerDto.id.getValue();

        return response;
    }

    private Boolean checkDateFormat(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


}
