package lesson_1.Obstacles;

public class Wall implements Objects {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getValue() {
        return height;
    }
}
