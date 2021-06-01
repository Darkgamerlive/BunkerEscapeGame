package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Actions {

    public void action (Player player) {
        System.out.println("Вы просыпаетесь от сильной головной боли... На затылке рана.");

        player.lookAround();
        Scanner scanner = new Scanner(System.in);
        String action = "";

        while (!action.equals("Выйти из игры")) {
            action = scanner.nextLine();

            List<String> words = Arrays.asList(action.split(" "));
            for (int x = 0; x < words.size(); x++) {
                switch (words.get(x)) {
                    case "Взять":
                        if (words.size() > 1) {
                            player.take(words.get(x + 1));
                        } else {
                            System.out.println("Что взять?");
                        }
                        break;
                    case "Идти":
                        if (words.size() > 1) {
                            player.go(words.get(x + 1));
                            if (player.location.getName().equals("Пустыня")) {
                                System.out.println("Выбравшись из бункера, вас ждет большая дорога приключений.\n" +
                                        "Поздравляем, вы прошли игру!");
                                return;
                            }
                            player.lookAround();
                        } else {
                            System.out.println("Куда?");
                        }
                        break;
                    case "Осмотреться":
                        player.lookAround();
                        break;
                    case "Осмотреть":
                        if (words.size() < 2) {
                            System.out.println("Не могу");
                        } else {
                            Item bufferItem = player.location.inventory.showDescription(words.get(x + 1));
                            if (bufferItem != null)
                                player.location.putOn(bufferItem);
                        }
                        break;
                    case "Инвентарь":
                        System.out.println("Мой инвентарь: ");
                        player.inventory();
                        break;
                    case "Использовать":
                        if (words.size() < 3) {
                            System.out.println("Не получается");
                        } else
                            player.use(words.get(x + 1), words.get(x + 2));
                        break;
                }
            }
        }
        System.out.println("Пока.");
    }
}
