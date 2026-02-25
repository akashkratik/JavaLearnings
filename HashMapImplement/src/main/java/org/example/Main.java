package org.example;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("hello", 1);
        map.put("how", 2);
        map.put("are", 3);
        map.put("you", 4);
        map.put("doing", 5);
        map.put("mate", 6);
        map.put("friend", 6);
        map.put("!", 7);

        System.out.println(map.get("you"));

        System.out.println(map.remove("you"));

        System.out.println(map.get("you"));

        System.out.println(map.isEmpty());

        System.out.println(map.size());

    }
}