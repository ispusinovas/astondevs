package com.lesson7;

public class TriangleArea {

    public double calculateArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны треугольника должны быть положительными числами");
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Данные стороны не образуют треугольник");
        }

        double p = (a + b + c) / 2;
        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        return area;
    }

}