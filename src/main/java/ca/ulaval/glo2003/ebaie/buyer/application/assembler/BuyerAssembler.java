package ca.ulaval.glo2003.ebaie.buyer.application.assembler;

import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerDto;
import ca.ulaval.glo2003.ebaie.buyer.entities.Buyer;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerAssembler {

    public List<BuyerDto> toDtos(List<Buyer> buyerList) {

        return buyerList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public BuyerDto toDto(Buyer buyer) {
        BuyerDto dto = new BuyerDto();
        dto.id = buyer.getId();
        dto.name = buyer.getName();
        dto.birthDate = buyer.getBirthDate();
        return dto;
    }
}
