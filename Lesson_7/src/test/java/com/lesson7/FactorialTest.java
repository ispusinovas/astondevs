package com.lesson7;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTest {

    private Factorial calculator;

    @BeforeMethod
    public void setUp() {
        calculator = new Factorial();
    }

    @Test(description = "Факториал нуля должен быть равен 1")
    public void testFactorialOfZero() {
        long result = calculator.calculateFactorial(0);
        assertEquals(result, 1, "Факториал 0 должен быть 1");
    }

    @Test(description = "Факториал единицы должен быть равен 1")
    public void testFactorialOfOne() {
        long result = calculator.calculateFactorial(1);
        assertEquals(result, 1, "Факториал 1 должен быть 1");
    }

    @Test(description = "Факториал положительных чисел")
    public void testFactorialOfPositiveNumbers() {
        assertEquals(calculator.calculateFactorial(2), 2);
        assertEquals(calculator.calculateFactorial(3), 6);
        assertEquals(calculator.calculateFactorial(4), 24);
        assertEquals(calculator.calculateFactorial(5), 120);
    }

    @Test(description = "Факториал числа 10")
    public void testFactorialOfLargeNumber() {
        long result = calculator.calculateFactorial(10);
        assertEquals(result, 3628800);
    }

    @Test(description = "Факториал отрицательного числа должен выбросить исключение", expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegativeNumber() {
        calculator.calculateFactorial(-5);
    }
}
