package com.d.tdd;

public class NewGame {
    int[] rolls = new int[21];
    int rollIndex = 0;

    public void roll(int pinsKnowDown) {
        rolls[rollIndex++] = pinsKnowDown;
    }

    public int score() {
        int sum = 0;
        int index = 0;
        for (int frame = 0; frame < 10; frame++) {
            int rollsSum = rolls[index] + rolls[index + 1];
            if (rollsSum == 10)
                sum += rolls[index + 2];
            sum += rollsSum;
            index += 2;
        }
        return sum;
    }
}
