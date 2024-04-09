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
    public Calculation calculate(@Argument long n) {
        return new Calculation(n);
    }

    @SchemaMapping
    public Golden golden(Calculation calculation) {
        return new Golden(calculation.value(), goldenService.getMin(), goldenService.getMax());
    }

    @SchemaMapping(typeName="Golden", field="result")
    public Result goldenResult(Golden golden) {
        BigInteger value = goldenService.calculate(golden.value());
        return new Result(value);
    }

    @SchemaMapping
    public Square square(Calculation calculation) {
        return new Square(calculation.value(), squareService.getMin(), squareService.getMax());
    }

    @SchemaMapping(typeName="Square", field="result")
    public Result squareResult(Square square) {
        BigInteger value = squareService.calculate(square.value());
        return new Result(value);
    }

    @SchemaMapping
    public Triangle triangle(Calculation calculation) {
        return new Triangle(calculation.value(), triangleService.getMin(), triangleService.getMax());
    }

    @SchemaMapping(typeName="Triangle", field="result")
    public Result triangleResult(Triangle triangle) {
        BigInteger value = goldenService.calculate(triangle.value());
        return new Result(value);
    }
}
