package com.company;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Item> items = new ArrayList<>();


    public void add(Item item) {
        this.items.add(item);
    }

    public void remove(Item item) {
        this.items.remove(item);
    }

    public void show() {
        if (items.size() < 1) {
            System.out.println("Инвентарь пуст");
        }
        for (int x = 0; x < items.size(); x++) {
            System.out.printf("%d)", x + 1);
            System.out.println(items.get(x).getName());
            System.out.println("описание: " + items.get(x).getDescription());
        }
    }



    public Item showDescription(String itemName) {
        Item bufferItemIn = null;
        for (Item item : items) {
            if (itemName.equals(item.getName())) {
                System.out.println("описание: " + item.getDescription());
                if (item.getItemIn() != null) {
                    item.setDescription("здесь пусто");
                    bufferItemIn = item.getItemIn();
                    item.setItemIn(null);
                    return bufferItemIn;
                }
            }
        }
        return bufferItemIn;
    }

    public Item find(String item) {
        for (Item value : this.items) {
            if (item.equals(value.getName())) {
                return value;
            }
        }
        return null;
    }
}
