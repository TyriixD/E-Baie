package ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.seller.api.SellerException.InvalidSellerIdException;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySellerRepositoryImp implements SellerRepository {
    private Map<String, Seller> sellerHashMap = new HashMap<>();

    @Override
    public Seller findById(String id) throws InvalidSellerIdException {
        for (Seller seller : sellerHashMap.values()) {
            if (seller.getId().getValue().equals(id)) {
                return seller;
            }
        }
        throw new InvalidSellerIdException("L'id du vendeur fournis n'existe pas");

    }

    @Override
    public List<Seller> findAll() {
        return new ArrayList<>(sellerHashMap.values());
    }

    @Override
    public void save(Seller seller) {
        sellerHashMap.put(seller.getId().getValue(), seller);
    }

    public void setSellerHashMap(Map<String, Seller> sellerHashMap) {
        this.sellerHashMap = sellerHashMap;
    }
}
