package com.sv.games.fruitmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.sv.games.fruitmachine.FruitMachine.EXCEPTION_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FruitMachineTest {

    private FruitMachine fruitMachine = new FruitMachine();

    @Test
    void shouldGenerateRandomSlot() {
        Color[] randomSlot = fruitMachine.generateRandomSlot();

        assertEquals(4, randomSlot.length);
    }

    @Test
    void shouldWinJackpot() {

        //given
        Color[] colorValues = {
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE
        };

        //when
        boolean result = fruitMachine.hasWonJackpot(colorValues);

        //then
        assertTrue(result);
    }

    @Test
    void shouldLoose() {

        //given
        Color[] colorValues = {
                Color.GREEN,
                Color.WHITE,
                Color.BLACK,
                Color.YELLOW
        };

        //when
        boolean result = fruitMachine.hasWonJackpot(colorValues);

        //then
        assertFalse(result);
    }

    @Test
    void shouldRefundJackpot() {

        //given
        final boolean won = true;
        BigDecimal amount = BigDecimal.valueOf(500);
        fruitMachine.storeMoney(amount);

        //when
        BigDecimal refunded = fruitMachine.refundJackpotIfWon(won);

        //then
        assertEquals(amount, refunded);
    }

    @Test
    void shouldKeepMoneyAndDontRefund() {

        //given
        final boolean won = false;
        BigDecimal amount = BigDecimal.valueOf(500);
        fruitMachine.storeMoney(amount);

        //when
        BigDecimal refunded = fruitMachine.refundJackpotIfWon(won);

        //then
        assertEquals(BigDecimal.ZERO, refunded);
    }

    @Test
    void shouldThrowIllegalArgumentWhenNullRevenueProvided() {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> fruitMachine.storeMoney(null));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenZeroRevenueProvided() {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> fruitMachine.storeMoney(BigDecimal.ZERO));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldHaveAllSlotsAdjacent() {
        //given
        Color[] colorValues = {
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE
        };

        //when
        int numberOfAdjacentSlots = fruitMachine.getNumberOfAdjacentSlots(colorValues);

        //then
        assertEquals(4, numberOfAdjacentSlots);
    }

    @Test
    void shouldHaveTwoSlotsAdjacent() {
        //given
        Color[] colorValues = {
                Color.WHITE,
                Color.WHITE,
                Color.BLACK,
                Color.GREEN
        };

        //when
        int numberOfAdjacentSlots = fruitMachine.getNumberOfAdjacentSlots(colorValues);

        //then
        assertEquals(2, numberOfAdjacentSlots);
    }

    @Test
    void shouldHaveNoSlotsAdjacent() {
        //given
        Color[] colorValues = {
                Color.WHITE,
                Color.BLACK,
                Color.GREEN,
                Color.YELLOW
        };

        //when
        int numberOfAdjacentSlots = fruitMachine.getNumberOfAdjacentSlots(colorValues);

        //then
        assertEquals(0, numberOfAdjacentSlots);
    }

    @Test
    void shouldHaveNoSlotsAdjacentButHasTheSame() {
        //given
        Color[] colorValues = {
                Color.WHITE,
                Color.BLACK,
                Color.GREEN,
                Color.WHITE
        };

        //when
        int numberOfAdjacentSlots = fruitMachine.getNumberOfAdjacentSlots(colorValues);

        //then
        assertEquals(0, numberOfAdjacentSlots);
    }

}
