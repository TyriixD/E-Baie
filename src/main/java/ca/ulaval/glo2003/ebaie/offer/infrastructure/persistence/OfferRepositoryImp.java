package ca.ulaval.glo2003.ebaie.offer.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.offer.api.OfferException.InvalidOfferIdException;
import ca.ulaval.glo2003.ebaie.offer.entities.Offer;
import ca.ulaval.glo2003.ebaie.offer.entities.OfferRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferRepositoryImp implements OfferRepository {
    private Map<String, Offer> offerHashMap = new HashMap<>();

    @Override
    public Offer findById(String id) throws InvalidOfferIdException {
        for (Offer offer : offerHashMap.values()) {
            if (offer.getId().getValue().equals(id)) {
                return offer;
            }
        }
        throw new InvalidOfferIdException("L'id de l'offre fourni n'existe pas");
    }

    @Override
    public List<Offer> findAll() {
        return new ArrayList<>(offerHashMap.values());
    }

    @Override
    public void save(Offer offer) {
        offerHashMap.put(offer.getId().getValue(), offer);

    }

    public void setOfferHashMap(Map<String, Offer> offerHashMap) {
        this.offerHashMap = offerHashMap;
    }
}
