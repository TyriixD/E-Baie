package ca.ulaval.glo2003.ebaie.buyer.infrastructure.persistance;

import ca.ulaval.glo2003.ebaie.buyer.entities.Buyer;
import ca.ulaval.glo2003.ebaie.buyer.entities.BuyerRepository;
import ca.ulaval.glo2003.ebaie.shared.api.SharedExceptions.InvalidBuyerIdException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerRepositoryImplementation implements BuyerRepository {
    private Map<String, Buyer> buyerHashMap = new HashMap<>();

    @Override
    public void save(Buyer buyer) {
        buyerHashMap.put(buyer.getId().getValue(), buyer);

    }

    @Override
    public List<Buyer> findAll() {
        return new ArrayList<>(buyerHashMap.values());
    }

    @Override
    public Buyer findById(String id) {
        for (Buyer buyer : buyerHashMap.values()) {
            if ((buyer.getId().getValue()).equals(id)) {
                return buyer;
            }
        }
        throw new InvalidBuyerIdException("L'id de l'acheteur est inexistant");
    }

    public void setBuyerHashMap(Map<String, Buyer> buyerHashMap) {
        this.buyerHashMap = buyerHashMap;
    }
}
