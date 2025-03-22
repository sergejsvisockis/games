package com.sv.games.fruitmachine;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class FruitMachine {

    static final String EXCEPTION_MESSAGE = "An amount can't be empty";

    private static final Deque<BigDecimal> MONEY_BANK = new ArrayDeque<>();

    private static final BigDecimal CURRENT_AMOUNT = BigDecimal.valueOf(new Random().nextInt(3000));

    private static final BigDecimal SINGLE_PLAY_COST = BigDecimal.valueOf(500);

    Color[] generateRandomSlot() {

        Color[] colorValues = Color.values();

        Color[] result = new Color[4];

        for (int i = 0; i <= result.length - 1; i++) {

            int randomIndex = new Random().nextInt(colorValues.length);

            result[i] = colorValues[randomIndex];

        }

        return result;
    }

    boolean hasWonJackpot(Color[] randomSlot) {

        int index = 1;

        for (int i = 0; i < randomSlot.length - 1; i++) {
            if (randomSlot[i] == randomSlot[i + 1]) {
                index++;
            }
        }

        return index == randomSlot.length;

    }

    BigDecimal refundJackpotIfWon(boolean hasWon) {
        if (hasWon) {
            return MONEY_BANK.peek();
        }

        return BigDecimal.ZERO;
    }

    int getNumberOfAdjacentSlots(Color[] randomSlot) {

        int index = 1;

        for (int i = 0; i < randomSlot.length - 1; i++) {

            if (randomSlot[i] == randomSlot[i + 1]) {
                index++;
            }

        }

        if (index == 1) {
            return 0;
        }

        return index;

    }

    void storeMoney(BigDecimal amount) {
        if (amount == null || BigDecimal.ZERO.equals(amount)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        MONEY_BANK.add(amount);
    }

    public static void main(String[] args) {

        FruitMachine fruitMachine = new FruitMachine();
        PrizeCalculator prizeCalculator = new PrizeCalculator();

        System.out.println("Current amount: " + CURRENT_AMOUNT);

        BigDecimal fortune = BigDecimal.valueOf(500);
        fruitMachine.storeMoney(fortune);

        Color[] randomSlots = fruitMachine.generateRandomSlot();
        System.out.println(Arrays.toString(randomSlots));

        boolean won = fruitMachine.hasWonJackpot(randomSlots);
        System.out.println("Has won: " + won);

        BigDecimal refunded = fruitMachine.refundJackpotIfWon(won);
        System.out.println("An amount refunded: " + refunded);

        int numberOfAdjacentSlots = fruitMachine.getNumberOfAdjacentSlots(randomSlots);
        System.out.println("Number of adjacent slots: " + numberOfAdjacentSlots);

        BigDecimal prize = prizeCalculator.calculatePrize(numberOfAdjacentSlots,
                CURRENT_AMOUNT, SINGLE_PLAY_COST);
        System.out.println("The prize: " + prize);

    }

}
