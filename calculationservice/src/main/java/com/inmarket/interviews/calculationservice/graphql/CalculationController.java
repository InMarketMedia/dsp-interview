package com.inmarket.interviews.calculationservice.graphql;

import com.inmarket.interviews.calculationservice.services.TriangleService;
import com.inmarket.interviews.calculationservice.services.GoldenService;
import com.inmarket.interviews.calculationservice.services.SquareService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.math.BigInteger;

@Controller
public class CalculationController {
    private final GoldenService goldenService;
    private final SquareService squareService;
    private final TriangleService triangleService;

    public CalculationController(GoldenService goldenService, SquareService squareService, TriangleService triangleService) {
        this.goldenService = goldenService;
        this.squareService = squareService;
        this.triangleService = triangleService;
    }

    @QueryMapping
    public Result calculate(@Argument long n) {
        return new Result(n);
    }

    @SchemaMapping
    public Golden golden(Result result) {
        BigInteger value = goldenService.calculate(result.value());
        return new Golden(new Calculation(value), goldenService.getMin(), goldenService.getMax());
    }

    @SchemaMapping
    public Square square(Result result) {
        BigInteger value = squareService.calculate(result.value());
        return new Square(new Calculation(value), squareService.getMin(), squareService.getMax());
    }

    @SchemaMapping
    public Triangle triangle(Result result) {
        BigInteger value = triangleService.calculate(result.value());
        return new Triangle(new Calculation(value), triangleService.getMin(), triangleService.getMax());
    }
}
