package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseADefaultItemTest {

    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int UN_EXPIRED_SELLIN = 15;
    public static final int DEFAULT_QUALITY = 3;
    public static final int EXPIRED_SELLIN = -1;
    public static final int QUALITY = 3;

    @Test
    public void shouldDecreaseSellInAndQualityBy1WhenUnExpiredDefaultItemIsProvided() {
        GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, UN_EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        Item actualItem = app.items[0];
        Item expectedItem = new Item(DEFAULT_ITEM, UN_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);

        assertItem(expectedItem, actualItem);
    }

    private static void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }

    private GildedRose getGildedRoseWithOneItem(String defaultItem, int unExpiredSellin, int defaultQuality) {
        Item item = new Item(defaultItem, unExpiredSellin, defaultQuality);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        return app;
    }

    @Test
    public void shouldDecreaseSellInBy1AndQualityBy2WhenExpiredDefaultItemIsProvided() {
        GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELLIN, QUALITY);

        app.updateQuality();

        Item actualItem = app.items[0];
        Item expectedItem = new Item(DEFAULT_ITEM, EXPIRED_SELLIN - 1, QUALITY - 2);

        assertItem(expectedItem, actualItem);
    }
}