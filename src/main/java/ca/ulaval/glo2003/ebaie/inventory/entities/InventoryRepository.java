package ca.ulaval.glo2003.ebaie.inventory.entities;

import java.util.List;

public interface InventoryRepository {
    List<Item> findAll();

    Item findById(String id);

    void save(Item item);

    void updateCurrentPrice(String itemId, double amount);

}
