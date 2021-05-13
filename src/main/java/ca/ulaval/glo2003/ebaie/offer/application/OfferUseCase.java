package ca.ulaval.glo2003.ebaie.offer.application;

import ca.ulaval.glo2003.ebaie.offer.application.assembler.OfferAssembler;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferCreationDto;
import ca.ulaval.glo2003.ebaie.offer.application.dto.OfferDto;
import ca.ulaval.glo2003.ebaie.offer.entities.Offer;
import ca.ulaval.glo2003.ebaie.offer.entities.OfferFactory;
import ca.ulaval.glo2003.ebaie.offer.entities.OfferRepository;

import java.util.List;

public class OfferUseCase {
    private final OfferFactory offerFactory;
    private final OfferRepository offerRepository;
    private final OfferAssembler offerAssembler;

    public OfferUseCase(OfferFactory offerFactory, OfferRepository offerRepository,
                        OfferAssembler offerAssembler) {
        this.offerFactory = offerFactory;
        this.offerRepository = offerRepository;
        this.offerAssembler = offerAssembler;
    }

    public String createOffer(OfferCreationDto dto) {
        Offer offer = offerFactory.create(dto.productId, dto.buyerId, dto.amount, dto.createdAt, dto.biddingStatus);
        offerRepository.save(offer);
        return offer.getId().getValue();
    }

    public List<OfferDto> getAllOffers() {
        List<Offer> offerList = offerRepository.findAll();
        return offerAssembler.toDtos(offerList);
    }

    public OfferDto getOffer(String id) {
        Offer offer = offerRepository.findById(id);

        return offerAssembler.toDto(offer);
    }

    public List<Offer> getOffers() {
        List<Offer> offerList = offerRepository.findAll();
        return offerList;
    }
}
