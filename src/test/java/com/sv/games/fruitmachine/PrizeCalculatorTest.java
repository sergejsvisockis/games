package com.sv.games.fruitmachine;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrizeCalculatorTest {

    private PrizeCalculator prizeCalculator = new PrizeCalculator();

    @Test
    void shouldPayOutHalfOfTheCurrentMoneyIfNoAdjacentSlotsPresent() {

        //given
        final int numberOfAdjacentSlots = 0;
        final BigDecimal currentAmount = BigDecimal.valueOf(5000);
        final BigDecimal singlePlayCost = BigDecimal.valueOf(100);

        //when
        BigDecimal prize = prizeCalculator.calculateThePrize(numberOfAdjacentSlots, currentAmount, singlePlayCost);

        //then
        assertEquals(BigDecimal.valueOf(2500), prize);
    }

    @Test
    void shouldPayOutAPrizeOfFiveTimesOfTheCostOfASinglePlay_HavingTwoAdjacentSlots() {

        //given
        final int numberOfAdjacentSlots = 2;
        final BigDecimal currentAmount = BigDecimal.valueOf(5000);
        final BigDecimal singlePlayCost = BigDecimal.valueOf(100);

        //when
        BigDecimal prize = prizeCalculator.calculateThePrize(numberOfAdjacentSlots, currentAmount, singlePlayCost);

        //then
        assertEquals(BigDecimal.valueOf(500), prize);
    }

    @Test
    void shouldPayOutAPrizeOfFiveTimesOfTheCostOfASinglePlay_HavingTHREEAdjacentSlots() {

        //given
        final int numberOfAdjacentSlots = 3;
        final BigDecimal currentAmount = BigDecimal.valueOf(5000);
        final BigDecimal singlePlayCost = BigDecimal.valueOf(100);

        //when
        BigDecimal prize = prizeCalculator.calculateThePrize(numberOfAdjacentSlots, currentAmount, singlePlayCost);

        //then
        assertEquals(BigDecimal.valueOf(500), prize);
    }

    @Test
    void shouldPayOutAPrizeOfFiveTimesOfTheCostOfASinglePlay_HavingFourAdjacentSlots() {

        //given
        final int numberOfAdjacentSlots = 4;
        final BigDecimal currentAmount = BigDecimal.valueOf(5000);
        final BigDecimal singlePlayCost = BigDecimal.valueOf(100);

        //when
        BigDecimal prize = prizeCalculator.calculateThePrize(numberOfAdjacentSlots, currentAmount, singlePlayCost);

        //then
        assertEquals(BigDecimal.valueOf(500), prize);
    }

    @Test
    void shouldCreditThePlayerWithANumberOfFreePlays() {

        //given
        final int numberOfAdjacentSlots = 2;
        final BigDecimal currentAmount = BigDecimal.valueOf(30);
        final BigDecimal singlePlayCost = BigDecimal.valueOf(100);

        //when
        BigDecimal prize = prizeCalculator.calculateThePrize(numberOfAdjacentSlots, currentAmount, singlePlayCost);

        //then
        assertEquals(BigDecimal.valueOf(470), prize);
    }
}
