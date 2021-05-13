package ca.ulaval.glo2003.ebaie.inventory.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ItemFactoryTest {
    public ItemFactory itemFactory = new ItemFactory();

    @Test
    public void givenValidParameter_WhenCallingCreate_ReturnValidItem() {
        Item item = itemFactory.create(Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION,
                Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);

        assertThat(item.getId()).isNotNull();
        assertThat(item.getSellerId()).isEqualTo(Constant.MY_SELLER_ID);
        assertThat(item.getName()).isEqualTo(Constant.MY_NAME);
        assertThat(item.getDescription()).isEqualTo(Constant.MY_DESCRIPTION);
        assertThat(item.getInitialPrice()).isEqualTo(Constant.MY_INITIAL_PRICE);
        assertThat(item.getCurrentPrice()).isEqualTo(Constant.MY_CURRENT_PRICE);
        assertThat(item.getStartTime()).isEqualTo(Constant.START_TIME);
        assertThat(item.getDuration()).isEqualTo(Constant.MY_DURATION);
    }
}