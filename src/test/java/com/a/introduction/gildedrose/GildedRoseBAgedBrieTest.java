package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseBAgedBrieTest {

    public static final String ITEM_NAME = "Aged Brie";
    public static final int UNEXPIRED_SELL_IN = 4;
    public static final int DEFAULT_QUALITY = 3;
    public static final int EXPIRED_SELLIN = -1;
    public static final int QUALITY = 50;

    @Test
    public void shouldDecreaseSellinBy1AndIncreaseQualityBy1WhenUnexpiredSellinIsProvided() {
        GildedRose app = getGildedRose(ITEM_NAME, UNEXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(ITEM_NAME, UNEXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 1);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void shouldDecreaseSellinBy1AndIncreaseQualityBy2WhenExpiredSellinIsProvided() {
        GildedRose app = getGildedRose(ITEM_NAME, EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(ITEM_NAME, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void shouldDecreaseSellinBy1WhenUnexpiredSellinIsProvided() {
        GildedRose app = getGildedRose(ITEM_NAME, UNEXPIRED_SELL_IN, QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(ITEM_NAME, UNEXPIRED_SELL_IN - 1, QUALITY);
        assertItem(expectedItem, app.items[0]);
    }

    private static GildedRose getGildedRose(String itemName, int unexpiredSellIn, int quality) {
        Item item = new Item(itemName, unexpiredSellIn, quality);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        return app;
    }

    private static void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }
}
