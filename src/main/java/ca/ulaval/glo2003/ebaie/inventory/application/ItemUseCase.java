package ca.ulaval.glo2003.ebaie.inventory.application;

import ca.ulaval.glo2003.ebaie.inventory.application.assemblers.ItemAssembler;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemCreationDto;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.inventory.entities.InventoryRepository;
import ca.ulaval.glo2003.ebaie.inventory.entities.Item;
import ca.ulaval.glo2003.ebaie.inventory.entities.ItemFactory;
import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;

import java.util.List;

public class ItemUseCase {
    private final ItemFactory itemFactory;
    private final InventoryRepository inventoryRepository;
    private final ItemAssembler itemAssembler;

    public ItemUseCase(ItemFactory itemFactory, InventoryRepository inventoryRepository,
                       ItemAssembler itemAssembler) {
        this.itemFactory = itemFactory;
        this.inventoryRepository = inventoryRepository;
        this.itemAssembler = itemAssembler;
    }

    public ItemId createItem(ItemCreationDto dto) {
        Item item = itemFactory
                .create(dto.sellerId, dto.name, dto.description, dto.initialPrice, dto.currentPrice,
                        dto.startTime,
                        dto.duration);
        inventoryRepository.save(item);
        return item.getId();
    }

    public void updateCurrentPrice(String productId, double amount) {
        inventoryRepository.updateCurrentPrice(productId, amount);
    }

    public List<ItemDto> getAllItems() {
        List<Item> itemList = inventoryRepository.findAll();
        return itemAssembler.toDtos(itemList);
    }

    public ItemDto getItem(String id) {
        Item item = inventoryRepository.findById(id);

        return itemAssembler.toDto(item);
    }

}
