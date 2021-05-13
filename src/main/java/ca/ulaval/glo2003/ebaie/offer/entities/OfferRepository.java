package ca.ulaval.glo2003.ebaie.offer.entities;

import java.util.List;

public interface OfferRepository {
    List<Offer> findAll();

    Offer findById(String id);

    void save(Offer offer);

}
