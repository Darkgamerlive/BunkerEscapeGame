package com.company;

public class Item {
    String name;
    String description;
    Moveable moveable;
    Item itemIn = null;

    public Item(String name, String description, Moveable moveable) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }

    public void setItemIn(Item itemIn) {
        this.itemIn = itemIn;
    }

    public Item getItemIn() {
        return itemIn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Moveable getMoveable() {
        return moveable;
    }
}
