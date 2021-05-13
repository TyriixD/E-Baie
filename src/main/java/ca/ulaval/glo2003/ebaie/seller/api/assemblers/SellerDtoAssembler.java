package ca.ulaval.glo2003.ebaie.seller.api.assemblers;

import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerRequest;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerResponse;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.SellerResponseSeller;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerCreationDto;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.FieldEmptyException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class SellerDtoAssembler {
    public SellerCreationDto fromRequest(SellerRequest request) throws RuntimeException {

        SellerCreationDto dto = new SellerCreationDto();
        if ((request.name == null || request.name.isEmpty()) ||
                (request.description == null || request.description.isEmpty())) {
            throw new FieldEmptyException("name or description should not be empty");
        }
        dto.name = request.name;
        dto.description = request.description;
        dto.createdAt = Instant.now();

        return dto;
    }

    public SellerResponseSeller toResponse(SellerDto sellerDto) {
        SellerResponseSeller response = new SellerResponseSeller();
        response.id = sellerDto.id.getValue();
        response.name = sellerDto.name;
        response.description = sellerDto.description;
        response.createdAt = sellerDto.createdAt;

        return response;
    }

    public SellerResponse toResponse(List<SellerDto> sellerDtos) {
        SellerResponse response = new SellerResponse();
        response.sellerList = sellerDtos.stream().map(this::toResponse).collect(Collectors.toList());

        return response;
    }
}


