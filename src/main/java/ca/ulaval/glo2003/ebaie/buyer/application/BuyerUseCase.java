package ca.ulaval.glo2003.ebaie.buyer.application;

import ca.ulaval.glo2003.ebaie.buyer.application.assembler.BuyerAssembler;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerCreationDto;
import ca.ulaval.glo2003.ebaie.buyer.application.dto.BuyerDto;
import ca.ulaval.glo2003.ebaie.buyer.entities.Buyer;
import ca.ulaval.glo2003.ebaie.buyer.entities.BuyerFactory;
import ca.ulaval.glo2003.ebaie.buyer.entities.BuyerRepository;
import ca.ulaval.glo2003.ebaie.valueobjects.BuyerId;

public class BuyerUseCase {
    BuyerFactory buyerFactory;
    BuyerRepository buyerRepository;
    BuyerAssembler buyerAssembler;

    public BuyerUseCase(BuyerFactory buyerFactory, BuyerRepository buyerRepository, BuyerAssembler buyerAssembler) {
        this.buyerFactory = buyerFactory;
        this.buyerRepository = buyerRepository;
        this.buyerAssembler = buyerAssembler;
    }

    public BuyerId createBuyer(BuyerCreationDto buyerCreationDto) {
        Buyer buyer = buyerFactory.create(buyerCreationDto.name, buyerCreationDto.birthDate);
        buyerRepository.save(buyer);

        return buyer.getId();
    }

    public BuyerDto getBuyerById(String id) {
        Buyer buyer = buyerRepository.findById(id);

        return buyerAssembler.toDto(buyer);
    }
}
