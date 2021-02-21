package lesson_1;

import lesson_1.Members.Actions;
import lesson_1.Members.Cat;
import lesson_1.Members.People;
import lesson_1.Members.Robot;
import lesson_1.Obstacles.Objects;
import lesson_1.Obstacles.Treadmill;
import lesson_1.Obstacles.Wall;

public class Main {
    public static void main(String[] args) {
        // создание массива участников
        Actions[] members = {
                new People("James", 200, 30),
                new Cat("Tomas", 100, 20),
                new Robot("J45O2", 1000, 100)
        };

        // создание массива с набором препятствий препятствий
        Objects[] obstacles = {
                new Treadmill(100),
                new Wall(10),
                new Treadmill(150),
                new Wall(20),
                new Treadmill(75),
                new Wall(50),
                new Treadmill(200)
        };
        // запуск метода для начала забега участниками и преодоление ими препятствий
        startOfTheRace(members, obstacles);
    }

    private static void startOfTheRace(Actions[] members, Objects[] obstacles) {
        // цикл для прохода по всем участикам (прохождение участниками через набор препятствий)
        for (int i = 0; i < members.length; i++) {
            // сообщение об начале забега участника
            System.out.printf("== %s начинает забег ==\n", members[i].getName());
            // цикл для прохода по всем препятствиям
            for (int j = 0; j < obstacles.length; j++) {
                // если препятствие принадлежит (instanceof) классу Treadmill (беговая дорожка)
                if (obstacles[j] instanceof Treadmill) {
                    // если истина, тогда проверяется, что участник может преодолеть это расстояние
                    // если истина, тогда выводиться сообщение об успешном преодолении дистанции
                    // дополнительно выводиться пройденное расстояние в консоль
                    if (members[i].getMaxDistance() >= obstacles[j].getValue()) {
                        members[i].run();
                        System.out.printf(" %d м\n", obstacles[j].getValue());
                        // в ином случае выводиться сообщение о невозможности дальнейшего прохождения забега участником
                        // и осуществляется переход к следующему участнику (выход из цикла препятствий)
                    } else {
                        System.out.println(members[i].getName() + " не может бежать дальше \n== Сходит с дистанции ==\n");
                        break;
                    }
                    // если препятствие не пренадлежит классу Treadmill, тогда оно пренадлежит классу Wall (стена)
                } else {
                    // проверяется, что участник может перепрыгнуть эту стену
                    // если истина, тогда выводиться сообщение об успешном преодолении препятствия
                    // дополнительно выводиться высота перепрыгнутой стены в консоль
                    if (members[i].getMaxJump() >= obstacles[j].getValue()) {
                        members[i].jump();
                        System.out.printf(" на %d м\n", obstacles[j].getValue());
                        // в ином случае выводиться сообщение о невозможности дальнейшего прохождения забега участником
                        // и осуществляется переход к следующему участнику (выход из цикла препятствий)
                    } else {
                        System.out.println(members[i].getName() + " не может перепрыгнуть стену\n== Сходит с дистанции ==\n");
                        break;
                    }
                }
            }
        }
    }
}
