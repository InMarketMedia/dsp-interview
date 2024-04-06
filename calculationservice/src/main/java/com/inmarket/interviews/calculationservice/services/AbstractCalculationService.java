package com.inmarket.interviews.calculationservice.services;

import com.inmarket.interviews.calculationservice.core.CalculationException;

import java.math.BigInteger;

public abstract class AbstractCalculationService implements CalculationService{
    @Override
    public BigInteger calculate(long n) throws CalculationException {
        if (n < MIN || n > MAX) {
            throw new CalculationException("Invalid n", n);
        }
        return doCalculation(n);
    }

    protected abstract BigInteger doCalculation(long n);

    @Override
    public long getMax() {
        return MAX;
    }

    @Override
    public long getMin() {
        return MIN;
    }
}
