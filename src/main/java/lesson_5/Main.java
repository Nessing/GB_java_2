package lesson_5;

public class Main {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        long a;

        calculationArray(arr);
        splitArrayThreads(arr, a1, a2);
    }

    public static void calculationArray(float[] array) {
        long a = System.currentTimeMillis();

        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.printf("%s: %s ms%n", Thread.currentThread().getName(), System.currentTimeMillis() - a);
    }

    public static void splitArrayThreads(float[] array, float[] a1, float[] a2) {

        System.arraycopy(array, 0, a1, 0, HALF);
        System.arraycopy(array, HALF, a2, 0, HALF);

        new Thread(() -> calculationArray(a1)).start();
        new Thread(() -> calculationArray(a2)).start();

        System.arraycopy(a1, 0, array, 0, HALF);
        System.arraycopy(a2, 0, array, HALF, HALF);
    }
}