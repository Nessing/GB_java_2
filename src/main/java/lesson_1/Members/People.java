package lesson_1.Members;

public class People implements Actions {
    private String name;
    private int maxDistance;
    private int maxJump;

    public People(String name, int maxDistance, int maxJump) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxJump = maxJump;
    }

    public String getName() {
        return name;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxJump() {
        return maxJump;
    }

    public void run() {
        System.out.print(name + " успешно пробежал");
    }

    public void jump() {
        System.out.print(name + " успешно перепрыгнул стену");
    }
}
