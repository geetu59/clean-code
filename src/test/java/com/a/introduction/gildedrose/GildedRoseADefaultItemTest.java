package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseADefaultItemTest {

    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int UN_EXPIRED_SELLIN = 15;
    public static final int DEFAULT_QUALITY = 3;

    @Test
    public void shouldDecreaseSellInAndQualityBy1WhenUnExpiredDefaultItemIsProvided() {
        GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, UN_EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        Item actualItem = app.items[0];
        Item expectedItem = new Item(DEFAULT_ITEM, UN_EXPIRED_SELLIN, DEFAULT_QUALITY);

        assertItem(expectedItem, actualItem);
    }

    private static void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn - 1, actualItem.sellIn);
        assertEquals(expectedItem.quality - 1, actualItem.quality);
    }

    private GildedRose getGildedRoseWithOneItem(String defaultItem, int unExpiredSellin, int defaultQuality) {
        Item item = new Item(defaultItem, unExpiredSellin, defaultQuality);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        return app;
    }

    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
     */
    @Test
    public void testUpdateQualityForExpiredItem() {
        Item item = new Item("DEFAULT_ITEM", -1, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("DEFAULT_ITEM", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }
}