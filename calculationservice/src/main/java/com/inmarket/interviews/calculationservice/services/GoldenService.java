package com.inmarket.interviews.calculationservice.services;

import com.inmarket.interviews.calculationservice.core.Calculation;
import com.inmarket.interviews.calculationservice.core.CalculationException;
import com.inmarket.interviews.calculationservice.core.GoldenCalculation;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class GoldenService extends AbstractCalculationService {
    private final Calculation calculation;

    public GoldenService(GoldenCalculation calculation) {
        this.calculation = calculation;
    }

    @Override
    public BigInteger doCalculation(long n) throws CalculationException {
        return calculation.calculate(n);
    }
}
