import java.util.Arrays;

public class lesson_one {
    public static void main(String[] args) {
        //Задание 1
        printThreeWords();

        //Задание 2
        checkSumSign();

        //Задание 3
        printColor();

        //Задание4
        compareNumbers();

        //Задание5
        System.out.println(checkSumRange(10, 8));

        //Задание 6
        checkNumber(-12);

        //Задание 7
        System.out.println(checkNegativeNumber(-1));

        //Задание 8
        printString("Привет", 1);

        //Задание 9
        System.out.println(leapYear(2024));

        //Задание 10
        changeArray();

        //Задание 11
        fillArray();

        //Задание 12
        multiplicationElements();

        //Задание 13
        fillDiagonal();

        //Задание 14
        createArray(5, 7);

    }
    //Задание 1
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    //Задание 2.
    public static void checkSumSign()
    {
        int a = 5;
        int b = -10;
        if (a + b >= 0) {
            System.out.println ("Сумма положительная");
        } else {
            System.out.println ("Сумма отрицательная");
        }
    }


    //Задание 3.
    public static void printColor()
    {
        int value = -10;
        if (value <= 0) {
            System.out.println ("Красный");
        } else if (value <= 100) {
            System.out.println ("Желтый");
        } else {
            System.out.println ("Зеленый");
        }
    }

    //Задание 4.
    public static void compareNumbers()
    {
        int a = -1;
        int b = -1;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    //Задание 5.
    public static boolean checkSumRange(int a, int b)
    {
        return a + b >= 10 && a + b <= 20;
    }

    //Задание 6.
    public static void checkNumber(int a)
    {
        if (a >= 10) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }

    //Задание 7.
    public static boolean checkNegativeNumber(int a)
    {
        return a < 0;
    }


    //Задание 8.
    public static void printString (String text, int a)
    {
        for (int i = 0; i < a; i++) {
            System.out.println(text);
        }
    }

    //Задание 9
    public static boolean leapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }


    //Задание 10
    public static void changeArray() {
        int[] nums = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = 1;
            } else if (nums[i] == 1) {
                nums[i] = 0;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    //Задание 11
    public static void fillArray() {
        int[] nums = new int[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        System.out.println(Arrays.toString(nums));
    }

    //Задание 12
    public static void multiplicationElements() {
        int[] nums = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 6) {
                nums[i] = nums[i] * 2;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    //Задание 13
    public static void fillDiagonal() {
        //int size = 4;
        int[][] table = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][i] = 1;
                table[i][4 - 1 - i] = 1;
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

    }

    //Задание 14
    public static void createArray(int len, int initialValue) {
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = initialValue;
        }
        System.out.println(Arrays.toString(nums));
    }


}
