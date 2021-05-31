package com.company;

public class Combo {
    Item object;
    Item subject;
    Item result;
    String message;

    public Combo(Item object, Item subject) {
        this.object = object;
        this.subject = subject;
    }

    public Combo resulting () {
        if (object.getName().equals("Ломик") && subject.getName().equals("Канистра")
                && subject.getDescription().equals("Канистра примерно наполовину заполнена керосином. Невозможно открыть руками")
        ) {
            result = new Item("Канистра","Открытая канистра, примерно наполовину заполнена керосином",Moveable.MOBILE);
            message = "Вы открыли канистру";
            return this;
        }
        else if (object.getName().equals("Канистра") && subject.getName().equals("Генератор")
                && object.getDescription().equals("Открытая канистра, примерно наполовину заполнена керосином")
        ) {
            result = new Item("Канистра","Пустая канистра",Moveable.MOBILE);
            message = "Вы заправили и включили генератор";
            return this;
        }
        else if (object.getName().equals("Ломик") && subject.getName().equals("СломаннаяДверь")) {
            result = new Item("Взломанная дверь","Теперь я могу пройти через нее",Moveable.STATIONARY);
            message = "Вы взломали дверь";
            return this;
        }
        else if (object.getName().equals("Ключ") && subject.getName().equals("ЗакрытаяДверь")) {
            result = new Item("Открытая дверь","Открытая дверь",Moveable.STATIONARY);
            message = "Повернув ключ в замке, дверь открылась";
            return this;
        }
        else if (object.getName().equals("Ключ-карта") && subject.getName().equals("ПультУправления")) {
            result = new Item("Открытые большие ворота","Ворота открылись",Moveable.STATIONARY);
            message = "Большие ворота бункера начали открываться, похоже я выбрался";
            return this;
        }
        return null;
    }
}
