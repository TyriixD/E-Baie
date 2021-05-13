package ca.ulaval.glo2003.ebaie.seller.entities;

import java.util.List;

public interface SellerRepository {
    List<Seller> findAll();

    Seller findById(String id);

    void save(Seller seller);

}
