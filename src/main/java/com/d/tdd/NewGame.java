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
            if (isStrike(index)) {
                sum += rollsSum + rolls[index + 2];
                index++;
            } else if (isSpare(rollsSum)) {
                sum += rollsSum + rolls[index + 2];
                index += 2;
            } else {
                sum += rollsSum;
                index += 2;
            }
        }
        return sum;
    }

    private static boolean isSpare(int rollsSum) {
        return rollsSum == 10;
    }

    private boolean isStrike(int index) {
        return isSpare(rolls[index]);
    }
}
