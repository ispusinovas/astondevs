public class Main {

    static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    static class MyArrayDataException extends Exception {
        public MyArrayDataException(String message) {
            super(message);
        }
    }

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {

        if (array == null) {
            throw new MyArraySizeException("Массив равен null");
        }

        if (array.length != 4) {
            throw new MyArraySizeException("Неправильное количество строк: " + array.length);
        }

        for (int i = 0; i < 4; i++) {
            if (array[i] == null) {
                throw new MyArraySizeException("Строка " + i + " равна null");
            }
            if (array[i].length != 4) {
                throw new MyArraySizeException("В строке " + i + " неправильное количество столбцов: " + array[i].length);
            }
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] == null) {
                    throw new MyArrayDataException("Ошибка в ячейке [" + i + "][" + j + "]: значение null");
                }
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка в ячейке [" + i + "][" + j + "]: '" + array[i][j] + "'");
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[][] array = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};

        try {
            int result = processArray(array);
            System.out.println("Сумма: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

        int[] numbers = {10, 20, 30};
        try {
            int value = numbers[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Неверный индекс массива");
        }
    }
}
