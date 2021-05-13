package ca.ulaval.glo2003.ebaie.buyer.entities;

import java.util.List;

public interface BuyerRepository {
    void save(Buyer buyer);

    Buyer findById(String id);

    List<Buyer> findAll();
}
