package ca.ulaval.glo2003.ebaie.seller.application;

import ca.ulaval.glo2003.ebaie.seller.application.assembler.SellerAssembler;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerCreationDto;
import ca.ulaval.glo2003.ebaie.seller.application.dto.SellerDto;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerFactory;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerRepository;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.util.List;

public class SellerUseCase {
    private final SellerFactory sellerFactory;
    private final SellerRepository sellerRepository;
    private final SellerAssembler sellerAssembler;

    public SellerUseCase(SellerFactory sellerFactory, SellerRepository sellerRepository,
                         SellerAssembler sellerAssembler) {
        this.sellerFactory = sellerFactory;
        this.sellerRepository = sellerRepository;
        this.sellerAssembler = sellerAssembler;
    }

    public SellerId createSeller(SellerCreationDto dto) {
        Seller seller = sellerFactory.create(dto.name, dto.description, dto.createdAt);
        sellerRepository.save(seller);

        return seller.getId();
    }

    public List<SellerDto> getAllSellers() {
        List<Seller> sellerList = sellerRepository.findAll();

        return sellerAssembler.toDtos(sellerList);
    }

    public SellerDto getSeller(String id) {
        Seller seller = sellerRepository.findById(id);

        return sellerAssembler.toDto(seller);
    }

}
