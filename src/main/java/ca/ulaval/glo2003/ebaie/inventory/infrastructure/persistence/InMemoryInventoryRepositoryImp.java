package ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductIdException;
import ca.ulaval.glo2003.ebaie.inventory.entities.InventoryRepository;
import ca.ulaval.glo2003.ebaie.inventory.entities.Item;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryInventoryRepositoryImp implements InventoryRepository {

    private Map<String, Item> itemHashMap = new HashMap<>();

    @Override
    public Item findById(String id) {
        if (itemHashMap.isEmpty()) {
            throw new InvalidProductIdException("l'id du produit fourni n'existe pas");
        } else {
            for (Item item : itemHashMap.values()) {
                if (item.getId().getValue().equals(id)) {
                    return item;
                }
            }
            throw new InvalidProductIdException("L'id du produit fourni n'existe pas");
        }
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(itemHashMap.values());
    }

    @Override
    public void save(Item item) {
        itemHashMap.put(item.getId().getValue(), item);
    }

    @Override
    public void updateCurrentPrice(String itemId, double amount){
        Item item = findById(itemId);
        item.setCurrentPrice(new PriceAmount(amount));
        save(item);
    }

    public void setItemHashMap(Map<String, Item> itemMap) {
        this.itemHashMap = itemMap;
    }
}
