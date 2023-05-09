package org.example;

import java.util.*;

public class Main {
    public static final String START = "start";
    public static final String STOP = "stop";
    public static final String GREETING = ("""
            Игра в Палиндром:
            Палиндром - это слово или фраза, одинаково читающаяся в обоих направлениях.
            Например:
            - топот
            - а роза упала на лапу Азора
            Каждый игрок должен начать игру, введя слово "start"
            Партия игрока считается оконченной, когда игрок вводит слово "stop"
            """);
    public static final String INPUT_PLAYER_NAME_REQUIREMENT = "Введите имя игрока: ";
    public static final String EMPTY_NAME = "";
    public static final String START_GAME = "Партия началась";
    public static final String PALINDROME_DETECTED = "Это палиндром";
    public static final String NOT_PALINDROME_PHRASE = "Это не палиндром";

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var palindromeList = new HashSet<String>();
        var playerList = new HashMap<String, Integer>();
        System.out.println(GREETING);
        String playerName = EMPTY_NAME;
        while (scanner.hasNextLine()) {
            var inputWord = scanner.nextLine();
            if (inputWord.equals(START)) {
                System.out.println(INPUT_PLAYER_NAME_REQUIREMENT);
                playerName = scanner.nextLine();
                if (!playerList.containsKey(playerName)) {
                    playerList.put(playerName, 0);
                }
                System.out.println(START_GAME);
            } else if (inputWord.equals(STOP)) {
                System.out.println(GREETING);
            } else {
                var palindromeWord = inputWord.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll(" ", "");
                boolean isPalindrome = true;
                char[] a = palindromeWord.toCharArray();
                int size = a.length;
                if (size < 3) {
                    isPalindrome = false;
                }
                for (int i = 0; i < size; i++) {
                    if (a[i] != a[size - i - 1]) {
                        isPalindrome = false;
                        break;
                    }
                }
                if (isPalindrome) {
                    System.out.println(PALINDROME_DETECTED);
                    if (!palindromeList.contains(palindromeWord)) {
                        if (!playerName.equals(EMPTY_NAME)) {
                            palindromeList.add(palindromeWord);
                            playerList.put(playerName, playerList.get(playerName) + palindromeWord.length());
                        }
                        var rez = playerList.entrySet().stream().filter(player -> !player.getKey().equals("")).sorted(new Comparator<Map.Entry<String, Integer>>() {
                            @Override
                            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                return o2.getValue() - o1.getValue();
                            }
                        }).limit(5).toList();
                        if (!rez.isEmpty()) {
                            System.out.println(rez);
                        }
                    }
                } else {
                    System.out.println(NOT_PALINDROME_PHRASE);
                }
            }
        }
    }
}