package com.company;


import java.util.Map;

public class Location {
    String name;
    String description;
    Inventory inventory;
    Map<Direction,Location> directions;

    public String getName() {
        return name;
    }

    public Location(String name, String description, Inventory inventory, Map<Direction, Location> directions) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.directions = directions;
    }

    public Location findNext(String directionName) {
        switch (directionName) {
            case "Север":
                if (directions.get(Direction.NORTH) != null) {
                    return (directions.get(Direction.NORTH));
                }
                break;
            case "Юг":
                if (directions.get(Direction.SOUTH) != null) {
                    return (directions.get(Direction.SOUTH));
                }
                break;
            case "Восток":
                if (directions.get(Direction.EAST) != null) {
                    return (directions.get(Direction.EAST));
                }
                break;
            case "Запад":
                if (directions.get(Direction.WEST) != null) {
                    return (directions.get(Direction.WEST));
                }
                break;
            case "Вниз":
                if (directions.get(Direction.DOWN) != null) {
                    return (directions.get(Direction.DOWN));
                }
                break;
            case "Наверх":
                if (directions.get(Direction.UP) != null) {
                    return (directions.get(Direction.UP));
                }
                break;
        }
        return (null);
    }

    public void putOn(Item item) {
        this.inventory.add(item);
    }

    public Item pickUp(String locationItem) {
        Item buffer = null;
        for (int i = 0; i < this.inventory.items.size(); i++) {
            if (locationItem.equals(this.inventory.items.get(i).getName()) && this.inventory.items.get(i).getMoveable().equals(Moveable.MOBILE)) {
                buffer = this.inventory.items.get(i);
                this.inventory.remove(this.inventory.items.get(i));
                return buffer;
            }
        }
        return buffer;
    }
}
