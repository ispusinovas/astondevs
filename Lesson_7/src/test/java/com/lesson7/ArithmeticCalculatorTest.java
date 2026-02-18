package com.lesson7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для арифметического калькулятора")

public class ArithmeticCalculatorTest {


    private ArithmeticCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new ArithmeticCalculator();
    }

    @Test
    @DisplayName("Сложение положительных чисел")
    void testAddPositiveNumbers() {
        assertEquals(8, calculator.add(5, 3), "5 + 3 должно быть 8");
        assertEquals(100, calculator.add(50, 50), "50 + 50 должно быть 100");
    }

    @Test
    @DisplayName("Сложение с нулем")
    void testAddWithZero() {
        assertEquals(5, calculator.add(5, 0), "5 + 0 должно быть 5");
        assertEquals(0, calculator.add(0, 0), "0 + 0 должно быть 0");
    }

    @Test
    @DisplayName("Сложение c отрицательными числами")
    void testAddNegativeNumbers() {
        assertEquals(-8, calculator.add(-5, -3), "-5 + (-3) должно быть -8");
        assertEquals(2, calculator.add(5, -3), "5 + (-3) должно быть 2");
    }

    @Test
    @DisplayName("Вычитание положительных чисел")
    void testSubtractPositiveNumbers() {
        assertEquals(2, calculator.subtract(5, 3), "5 - 3 должно быть 2");
        assertEquals(-5, calculator.subtract(5, 10), "5 - 10 должно быть -5");
    }

    @Test
    @DisplayName("Вычитание с нулем")
    void testSubtractWithZero() {
        assertEquals(5, calculator.subtract(5, 0), "5 - 0 должно быть 5");
        assertEquals(-5, calculator.subtract(0, 5), "0 - 5 должно быть -5");
    }

    @Test
    @DisplayName("Вычитание отрицательных чисел")
    void testSubtractNeagtiveNumbers() {
        assertEquals(-4, calculator.subtract(-5, -1), "-5 - (-1) должно быть -4");
        assertEquals(4, calculator.subtract(-11, -15), "-11 - (-15) должно быть 4");
    }

    @Test
    @DisplayName("Умножение положительных чисел")
    void testMultiplyPositiveNumbers() {
        assertEquals(15, calculator.multiply(5, 3), "5 * 3 должно быть 15");
        assertEquals(100, calculator.multiply(10, 10), "10 * 10 должно быть 100");
    }

    @Test
    @DisplayName("Умножение на ноль")
    void testMultiplyByZero() {
        assertEquals(0, calculator.multiply(5, 0), "5 * 0 должно быть 0");
        assertEquals(0, calculator.multiply(0, 5), "0 * 5 должно быть 0");
    }

    @Test
    @DisplayName("Умножение с отрицательными числами")
    void testMultiplyNegativeNumbers() {
        assertEquals(-15, calculator.multiply(5, -3), "5 * (-3) должно быть -15");
        assertEquals(15, calculator.multiply(-5, -3), "(-5) * (-3) должно быть 15");
    }

    @Test
    @DisplayName("Деление положительных чисел")
    void testDividePositiveNumbers() {
        assertEquals(2.0, calculator.divide(6, 3), 0.001, "6 / 3 должно быть 2");
        assertEquals(2.5, calculator.divide(5, 2), 0.001, "5 / 2 должно быть 2.5");
    }

    @Test
    @DisplayName("Деление с отрицательными числами")
    void testDivideNegativeNumbers() {
        assertEquals(-2.0, calculator.divide(6, -3), 0.001, "6 / (-3) должно быть -2");
        assertEquals(2.0, calculator.divide(-6, -3), 0.001, "(-6) / (-3) должно быть 2");
    }

    @Test
    @DisplayName("Деление нуля на число")
    void testDivideZeroByNumber() {
        assertEquals(0.0, calculator.divide(0, 5), 0.001, "0 / 5 должно быть 0");
    }

    @Test
    @DisplayName("Деление на ноль должно выбросить исключение")
    void testDivideByZero() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(5, 0),
                "Должно быть выброшено исключение при делении на ноль"
        );

        assertTrue(exception.getMessage().contains("Деление на ноль"));
    }


    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "10, 20, 30",
            "-5, 5, 0",
            "100, 200, 300"
    })
    @DisplayName("Параметризованный тест сложения")
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
}
