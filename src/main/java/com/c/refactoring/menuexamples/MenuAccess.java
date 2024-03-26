package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    /*Refactor:
    * 1. When role is not null then we do things, so move that condition upwards
    * 2. Use java streams fore
    * 3. Remove else if as both are diff statements
    * 4. See if roles has corresponding role for which we look for, move that if condition into method*/
    public void setAuthorizationsInEachMenus(List<MenuItem> menuItemsList, Role[] roles) {
        if (roles == null) return;

        menuItemsList.forEach(menuItem -> {
            if (hasRequiredAccessRole(roles, menuItem.getReadAccessRole())) {
                menuItem.setAccess(Constants.READ);
                menuItem.setVisible(true);
            }
            if (hasRequiredAccessRole(roles, menuItem.getWriteAccessRole())) {
                menuItem.setAccess(Constants.WRITE);
                menuItem.setVisible(true);
            }
        });

    }

    private static boolean hasRequiredAccessRole(Role[] roles, String roleToCheckFor) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(roleToCheckFor));
    }

}
