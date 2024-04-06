package com.inmarket.interviews.calculationservice.services;

import com.inmarket.interviews.calculationservice.core.Calculation;
import com.inmarket.interviews.calculationservice.core.SquareCalculation;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class SquareService extends AbstractCalculationService {
    private final Calculation calculation;

    public SquareService(SquareCalculation calculation) {
        this.calculation = calculation;
    }

    @Override
    public BigInteger doCalculation(long n)  {
        return calculation.calculate(n);
    }
}
