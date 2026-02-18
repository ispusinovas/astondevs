package com.lesson7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для калькулятора факториала")
public class FactorialTest {
    private Factorial calculator;

    @BeforeEach
    void setUp() {
        calculator = new Factorial();
    }

    @Test
    @DisplayName("Факториал нуля должен быть равен 1")
    void testFactorialOfZero() {
        long result = calculator.calculateFactorial(0);
        assertEquals(1, result, "Факториал 0 должен быть 1");
    }

    @Test
    @DisplayName("Факториал единицы должен быть равен 1")
    void testFactorialOfOne() {
        long result = calculator.calculateFactorial(1);
        assertEquals(1, result, "Факториал 1 должен быть 1");
    }

    @Test
    @DisplayName("Факториал положительного числа")
    void testFactorialOfPositiveNumber() {
        assertEquals(2, calculator.calculateFactorial(2), "Факториал 2 должен быть 2");
        assertEquals(6, calculator.calculateFactorial(3), "Факториал 3 должен быть 6");
        assertEquals(24, calculator.calculateFactorial(4), "Факториал 4 должен быть 24");
        assertEquals(120, calculator.calculateFactorial(5), "Факториал 5 должен быть 120");
    }

    @Test
    @DisplayName("Факториал отрицательного числа должен выбросить исключение")
    void testFactorialOfNegativeNumber() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateFactorial(-5),
                "Должно быть выброшено исключение для отрицательного числа"
        );

        assertTrue(exception.getMessage().contains("отрицательного числа"));
    }
}
