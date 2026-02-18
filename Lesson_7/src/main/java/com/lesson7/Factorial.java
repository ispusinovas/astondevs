package com.lesson7;

public class Factorial {

    public long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не существует");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }

}
