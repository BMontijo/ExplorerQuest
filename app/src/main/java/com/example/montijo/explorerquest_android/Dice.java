package com.example.montijo.explorerquest_android;

import java.util.Random;

/**
 * Created by Brian on 2/26/2017.
 */

public final class Dice {
    public static int roll(int sides) {
        Random dieRoll = new Random();
        return (dieRoll.nextInt(sides) + 1);
    }

    public static int rollD4() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(4) + 1);
    }

    public static int rollD6() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(6) + 1);
    }

    public static int rollD7() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(7) + 1);
    }

    public static int rollD8() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(8) + 1);
    }

    public static int rollD10() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(10) + 1);
    }

    public static int rollD12() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(12) + 1);
    }

    public static int rollD16() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(16) + 1);
    }

    public static int rollD20() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(20) + 1);
    }

    public static int rollD24() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(24) + 1);
    }

    public static int rollD26() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(26) + 1);
    }
    public static int rollD30() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(30) + 1);
    }

    public static int rollD50() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(50) + 1);
    }

    public static int rollD60() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(60) + 1);
    }

    public static int rollD100() {
        Random diceRoll = new Random();
        return (diceRoll.nextInt(100) + 1);
    }
}
