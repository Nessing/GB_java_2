package lesson_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //--- №1 ПОИСК УНИКАЛЬНЫХ СЛОВ И КОЛИЧЕСТВО ПОВТОРЕНИЙ ---
        String[] arrayWords = new String[]{
                "Airborne", "overdrive", "Flex", "Fire", "gun",
                "guitar", "military", "water", "Fire", "car",
                "king", "car", "Airborne", "king", "yellow",
                "red", "gun", "water", "lazy", "overdrive"
        };
        // вывод в консоль уникальных слов из массива
        printListUniqWords(arrayWords);
        // вывод в консоль количество повторений слов
        printCountWords(arrayWords);


        //--- № 2 ТЕЛЕФОННАЯ КНИГА ---
        // создание телефонной книги
        Phonebook phonebook = new Phonebook();
        // добавление новых контактов в телефонную книгу через метод add()
        phonebook.add("Василий", 8_900_82_57_412L);
        phonebook.add("Василий", 8_911_27_67_463L);
        phonebook.add("Анна", 8_921_55_21_108L);
        phonebook.add("Евгений", 8_911_72_00_981L);

        // поиск контакта по имени и вывод в консоль найденные контакты с номерами телефонов через метод get()
        phonebook.get("Василий");
    }

    public static void printListUniqWords(String[] arrayStr) {
        Set<String> uniqWords = new HashSet<>();

        // добавление в коллекцию все элементы массива arrayStr. HashSet хранит только уникальные значение
        for (String s : arrayStr) {
            uniqWords.add(s);
        }

        // вывод результата в консоль
        System.out.println("=== unique words from an array ===");
        for (String s : arrayStr) {
            System.out.print(s.toString() + " ");
        }
        System.out.println("\n==================================");
    }

    public static void printCountWords(String[] arrayStr) {
        // создание коллекции Map
        Map<String, Integer> map = new HashMap<>();
        // проход по всему списку, счетчик количества повторений слов, запись результата в Map
        // K -> слово, V -> количество слов
        for (String str : arrayStr) {
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        // проход по коллекции Map и вывод в консоль результат
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " | " + entry.getValue());
        }

        System.out.println("==================================");
    }


}
