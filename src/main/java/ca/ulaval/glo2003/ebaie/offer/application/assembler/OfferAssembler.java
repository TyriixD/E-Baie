package ca.ulaval.glo2003.ebaie.offer.application.assembler;


import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferDto;
import ca.ulaval.glo2003.ebaie.offer.entities.Offer;

import java.util.List;
import java.util.stream.Collectors;

public class OfferAssembler {
    public List<OfferDto> toDtos(List<Offer> offerList) {

        return offerList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public OfferDto toDto(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.id = offer.getId();
        dto.productId = offer.getProductId();
        dto.buyerId = offer.getBuyerId();
        dto.amount = offer.getAmount();
        dto.createdAt = offer.getCreatedAt();
        dto.biddingStatus = offer.getBiddingStatus();
        return dto;
    }

}
