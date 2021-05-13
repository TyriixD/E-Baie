package ca.ulaval.glo2003.ebaie.inventory.application.assemblers;

import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.inventory.entities.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemAssembler {
    public List<ItemDto> toDtos(List<Item> itemList) {

        return itemList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ItemDto toDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.id = item.getId();
        dto.name = item.getName();
        dto.sellerId = item.getSellerId();
        dto.description = item.getDescription();
        dto.initialPrice = item.getInitialPrice();
        dto.currentPrice = item.getCurrentPrice();
        dto.startTime = item.getStartTime();
        dto.duration = item.getDuration();

        return dto;
    }
}
