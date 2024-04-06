package com.inmarket.interviews.calculationservice.services;

import com.inmarket.interviews.calculationservice.core.CalculationException;

import java.math.BigInteger;

public interface CalculationService {
    long MIN = 0;
    long MAX = 1000000000;

    BigInteger calculate(long n) throws CalculationException;

    long getMax();

    long getMin();
}
