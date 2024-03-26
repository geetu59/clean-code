package com.d.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringHelperTest {
    StringHelper stringHelper = new StringHelper();

    //"ABCD" => "BCD", "AACD"=> "CD", "BACD"=>"BCD", "AAAA" => "AA", "MNAA"=>"MNAA" "" "A" "B" -> All test cases
    //since this is very simple, we will add asserts in one test only
    //follow red-> fail test green-> make it pass, refactor it then
    @Test
    void shouldRemoveAFromFirstTwoLettersOfString() {
        assertEquals("BCD", stringHelper.replaceAInFirst2Positions("ABCD"));
        assertEquals("CD", stringHelper.replaceAInFirst2Positions("AACD"));
        assertEquals("BCD", stringHelper.replaceAInFirst2Positions("BACD"));
        assertEquals("AA", stringHelper.replaceAInFirst2Positions("AAAA"));
        assertEquals("MNAA", stringHelper.replaceAInFirst2Positions("MNAA"));
        assertEquals("", stringHelper.replaceAInFirst2Positions(""));
        assertEquals("", stringHelper.replaceAInFirst2Positions("A"));
        assertEquals("B", stringHelper.replaceAInFirst2Positions("B"));
    }

    // ""=>false, "A"=>false, "AB"=>true, "ABC"=>false, "AAA"=>true, "ABCAB"=>true, "ABCDEBA"=>false
    @Test
    void shouldCheckIfFirstTwoAndLastTowCharactersAreSame() {
        assertFalse(stringHelper.areFirstTwoAndLastTwoCharsTheSame(""));
        assertFalse(stringHelper.areFirstTwoAndLastTwoCharsTheSame("A"));
        assertFalse(stringHelper.areFirstTwoAndLastTwoCharsTheSame("ABC"));
        assertFalse(stringHelper.areFirstTwoAndLastTwoCharsTheSame("ABCDEBA"));
        assertTrue(stringHelper.areFirstTwoAndLastTwoCharsTheSame("AB"));
        assertTrue(stringHelper.areFirstTwoAndLastTwoCharsTheSame("AAA"));
        assertTrue(stringHelper.areFirstTwoAndLastTwoCharsTheSame("ABCAB"));
    }
}
