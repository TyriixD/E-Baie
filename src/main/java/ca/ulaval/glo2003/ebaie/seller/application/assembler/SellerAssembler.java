package ca.ulaval.glo2003.ebaie.seller.application.assembler;


import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class SellerAssembler {
    public List<SellerDto> toDtos(List<Seller> sellerList) {

        return sellerList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public SellerDto toDto(Seller seller) {
        SellerDto dto = new SellerDto();
        dto.id = seller.getId();
        dto.name = seller.getName();
        dto.description = seller.getDescription();
        dto.createdAt = seller.getCreatedAt();
        return dto;
    }

}
