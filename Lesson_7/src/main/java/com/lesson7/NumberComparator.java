package com.lesson7;

public class NumberComparator {

    public enum ComparisonResult {
        GREATER,
        LESS,
        EQUAL
    }

    public ComparisonResult compare(int a, int b) {
        if (a > b) {
            return ComparisonResult.GREATER;
        } else if (a < b) {
            return ComparisonResult.LESS;
        } else {
            return ComparisonResult.EQUAL;
        }
    }

    public int compareAsInt(int a, int b) {
        return Integer.compare(a, b);
    }

}
