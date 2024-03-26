package com.c.refactoring.menuexamples;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuAccessTest {
    public static final MenuItem MENU_ITEM = new MenuItem("A", "MenuARead", "MenuAWrite");
    public static final Role MENU_A_READ = new Role("MenuARead");
    public static final Role MENU_A_WRITE = new Role("MenuAWrite");
    MenuAccess menuAccess = new MenuAccess();

    @Test
    public void testSetAuthorizationsInEachMenusWhenUseHasOnlyReadRole() {
        Role[] userRoles = {MENU_A_READ};
        List<MenuItem> menuItems = Collections.singletonList(MENU_ITEM);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertIsReadable(menuItems.get(0));
    }

    @Test
    public void testSetAuthorizationsInEachMenusWhenUseHasOnlyWriteRole() {
        Role[] userRoles = {MENU_A_WRITE};
        List<MenuItem> menuItems = Collections.singletonList(MENU_ITEM);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertIsWritable(menuItems.get(0));
    }

    @Test
    public void testSetAuthorizationsInEachMenusWhenUseHasReadAndWriteRole() {
        Role[] userRoles = {MENU_A_READ, MENU_A_WRITE};
        List<MenuItem> menuItems = Collections.singletonList(MENU_ITEM);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertIsWritable(menuItems.get(0));
    }

    private static void assertIsWritable(MenuItem menuItem) {
        assertEquals(Constants.WRITE, menuItem.getAccess());
        assertTrue(menuItem.isVisible());
    }

    private static void assertIsReadable(MenuItem menuItem) {
        assertEquals(Constants.READ, menuItem.getAccess());
        assertTrue(menuItem.isVisible());
    }
}
