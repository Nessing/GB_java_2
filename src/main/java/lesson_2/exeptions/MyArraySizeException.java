package lesson_2.exeptions;

public class MyArraySizeException extends Exception  {

    public MyArraySizeException() {
        super();
    }

    public MyArraySizeException(String message) {
        super(message);
    }
}
