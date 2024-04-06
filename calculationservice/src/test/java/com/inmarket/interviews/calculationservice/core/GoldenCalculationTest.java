package com.inmarket.interviews.calculationservice.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoldenCalculationTest {

    private Calculation calculation;

    @BeforeEach
    void init() {
        calculation = new GoldenCalculation();
    }

    @Test
    void testCalculate() {
        long[] result = {0, 1, 1, 2, 3, 5};
        for (int i = 0; i < result.length; i++) {
            BigInteger value = calculation.calculate(i);
            assertEquals(result[i], value.longValue());
        }
    }
}