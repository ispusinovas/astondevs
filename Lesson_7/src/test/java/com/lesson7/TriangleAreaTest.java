package com.lesson7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для калькулятора площади треугольника")

public class TriangleAreaTest {


    private TriangleArea calculator;
    private static final double DELTA = 0.001; // Погрешность для сравнения double

    @BeforeEach
    void setUp() {
        calculator = new TriangleArea();
    }

    @Test
    @DisplayName("Площадь прямоугольного треугольника")
    void testRightTriangle() {
        double area = calculator.calculateArea(3, 4, 5);
        assertEquals(6.0, area, DELTA, "Площадь треугольника 3-4-5 должна быть 6");
    }

    @Test
    @DisplayName("Площадь равностороннего треугольника")
    void testEquilateralTriangle() {
        double area = calculator.calculateArea(5, 5, 5);
        double expectedArea = (Math.sqrt(3) / 4) * 25; // ≈ 10.825
        assertEquals(expectedArea, area, DELTA, "Площадь равностороннего треугольника со стороной 5");
    }

    @Test
    @DisplayName("Площадь разностороннего треугольника")
    void testScaleneTriangle() {
        double area = calculator.calculateArea(7, 8, 9);
        assertEquals(26.832, area, DELTA, "Площадь треугольника со сторонами 7, 8, 9");
    }

    @Test
    @DisplayName("Невалидный треугольник")
    void testInvalidTriangle() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateArea(1, 2, 10),
                "Должно быть выброшено исключение для невалидного треугольника"
        );

        assertTrue(exception.getMessage().contains("не образуют треугольник"));
    }

    @Test
    @DisplayName("Отрицательное значение для стороны треугольника")
    void testNegativeSide() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateArea(-3, 4, 5),
                "Должно быть выброшено исключение для отрицательной стороны"
        );

        assertTrue(exception.getMessage().contains("положительными"));
    }

    @Test
    @DisplayName("Нулевое значение для стороны треугольника")
    void testZeroSide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateArea(0, 4, 5),
                "Должно быть выброшено исключение для нулевой стороны"
        );
    }
}
