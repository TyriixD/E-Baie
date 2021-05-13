package ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.inventory.entities.Item;
import ca.ulaval.glo2003.ebaie.valueobjects.ItemId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InventoryMongoDBAssembler {

    public Item fromDto(ItemDtoMongoDB mongoDto) {

        return new Item(
                new ItemId(mongoDto.itemId),
                new SellerId(mongoDto.sellerId),
                mongoDto.name,
                mongoDto.description,
                new PriceAmount(Double.parseDouble(mongoDto.initialPrice)),
                new PriceAmount(Double.parseDouble(mongoDto.currentPrice)),
                Instant.parse(mongoDto.startTime),
                Long.parseLong(mongoDto.duration)
        );
    }

    public List<Item> fromDtoList(List<ItemDtoMongoDB> itemDtoMongoDBList) {
        List<Item> itemList = new ArrayList<>();
        for (ItemDtoMongoDB itemDtoMongoDB : itemDtoMongoDBList) {
            itemList.add(fromDto(itemDtoMongoDB));
        }

        return itemList;
    }

    public ItemDtoMongoDB toDto(Item item) {
        ItemDtoMongoDB itemDtoMongoDB = new ItemDtoMongoDB();
        itemDtoMongoDB.itemId = item.getId().getValue();
        itemDtoMongoDB.sellerId = item.getSellerId().getValue();
        itemDtoMongoDB.name = item.getName();
        itemDtoMongoDB.description = item.getDescription();
        itemDtoMongoDB.initialPrice = Double.toString(item.getInitialPrice().getAmount());
        itemDtoMongoDB.currentPrice = Double.toString(item.getCurrentPrice().getAmount());
        itemDtoMongoDB.startTime = item.getStartTime().toString();
        itemDtoMongoDB.duration = Long.toString(item.getDuration());

        return itemDtoMongoDB;
    }
}
