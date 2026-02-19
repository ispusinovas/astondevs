package com.lesson7;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleAreaTest {

    private TriangleArea calculator;
    private static final double DELTA = 0.001;

    @BeforeMethod
    public void setUp() {
        calculator = new TriangleArea();
    }

    @Test(description = "Площадь прямоугольного треугольника")
    public void testRightTriangle() {
        double area = calculator.calculateArea(3, 4, 5);
        assertEquals(area, 6.0, DELTA, "Площадь треугольника 3-4-5 должна быть 6");
    }

    @Test(description = "Площадь равностороннего треугольника")
    public void testEquilateralTriangle() {
        double area = calculator.calculateArea(5, 5, 5);
        double expected = (Math.sqrt(3) / 4) * 25;
        assertEquals(area, expected, DELTA);
    }

    @Test(description = "Площадь разностороннего треугольника")
    public void testScaleneTriangle() {
        double area = calculator.calculateArea(7, 8, 9);
        assertEquals(area, 26.832, DELTA);
    }

    @Test(description = "Должно быть выброшено исключение для невалидного треугольника", expectedExceptions = IllegalArgumentException.class)
    public void testInvalidTriangle() {
        calculator.calculateArea(1, 2, 10);
    }

    @Test(description = "Должно быть выброшено исключение для отрицательной стороны", expectedExceptions = IllegalArgumentException.class)
    public void testNegativeSide() {
        calculator.calculateArea(-3, 4, 5);
    }

    @Test(description = "Должно быть выброшено исключение для нулевой стороны", expectedExceptions = IllegalArgumentException.class)
    public void testZeroSide() {
        calculator.calculateArea(0, 4, 5);
    }
}
