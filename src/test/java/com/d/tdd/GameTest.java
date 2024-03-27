package com.d.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    static NewGame game = new NewGame();

    /*TCs:
     * 1. All 0s
     * 2. All 1s
     * 3. 10 1s and 10 2s*/
    @Test
    void shouldGiveTheTotalScoreWhenAllRollsAre0() {
        rollMutipleTimes(0, 20);
        assertEquals(0, game.score());
    }

    @Test
    void shouldGiveTheTotalScoreWhenAllRollsAre1() {
        rollMutipleTimes(1, 20);
        assertEquals(20, game.score());
    }

    @Test
    void shouldGiveTheTotalScoreWhenHalfRollsAre1AndHalfAre2() {
        rollMutipleTimes(1, 10);
        rollMutipleTimes(2, 10);
        assertEquals(30, game.score());
    }

    private static void rollMutipleTimes(int pinsKnowDown, int numberOfRolls) {
        for (int i = 0; i < numberOfRolls; i++) {
            game.roll(pinsKnowDown);
        }
    }
}
