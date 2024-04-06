package com.inmarket.interviews.calculationservice.services;

import com.inmarket.interviews.calculationservice.core.Calculation;
import com.inmarket.interviews.calculationservice.core.FastTriangleCalculation;
import com.inmarket.interviews.calculationservice.core.TriangleCalculation;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class TriangleService extends AbstractCalculationService {
    private final Calculation calculation;

    public TriangleService(TriangleCalculation calculation) {
        this.calculation = calculation;
    }

    @Override
    protected BigInteger doCalculation(long n) {
        return calculation.calculate(n);
    }
}
