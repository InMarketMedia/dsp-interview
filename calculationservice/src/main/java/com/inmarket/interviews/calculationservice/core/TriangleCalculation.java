package com.inmarket.interviews.calculationservice.core;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Calculates the triangular or triangle number given a value n
 *
 * The triangle number is the sum of all number from 0 to n
 *
 * E.g.
 *   Given n is 4
 *   The triangle number is sum(0->4) = 0 + 1 + 2 + 3 + 4 = 10
 */
@Component
public class TriangleCalculation implements Calculation {

    /**
     * Calculates the triangle number of n
     * @param n
     * @return The triangle number of n
     * @throws CalculationException
     */
    @Override
    public BigInteger calculate(long n) throws CalculationException {
        if (n == 0) {
            return BigInteger.ZERO;
        }

        BigInteger n0 = BigInteger.valueOf(n);
        BigInteger n1 = calculate(n-1);
        return n0.add(n1);
    }
}
