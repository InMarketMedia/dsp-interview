package com.inmarket.interviews.calculationprocessor;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CalculationProcessorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CalculationProcessorApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
