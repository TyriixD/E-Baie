package ca.ulaval.glo2003.ebaie.inventory.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductIdException;
import ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence.InMemoryInventoryRepositoryImp;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class ItemRepositoryTest {
    public InMemoryInventoryRepositoryImp inventoryRepositoryImp = new InMemoryInventoryRepositoryImp();

    @Test
    public void givenAnItemToAdd_whenCallingSaveMethod_ReturnTheRightItemInMemory() {
        Item item = new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);
        inventoryRepositoryImp.save(item);

        List<Item> itemList = inventoryRepositoryImp.findAll();

        assertThat(itemList.size()).isEqualTo(1);
        assertThat(inventoryRepositoryImp.findById(Constant.MY_ITEM_ID.getValue())).isEqualTo(item);
    }

    @Test
    public void givenAnItemMap_WhenCallingFindAllMethod_ReturnAllTheItems() {
        Map<String, Item> itemMap = fillItemHashMap();
        inventoryRepositoryImp.setItemHashMap(itemMap);

        List<Item> itemList = inventoryRepositoryImp.findAll();

        assertThat(itemList.get(0)).isEqualTo(itemMap.get("1"));
        assertThat(itemList.get(1)).isEqualTo(itemMap.get("2"));
        assertThat(itemList.size()).isEqualTo(2);
    }

    @Test
    public void givenAnItemMap_WhenCallingFindBy_ReturnListWithItem() {
        Map<String, Item> itemMap = fillItemHashMap();
        inventoryRepositoryImp.setItemHashMap(itemMap);

        Item item = inventoryRepositoryImp.findById(Constant.MY_ITEM_ID.getValue());

        assertThat(item.getName()).isEqualTo(Constant.MY_NAME);
        assertThat(item.getDescription()).isEqualTo(Constant.MY_DESCRIPTION);
    }

    @Test
    public void givenAnItemMap_WhenCallingUpdateCurrentPrice_ReturnAnUpdatedItem() {
        Map<String, Item> itemMap = fillItemHashMap();
        inventoryRepositoryImp.setItemHashMap(itemMap);

        inventoryRepositoryImp.updateCurrentPrice(Constant.MY_ITEM_ID.getValue(), Constant.MY_NEW_OFFER_AMOUNT.getAmount());

        Item updated_item = inventoryRepositoryImp.findById(Constant.MY_ITEM_ID.getValue());
        assertThat(updated_item.getCurrentPrice()).isEqualTo(Constant.MY_NEW_OFFER_AMOUNT);
    }

    @Test(expected = InvalidProductIdException.class)
    public void givenANonExistingId_WhenCallingFindBy_ThrowAnException() {
        Map<String, Item> itemMap = fillItemHashMap();

        inventoryRepositoryImp.setItemHashMap(itemMap);

        inventoryRepositoryImp.findById(Constant.INVALID_ID.toString());
    }

    private Map<String, Item> fillItemHashMap() {
        Map<String, Item> itemHashMap = new HashMap<>();

        Item item = new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);
        Item item2 = new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);

        itemHashMap.put("1", item);
        itemHashMap.put("2", item2);

        return itemHashMap;

    }

}
