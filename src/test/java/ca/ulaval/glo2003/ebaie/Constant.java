package ca.ulaval.glo2003.ebaie;

import ca.ulaval.glo2003.ebaie.valueobjects.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Constant {
    public static final SellerId MY_SELLER_ID = new SellerId("52");
    public static final PriceAmount MY_INITIAL_PRICE = new PriceAmount(50);
    public static final PriceAmount INVALID_PRICE = new PriceAmount(0);
    public static final PriceAmount INVALID_OFFER_AMOUNT = new PriceAmount(1);
    public static final PriceAmount MY_CURRENT_PRICE = new PriceAmount(50);
    public static final PriceAmount MY_OFFER_AMOUNT = new PriceAmount(60);
    public static final PriceAmount MY_NEW_OFFER_AMOUNT = new PriceAmount(70);
    public static final String MY_NAME = "name";
    public static final String MY_NEW_DESCRIPTION = "Description";
    public static final String MY_NEW_NAME = "newName";
    public static final long MY_DURATION = 2;
    public static final long INVALID_DURATION = 32;
    public static final String EMPTY_DATE_FIELD = "";
    public static final String BAD_DATE_FIELD = "1234";
    public static final long MY_NEW_DURATION = 10;
    public static final String EMPTY_NAME = "";
    public static final String EMPTY_SELLER_ID = null;
    public static final String EMPTY_DESCRIPTION = "";
    public static final Instant START_TIME = Instant.now();
    public static final Instant EXPIRED_START_TIME = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse("2020-01-01T00:00:00-02:00", Instant::from);
    public static final String INVALID_START_TIME = "";
    public static final LocalDate MY_BIRTHDAY = LocalDate.of(2000, 1, 30);
    public static final LocalDate INVALID_BIRTHDAY = LocalDate.of(2017, 1, 30);
    public static final Instant CREATED_AT = Instant.now();
    public static final String MY_DESCRIPTION = "myDescription";
    public static final ItemId MY_ITEM_ID = new ItemId("2");
    public static final ItemId MY_NEW_ITEM_ID = new ItemId("1");
    public static final BuyerId MY_BUYER_ID = new BuyerId("ID");
    public static final BuyerId INVALID_BUYER_ID = new BuyerId("hello");
    public static final String EMPTY_BUYER_ID = null;
    public static final BuyerId MY_NEW_BUYER_ID = new BuyerId("ID2");
    public static final SellerId MY_NEW_SELLER_ID = new SellerId("85");
    public static final ItemId INVALID_ID = new ItemId("Pizza");
    public static final BuyerId INV_ID = new BuyerId("Pizza");
    public static final OfferId OFFER_ID = new OfferId("1234");
    public static final OfferId NEW_OFFER_ID = new OfferId("1235664");
    public static final String BIDDING_STATUS = "ongoing";
    public static final ReviewId REVIEW_ID = new ReviewId("id1");
    public static final ReviewId NEW_REVIEW_ID = new ReviewId("id2");
    public static final String REVIEW_TITLE = "Title";
    public static final String REVIEW_DESCRIPTION = "Description";
    public static final Integer REVIEW_RATING = 5;
    public static final ReviewId INVALID_REVIEW_ID = new ReviewId("1");
}
