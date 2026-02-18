package com.lesson7;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.lesson7.NumberComparator.ComparisonResult;

public class NumberComparatorTest {

    private NumberComparator comparator;

    @BeforeMethod
    public void setUp() {
        comparator = new NumberComparator();
    }

    @Test(description = "Первое число больше второго")
    public void testFirstNumberGreater() {
        assertEquals(comparator.compare(10, 5), ComparisonResult.GREATER);
        assertEquals(comparator.compare(0, -5), ComparisonResult.GREATER);
    }

    @Test(description = "Первое число меньше второго")
    public void testFirstNumberLess() {
        assertEquals(comparator.compare(5, 10), ComparisonResult.LESS);
        assertEquals(comparator.compare(-10, 0), ComparisonResult.LESS);
    }

    @Test(description = "Числа равны")
    public void testNumbersEqual() {
        assertEquals(comparator.compare(5, 5), ComparisonResult.EQUAL);
        assertEquals(comparator.compare(0, 0), ComparisonResult.EQUAL);
        assertEquals(comparator.compare(-5, -5), ComparisonResult.EQUAL);
    }

    @Test(description = "Сравнение больших чисел")
    public void testLargeNumbers() {
        assertEquals(comparator.compare(1000000, 999999), ComparisonResult.GREATER);
    }

    @Test(description = "Сравнение отрицательных чисел")
    public void testNegativeNumbers() {
        assertEquals(comparator.compare(-5, -10), ComparisonResult.GREATER);
        assertEquals(comparator.compare(-10, -5), ComparisonResult.LESS);
    }

    @Test(description = "compareAsInt - положительный результат")
    public void testCompareAsIntPositive() {
        assertTrue(comparator.compareAsInt(10, 5) > 0);
    }

    @Test(description = "compareAsInt - отрицательный результат")
    public void testCompareAsIntNegative() {
        assertTrue(comparator.compareAsInt(5, 10) < 0);
    }

    @Test(description = "compareAsInt - ноль")
    public void testCompareAsIntZero() {
        assertEquals(comparator.compareAsInt(5, 5), 0);
    }
}
