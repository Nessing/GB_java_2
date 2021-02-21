package lesson_3;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private Map<Long, String> map = new HashMap<>();

    public void add(String name, long telephone) {
        map.put(telephone, name);
    }

    public void get(String name) {
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            if (name == entry.getValue()) {
                System.out.println(entry.getValue() + " | " + entry.getKey());
            }
        }
    }
}