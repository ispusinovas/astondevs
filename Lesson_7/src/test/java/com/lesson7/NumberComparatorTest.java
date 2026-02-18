package com.lesson7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import com.lesson7.NumberComparator.ComparisonResult;

@DisplayName("Тесты для сравнения чисел")

public class NumberComparatorTest {


    private NumberComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new NumberComparator();
    }

    @Test
    @DisplayName("Первое число больше второго")
    void testFirstNumberGreater() {
        assertEquals(ComparisonResult.GREATER, comparator.compare(10, 5),
                "10 должно быть больше 5");
        assertEquals(ComparisonResult.GREATER, comparator.compare(0, -5),
                "0 должно быть больше -5");
    }

    @Test
    @DisplayName("Первое число меньше второго")
    void testFirstNumberLess() {
        assertEquals(ComparisonResult.LESS, comparator.compare(5, 10),
                "5 должно быть меньше 10");
        assertEquals(ComparisonResult.LESS, comparator.compare(-10, 0),
                "-10 должно быть меньше 0");
    }

    @Test
    @DisplayName("Числа равны")
    void testNumbersEqual() {
        assertEquals(ComparisonResult.EQUAL, comparator.compare(5, 5),
                "5 должно быть равно 5");
        assertEquals(ComparisonResult.EQUAL, comparator.compare(0, 0),
                "0 должно быть равно 0");
        assertEquals(ComparisonResult.EQUAL, comparator.compare(-5, -5),
                "-5 должно быть равно -5");
    }

    @Test
    @DisplayName("Сравнение отрицательных чисел")
    void testNegativeNumbers() {
        assertEquals(ComparisonResult.GREATER, comparator.compare(-5, -10),
                "-5 должно быть больше -10");
        assertEquals(ComparisonResult.LESS, comparator.compare(-10, -5),
                "-10 должно быть меньше -5");
    }

}
