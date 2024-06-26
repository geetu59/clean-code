package com.b.simple.design.business.text;

public class TextHelper {

    public String swapLastTwoCharacters(String str) {
        if (str.length() < 2) return str;
        char lastChar = str.charAt(str.length() - 1);
        char secondLastChar = str.charAt(str.length() - 2);
        String restOfString = str.substring(0, str.length() - 2);
        return restOfString + lastChar + secondLastChar;
    }

    public String truncateAInFirst2Positions(String str) {
        if (str.length() < 2) return str.replaceAll("A", "");
        String first2Characters = str.substring(0, 2);
        String updatedFirstTwoCharacters = first2Characters.replaceAll("A", "");
        String remainingString = str.substring(2, str.length());
        return updatedFirstTwoCharacters + remainingString;
    }
}
