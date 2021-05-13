package ca.ulaval.glo2003.ebaie.inventory.application;

import ca.ulaval.glo2003.ebaie.Constant;
import ca.ulaval.glo2003.ebaie.inventory.application.assemblers.ItemAssembler;
import ca.ulaval.glo2003.ebaie.inventory.application.dtos.ItemDto;
import ca.ulaval.glo2003.ebaie.inventory.entities.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ItemAssemblerTest {
    ItemAssembler itemAssembler = new ItemAssembler();

    @Test
    public void givenItem_whenCallingtoDto_returnValidDtoObject() {
        Item item = fillItemObject();

        ItemDto itemDto = itemAssembler.toDto(item);

        assertThat(itemDto.id).isEqualTo(item.getId());
        assertThat(itemDto.name).isEqualTo(item.getName());
        assertThat(itemDto.sellerId).isEqualTo(item.sellerId);
        assertThat(itemDto.description).isEqualTo(item.getDescription());
        assertThat(itemDto.initialPrice).isEqualTo(item.getInitialPrice());
        assertThat(itemDto.currentPrice).isEqualTo(item.getCurrentPrice());
        assertThat(itemDto.startTime).isEqualTo(item.getStartTime());
        assertThat(itemDto.duration).isEqualTo(item.getDuration());
    }

    @Test
    public void givenAListOfItem_whenCallingToDtos_returnAValidListOfDtos() {
        List<Item> itemList = fillItemArrayList();

        List<ItemDto> itemDtos = itemAssembler.toDtos(itemList);

        assertThat(itemDtos.size()).isEqualTo(2);
    }

    private Item fillItemObject() {

        return new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION,
                Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);
    }

    private List<Item> fillItemArrayList() {
        List<Item> itemArrayList = new ArrayList<>();

        Item item1 = new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION,
                Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);
        Item item2 = new Item(Constant.MY_NEW_ITEM_ID, Constant.MY_NEW_SELLER_ID, Constant.MY_NEW_NAME,
                Constant.MY_NEW_DESCRIPTION, Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE,
                Constant.START_TIME, Constant.MY_NEW_DURATION);

        itemArrayList.add(item1);
        itemArrayList.add(item2);

        return itemArrayList;
    }
}
