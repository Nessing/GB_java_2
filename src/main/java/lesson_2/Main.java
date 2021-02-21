package lesson_2;

import lesson_2.exeptions.MyArrayDataException;
import lesson_2.exeptions.MyArraySizeException;

public class Main {

    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        String[][] arrStrings = new String[][]{
                {"4", "2", "12", "54"},
                {"4", "2", "12", "6"},
                {"7", "2", "5", "12"},
                {"4", "2", "43", "54"}
        };

        arrayCalculate(arrStrings);
    }

    public static void arrayCalculate(String[][] arrayStr) throws MyArraySizeException, MyArrayDataException {
        // № 1
        if (arrayStr.length != 4 || arrayStr[1].length != 4)
            throw new MyArraySizeException("Массив должен быть 4х4. Текущий размер массива: " + arrayStr.length + "x" + arrayStr[1].length);

        // № 2
        int sum = 0;
        for (int i = 0; i < arrayStr.length; i++) {
            for (int j = 0; j < arrayStr[i].length; j++) {
                try {
                    int s = Integer.parseInt(arrayStr[i][j]);
                    sum += s;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("В " + (i + 1) + " строке " + (j + 1) + " столбца должно быть число (int). По факту: \"" + arrayStr[i][j] + "\"");
                }
            }
        }
        System.out.println("Сумма всех элементов массива равна: " + sum);
    }
}
