package ca.ulaval.glo2003.ebaie.inventory.api;

import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductDurationException;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductInitialPriceException;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemRequest;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemResponseItem;
import ca.ulaval.glo2003.ebaie.inventory.api.dtos.ItemsResponse;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemCreationDto;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.seller.application.SellerUseCase;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidDateTimeException;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class ItemDtoAssembler {
    private final SellerUseCase sellerUseCase;

    public ItemDtoAssembler(SellerUseCase sellerUseCase) {
        this.sellerUseCase = sellerUseCase;
    }

    public ItemCreationDto fromRequest(ItemRequest request) {
        ItemCreationDto dto = new ItemCreationDto();
        if ((request.sellerId == null || request.sellerId.isEmpty())
                || (request.name == null || request.name.isEmpty())
                || (request.description == null || request.description.isEmpty())) {
            throw new FieldEmptyException(
                    "le sellerId, le name ou la description ne devraient pas être vides");
        }
        if (request.initialPrice < (1)) {
            throw new InvalidProductInitialPriceException("Le prix initial fourni est invalide");
        }
        if (request.duration < 1 || request.duration > 31) {
            throw new InvalidProductDurationException("La durée fournie est invalide");
        }
        if (!checkStartTime(request.startTime)) {
            throw new InvalidDateTimeException("le format de la date est invalide");
        }

        SellerDto sellerDto = sellerUseCase.getSeller(request.sellerId);
        dto.sellerId = sellerDto.id;
        dto.name = request.name;
        dto.description = request.description;
        dto.initialPrice = new PriceAmount(request.initialPrice);
        dto.currentPrice = new PriceAmount(request.initialPrice);
        dto.startTime =
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(request.startTime, Instant::from);
        dto.duration = request.duration;

        return dto;
    }

    public ItemResponseItem toResponse(ItemDto itemDto) {
        ItemResponseItem response = new ItemResponseItem();
        response.id = itemDto.id.getValue();
        response.name = itemDto.name;
        response.sellerId = itemDto.sellerId.getValue();
        response.description = itemDto.description;
        response.initialPrice = itemDto.initialPrice.arrondir(itemDto.initialPrice.getAmount());
        response.currentPrice = itemDto.initialPrice.arrondir(itemDto.currentPrice.getAmount());
        // might be updated later for PUT request
        response.startTime = itemDto.startTime;
        Instant endTime = response.startTime.plus(itemDto.duration, ChronoUnit.DAYS);
        response.endTime = endTime.toString();

        return response;
    }

    public ItemsResponse toResponse(List<ItemDto> itemDtos) {
        ItemsResponse response = new ItemsResponse();
        response.itemList = itemDtos.stream().map(this::toResponse).collect(Collectors.toList());

        return response;
    }

    private boolean checkStartTime(String startTime) {
        try {
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(startTime, Instant::from);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
