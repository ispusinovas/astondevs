package com.lesson7;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArithmeticCalculatorTest {

    private ArithmeticCalculator calculator;

    @BeforeMethod
    public void setUp() {
        calculator = new ArithmeticCalculator();
    }

    @Test(description = "Сложение положительных чисел")
    public void testAddPositiveNumbers() {
        assertEquals(calculator.add(5, 3), 8);
        assertEquals(calculator.add(50, 50), 100);
    }

    @Test(description = "Сложение с нулем")
    public void testAddWithZero() {
        assertEquals(calculator.add(5, 0), 5);
        assertEquals(calculator.add(0, 0), 0);
    }

    @Test(description = "Сложение отрицательных чисел")
    public void testAddNegativeNumbers() {
        assertEquals(calculator.add(-5, -3), -8);
        assertEquals(calculator.add(5, -3), 2);
    }

    @Test(description = "Вычитание положительных чисел")
    public void testSubtractPositiveNumbers() {
        assertEquals(calculator.subtract(5, 3), 2);
        assertEquals(calculator.subtract(5, 10), -5);
    }

    @Test(description = "Вычитание с нулем")
    public void testSubtractWithZero() {
        assertEquals(calculator.subtract(5, 0), 5);
        assertEquals(calculator.subtract(0, 5), -5);
    }

    @Test(description = "Умножение положительных чисел")
    public void testMultiplyPositiveNumbers() {
        assertEquals(calculator.multiply(5, 3), 15);
        assertEquals(calculator.multiply(10, 10), 100);
    }

    @Test(description = "Умножение на ноль")
    public void testMultiplyByZero() {
        assertEquals(calculator.multiply(5, 0), 0);
        assertEquals(calculator.multiply(0, 5), 0);
    }

    @Test(description = "Умножение отрицательных чисел")
    public void testMultiplyNegativeNumbers() {
        assertEquals(calculator.multiply(5, -3), -15);
        assertEquals(calculator.multiply(-5, -3), 15);
    }

    @Test(description = "Деление положительных чисел")
    public void testDividePositiveNumbers() {
        assertEquals(calculator.divide(6, 3), 2.0, 0.001);
        assertEquals(calculator.divide(5, 2), 2.5, 0.001);
    }

    @Test(description = "Деление с отрицательными числами")
    public void testDivideNegativeNumbers() {
        assertEquals(calculator.divide(6, -3), -2.0, 0.001);
        assertEquals(calculator.divide(-6, -3), 2.0, 0.001);
    }

    @Test(description = "Деление нуля на число")
    public void testDivideZeroByNumber() {
        assertEquals(calculator.divide(0, 5), 0.0, 0.001);
    }

    @Test(description = "Деление на ноль должно выбросить исключение", expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        calculator.divide(5, 0);
    }

    @DataProvider(name = "additionData")
    public Object[][] additionData() {
        return new Object[][]{{1, 1, 2}, {10, 20, 30}, {-5, 5, 0}, {100, 200, 300}};
    }

    @Test(dataProvider = "additionData", description = "Параметризованный тест сложения")
    public void testAddParameterized(int a, int b, int expected) {
        assertEquals(calculator.add(a, b), expected);
    }
}
