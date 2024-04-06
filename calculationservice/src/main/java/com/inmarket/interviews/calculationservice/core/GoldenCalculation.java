package com.inmarket.interviews.calculationservice.core;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Calculates the fibonacci number of a value n
 *   F(0) = 0
 *   F(1) = 1
 *   F(n) = F(n-1) + F(n-2)
 *
 * E.g
 *   Given n = 4
 *   F(4) = F(3) + F(2)
 *        = F(2) + F(1) + F(1) + F(0)
 *        = F(1) + F(0) + F(1) + F(1) + F(0)
 *        = 1 + 0 + 1 + 1 + 0
 *        = 3
 *
 *   The fibonacci number is 1
 */
@Component
public class GoldenCalculation implements Calculation {

    /**
     * Calculates the fibonacci number of n
     * @param n
     * @return The fibonacci number of n
     * @throws CalculationException
     */
    @Override
    public BigInteger calculate(long n) throws CalculationException {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        }
        BigInteger n1 = calculate(n - 1);
        BigInteger n2 = calculate(n - 2);
        return n1.add(n2);
    }
}
