package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseCBackstagePassesTest {

    public static final String ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SELL_IN_1 = 15;
    public static final int QUALITY = 3;
    public static final int SELL_IN_2 = 7;
    public static final int SELL_IN_3 = 4;

    @Test
    public void shouldDecreaseSellinBy1AndIncreaseQualityBy1WhenUnexpiredSellinIsProvided() {
        GildedRose app = getGildedRose(ITEM_NAME, SELL_IN_1, QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(ITEM_NAME, SELL_IN_1 - 1, QUALITY + 1);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void shouldDecreaseSellinBy1AndIncreaseQualityBy2WhenSellinIsProvided() {
        GildedRose app = getGildedRose(ITEM_NAME, SELL_IN_2, QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(ITEM_NAME, SELL_IN_2 - 1, QUALITY + 2);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void shouldDecreaseSellinBy1AndIncreaseQualityBy3WhenSellinIsProvided() {
        GildedRose app = getGildedRose(ITEM_NAME, SELL_IN_3, QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(ITEM_NAME, SELL_IN_3 - 1, QUALITY + 3);
        assertItem(expectedItem, app.items[0]);
    }

    private static void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }

    private static GildedRose getGildedRose(String itemName, int sellIn1, int quality) {
        Item item = new Item(itemName, sellIn1, quality);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        return app;
    }

}