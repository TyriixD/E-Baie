package ca.ulaval.glo2003.ebaie.inventory.entities;

import ca.ulaval.glo2003.ebaie.Constant;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ItemTest {
    private Item item;

    @Before
    public void setup() {
        item = new Item(Constant.MY_ITEM_ID, Constant.MY_SELLER_ID, Constant.MY_NAME, Constant.MY_DESCRIPTION, Constant.MY_INITIAL_PRICE, Constant.MY_CURRENT_PRICE, Constant.START_TIME, Constant.MY_DURATION);

    }

    @Test
    public void givenItemObject_WhenCallingGetIDFunction_ReturnIfIdIsEqual() {

        assertThat(item.getId()).isEqualTo(Constant.MY_ITEM_ID);
    }

    @Test
    public void givenItemObject_WhenCallingGetNameFunction_ReturnIfNameIsEqual() {
        assertThat(item.getName()).isEqualTo(Constant.MY_NAME);
    }

    @Test
    public void givenItemObject_WhenCallingGetSellerIdFunction_ReturnIfSellerIdIsEqual() {
        assertThat(item.getSellerId()).isEqualTo(Constant.MY_SELLER_ID);
    }

    @Test
    public void givenItemObject_WhenCallingGetDescriptionFunction_ReturnIfDescriptionIsEqual() {
        assertThat(item.getDescription()).isEqualTo(Constant.MY_DESCRIPTION);
    }

    @Test
    public void givenItemObject_WhenCallingGetInitialPriceFunction_ReturnIfInitialPriceIsEqual() {
        assertThat(item.getInitialPrice()).isEqualTo(Constant.MY_INITIAL_PRICE);
    }

    @Test
    public void givenItemObject_WhenCallingGetCurrentPriceFunction_ReturnIfCurrentPriceIsEqual() {
        assertThat(item.getCurrentPrice()).isEqualTo(Constant.MY_CURRENT_PRICE);
    }

    @Test
    public void givenItemObject_WhenCallingGetStartTimeFunction_ReturnIfStartTimeIsEqual() {
        assertThat(item.getStartTime()).isEqualTo(Constant.START_TIME);
    }

    @Test
    public void givenItemObject_WhenCallingGetDurationFunction_ReturnIfDurationIsEqual() {
        assertThat(item.getDuration()).isEqualTo(Constant.MY_DURATION);
    }
}