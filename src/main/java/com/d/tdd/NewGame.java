package com.d.tdd;

public class NewGame {
int sum=0;
    public void roll(int pinsKnowDown) {
        sum+=pinsKnowDown;
    }

    public int score() {
        return sum;
    }
}
