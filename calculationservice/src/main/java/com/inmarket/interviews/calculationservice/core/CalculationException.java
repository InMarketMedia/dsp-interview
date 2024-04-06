package com.inmarket.interviews.calculationservice.core;

public class CalculationException extends RuntimeException {
    public CalculationException(String message, long n) {
        super(message + " value: " + n);
    }
}
