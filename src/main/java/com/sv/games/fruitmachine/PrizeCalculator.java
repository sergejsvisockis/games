package com.sv.games.fruitmachine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrizeCalculator {

    BigDecimal calculatePrize(int numberOfAdjacentSlots, BigDecimal currentAmount, BigDecimal singlePlayCost) {

        BigDecimal refundAmount = BigDecimal.ZERO;

        if (numberOfAdjacentSlots == 0) {

            BigDecimal fullPrize = currentAmount.subtract(currentAmount
                    .divide(BigDecimal.TWO, RoundingMode.UP));

            refundAmount = calculatePrizeIfNoMoneyAvailable(fullPrize, currentAmount);

        } else if (numberOfAdjacentSlots >= 2) {

            BigDecimal fullPrize = singlePlayCost.multiply(BigDecimal.valueOf(5));

            refundAmount = calculatePrizeIfNoMoneyAvailable(fullPrize, currentAmount);

        }

        return refundAmount;
    }

    private BigDecimal calculatePrizeIfNoMoneyAvailable(BigDecimal fullPrize, BigDecimal currentAmount) {

        if (fullPrize.longValue() > currentAmount.longValue()) {

            return fullPrize.subtract(currentAmount);

        }

        return fullPrize;
    }
}
