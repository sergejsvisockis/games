package com.sv.games.fruitmachine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrizeCalculator {

    BigDecimal calculateThePrize(int numberOfAdjacentSlots, BigDecimal currentAmount, BigDecimal singlePlayCost) {

        BigDecimal refundAmount = BigDecimal.ZERO;

        if (numberOfAdjacentSlots == 0) {

            BigDecimal fullPrize = currentAmount.subtract(currentAmount
                    .divide(BigDecimal.TWO, RoundingMode.UP));

            refundAmount = calculatePrizeIfNoMoneyAvailable(fullPrize, currentAmount);

        } else if (numberOfAdjacentSlots >= 2) {

            BigDecimal fullPrize = singlePlayCost.multiply(BigDecimal.valueOf(5));

            refundAmount = calculatePrizeIfNoMoneyAvailable(fullPrize, currentAmount);

        }

        currentAmount.subtract(refundAmount); // TODO: verify that this works

        return refundAmount;
    }

    // TODO: revisit this requirement, doesn't seem like to be correct
    private BigDecimal calculatePrizeIfNoMoneyAvailable(BigDecimal fullPrize, BigDecimal currentAmount) {

        if (fullPrize.longValue() > currentAmount.longValue()) {

            return fullPrize.subtract(currentAmount);

        }

        return fullPrize;
    }
}
