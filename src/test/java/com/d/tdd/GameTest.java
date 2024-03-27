package com.d.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    NewGame game = new NewGame();

    /*TCs:
     * 1. All 0s
     * 2. All 1s
     * 3. 10 1s and 10 2s
     * 4. Spare: 5 5 and 1 18s
     * 5. Spare: 5 5 5 5 and 1 16s
     * 6. Strike: 10 skip 1 18s*/
    @Test
    void shouldGiveTheTotalScoreWhenAllRollsAre0() {
        rollMultipleTimes(0, 20);
        assertEquals(0, game.score());
    }

    @Test
    void shouldGiveTheTotalScoreWhenAllRollsAre1() {
        rollMultipleTimes(1, 20);
        assertEquals(20, game.score());
    }

    @Test
    void shouldGiveTheTotalScoreWhenHalfRollsAre1AndHalfAre2() {
        rollMultipleTimes(1, 10);
        rollMultipleTimes(2, 10);
        assertEquals(30, game.score());
    }

    @Test
    void shouldGiveTheTotalScoreWhenItIsASpare() {
        rollMultipleTimes(5, 2);
        rollMultipleTimes(1, 18);
        assertEquals(29, game.score());
    }

    @Test
    void shouldGiveTheTotalScoreWhenItIsASpareTwice() {
        rollMultipleTimes(5, 2);
        rollMultipleTimes(5, 2);
        rollMultipleTimes(1, 16);
        assertEquals(42, game.score());
    }

    @Test
    void shouldGiveTheTotalScoreWhenItIsAStrike() {
        rollMultipleTimes(10, 1);
        rollMultipleTimes(1, 18);
        assertEquals(30, game.score());
    }

    private void rollMultipleTimes(int pinsKnowDown, int numberOfRolls) {
        for (int i = 0; i < numberOfRolls; i++) {
            game.roll(pinsKnowDown);
        }
    }
}
