package com.sv.games.fruitmachine;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class FruitMachine {

    static final String EXCEPTION_MESSAGE = "An amount can't be empty";

    private static final Deque<BigDecimal> MONEY_BANK = new ArrayDeque<>();

    Color[] generateRandomSlot() {

        Color[] colorValues = Color.values();

        Color[] result = new Color[4];

        for (int i = 0; i <= result.length - 1; i++) {

            int randomIndex = new Random().nextInt(colorValues.length);

            result[i] = colorValues[randomIndex];

        }

        return result;
    }

    boolean hasWon(Color[] randomSlot) {

        int index = 1;

        for (int i = 0; i < randomSlot.length - 1; i++) {
            if (randomSlot[i] == randomSlot[i + 1]) {
                index++;
            }
        }

        return index == randomSlot.length;

    }

    void storeMoney(BigDecimal amount) {
        if (amount == null || BigDecimal.ZERO.equals(amount)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        MONEY_BANK.add(amount);
    }

    BigDecimal refundIfWon(boolean hasWon) {
        if (hasWon) {
            return MONEY_BANK.peek();
        }

        return BigDecimal.ZERO;
    }

    public static void main(String[] args) {

        FruitMachine fruitMachine = new FruitMachine();

        BigDecimal fortune = BigDecimal.valueOf(500);
        fruitMachine.storeMoney(fortune);

        Color[] randomSlot = fruitMachine.generateRandomSlot();
        System.out.println(Arrays.toString(randomSlot));

        boolean won = fruitMachine.hasWon(randomSlot);
        System.out.println(won);

        BigDecimal refunded = fruitMachine.refundIfWon(won);
        System.out.println(refunded);

    }

}
