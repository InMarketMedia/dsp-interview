package com.inmarket.interviews.calculationservice.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareCalculationTest {

    private Calculation calculation;

    @BeforeEach
    void init() {
        calculation = new SquareCalculation();
    }

    @Test
    void testCalculate() {
        long[] result = {0, 1, 4, 9, 16, 25};
        for (int i = 0; i < result.length; i++) {
            BigInteger value = calculation.calculate(i);
            assertEquals(result[i], value.longValue());
        }
    }
}