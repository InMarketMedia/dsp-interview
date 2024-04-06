package com.inmarket.interviews.calculationservice.core;

import java.math.BigInteger;

public interface Calculation {
    BigInteger calculate(long n) throws CalculationException;
}
