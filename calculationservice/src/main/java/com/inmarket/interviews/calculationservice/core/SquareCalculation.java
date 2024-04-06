package com.inmarket.interviews.calculationservice.core;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Calculates the square of a number
 *
 * E.g.
 *   Given n is 4
 *   The square is 4 * 4 = 16
 */
@Component
public class SquareCalculation implements Calculation {
    private final static int EXP = 2;

    /**
     * Calculate the square of n
     * @param n
     * @return The square of n
     * @throws CalculationException
     */
    @Override
    public BigInteger calculate(long n) throws CalculationException {
        BigInteger value = BigInteger.valueOf(n);
        return value.pow(EXP);
    }
}
