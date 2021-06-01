package com.company;

import java.util.HashMap;
import java.util.Map;

public class Player {
    Location location;
    Inventory inventory;

    public Player() {
        Item item1 = new Item("Шкаф","В шкафу лежит канистра с керосином", Moveable.STATIONARY);
        Item item2 = new Item("Канистра",
                "Канистра примерно наполовину заполнена керосином. Невозможно открыть руками", Moveable.MOBILE);
        item1.setItemIn(item2);
        Item item3 = new Item("Койки","На койках ничего необычного, просто разбросаны простыни", Moveable.STATIONARY);
        Inventory playerInventory = new Inventory();
        Inventory locationInventory = new Inventory();
        locationInventory.add(item1);
        locationInventory.add(item3);

        Item itemSofa = new Item("Диван", "За диваном виднеется ключ-карта", Moveable.STATIONARY);
        Item itemKeyCard = new Item("Ключ-карта", "Видимо что-то здесь открывается с помощью этой карты", Moveable.MOBILE);
        itemSofa.setItemIn(itemKeyCard);
        Item itemBookCase = new Item("Книжный шкаф", "Просто книжный шкаф", Moveable.STATIONARY);
        Item itemBrokenDoor = new Item("СломаннаяДверь", "Дверь заклинило, за ней что-то есть", Moveable.STATIONARY);
        Inventory locationInventoryBigRoom = new Inventory();
        locationInventoryBigRoom.add(itemSofa);
        locationInventoryBigRoom.add(itemBookCase);
        locationInventoryBigRoom.add(itemBrokenDoor);
        Map<Direction,Location> directions = new HashMap<>();
        directions.put(Direction.NORTH,new Location("Зал","Похоже на комнату отдыха, куда же все подевались?",
                locationInventoryBigRoom, new HashMap<>()));

        Item itemCupboard = new Item("Шкаф", "И отсюда все забрали", Moveable.STATIONARY);
        Item itemTable = new Item("Стол", "На столе пустые тарелки и емкости из вод воды", Moveable.STATIONARY);
        Inventory locationInventoryKitchen = new Inventory();
        locationInventoryKitchen.add(itemCupboard);
        locationInventoryKitchen.add(itemTable);
        Location locationKitchen = new Location("Кухня", "Пустая кухня, здесь нечего искать", locationInventoryKitchen, new HashMap<>());

        Item itemCrowbar = new Item("Ломик","Хмм... Может он мне поможет открыть сломанную дверь?", Moveable.MOBILE);
        Item itemGenerator = new Item("Генератор", "Выключен, похоже его бак пуст", Moveable.STATIONARY);
        Inventory locationInventoryGeneratorRoom = new Inventory();
        locationInventoryGeneratorRoom.add(itemGenerator);
        locationInventoryGeneratorRoom.add(itemCrowbar);
        Location locationGeneratorRoom = new Location("Генераторная", "Здесь стоит генератор, здесь можно включить основное питание помещений",
                locationInventoryGeneratorRoom, new HashMap<>());
        locationGeneratorRoom.directions.put(Direction.NORTH, locationKitchen);
        locationKitchen.directions.put(Direction.SOUTH, locationGeneratorRoom);

        Item itemElevatorDown = new Item("Лифт", "Выключен, лифт не работает без основного питания", Moveable.STATIONARY);
        Inventory locationInventoryElevatorDown = new Inventory();
        locationInventoryElevatorDown.add(itemElevatorDown);
        Location locationElevatorDown = new Location("Нижний этаж лифта", "В этой комнате находится только лифт, в надписью \"ВЫХОД\"",
                locationInventoryElevatorDown, new HashMap<>());
        locationElevatorDown.directions.put(Direction.WEST, locationKitchen);
        locationKitchen.directions.put(Direction.EAST,locationElevatorDown);

        this.location = new Location("Комната","В комнате горят красные лампы, вокруг койки. Видно, что " +
                "отсюда уходили очень быстро.", locationInventory,directions);
        this.inventory = playerInventory;
        this.location.directions.get(Direction.NORTH).directions.put(Direction.SOUTH, this.location);
        this.location.directions.get(Direction.NORTH).directions.put(Direction.UP, locationKitchen);
        locationKitchen.directions.put(Direction.DOWN, this.location.directions.get(Direction.NORTH));

    }

    public void lookAround() {
        System.out.println(location.description + "\nВ локации " + location.getName() + " находится: ");
        location.inventory.show();
        if (location.directions != null) {
            System.out.println("Вы можете пойти: ");
            if (location.directions.get(Direction.DOWN) != null) {
                System.out.println("Вниз - " + location.directions.get(Direction.DOWN).getName());
            }
            if (location.directions.get(Direction.NORTH) != null) {
                System.out.println("Север - " + location.directions.get(Direction.NORTH).getName());
            }
            if (location.directions.get(Direction.SOUTH) != null) {
                System.out.println("Юг - " + location.directions.get(Direction.SOUTH).getName());
            }
            if (location.directions.get(Direction.WEST) != null) {
                System.out.println("Запад - " + location.directions.get(Direction.WEST).getName());
            }
            if (location.directions.get(Direction.EAST) != null) {
                System.out.println("Восток - " + location.directions.get(Direction.EAST).getName());
            }
            if (location.directions.get(Direction.UP) != null) {
                System.out.println("Наверх - " + location.directions.get(Direction.UP).getName());
            }
        }
        else System.out.println("Никуда нельзя идти");
    }
    public void go(String directionName) {
        if (location.findNext(directionName) != null){
            this.location = location.findNext(directionName);
        }
        else System.out.println("Не могу");
    }
    public void take(String itemName) {
        Item pickUpItem = this.location.pickUp(itemName);
        if ( pickUpItem != null) {
            this.inventory.items.add(pickUpItem);
            System.out.println("Взял " + pickUpItem.getName());
        }
        else
            System.out.println("Не могу");
    }

    public void inventory (){
        this.inventory.show();
    }


    public void use (String object, String subject) {
        Item itemObject = this.inventory.find(object);
        if (itemObject != null) {
            Item itemSubject1 = this.inventory.find(subject);
            Item itemSubject2 = this.location.inventory.find(subject);
            Combo combo = null;
            if (itemSubject1!= null) {
                combo = new Combo(itemObject, itemSubject1);
            }
            else if (itemSubject2 != null){
                combo = new Combo(itemObject,itemSubject2);
            }
            if (combo != null) {
                if (combo.resulting() != null) {
                    if (combo.resulting().result.getName().equals("Взломанная дверь")) {
                        this.location.inventory.add(combo.resulting().result);
                        this.location.inventory.remove(itemSubject2);

                        Item itemShelves = new Item("Полки", "Здесь лежит странный ключ", Moveable.STATIONARY);
                        Item itemKey = new Item("Ключ", "Похоже этот ключ мне пригодится", Moveable.MOBILE);
                        itemShelves.setItemIn(itemKey);
                        Inventory locationInventoryPantry = new Inventory();
                        locationInventoryPantry.add(itemShelves);
                        Location locationPantry = new Location("Кладовая", "Отсюда недавно все вынесли, почти ничего не осталось", locationInventoryPantry,new HashMap<>() );
                        this.location.directions.put(Direction.WEST, locationPantry);
                        locationPantry.directions.put(Direction.EAST, this.location);

                    }
                    else if (combo.resulting().result.getName().equals("Открытая дверь")) {
                        this.location.inventory.add(combo.resulting().result);
                        this.location.inventory.remove(itemSubject2);

                        Item itemControlDesk = new Item("ПультУправления", "С его помощью я смогу открыть эти ворота, но здесь нужна ключ-карта", Moveable.STATIONARY);
                        Item itemGateway = new Item("Большие ворота", "Они закрыты, похоже за ними выход",Moveable.STATIONARY);
                        Inventory locationInventoryGatewayRoom = new Inventory();
                        locationInventoryGatewayRoom.add(itemControlDesk);
                        locationInventoryGatewayRoom.add(itemGateway);
                        Location locationGatewayRoom = new Location("Шлюз-комната", "За этой комнатой выход из бункера",
                                locationInventoryGatewayRoom, new HashMap<>());
                        locationGatewayRoom.directions.put(Direction.SOUTH, this.location);
                        this.location.directions.put(Direction.NORTH, locationGatewayRoom);

                    }
                    else if (combo.resulting().result.getName().equals("Открытые большие ворота")) {
                        this.location.inventory.add(combo.resulting().result);
                        this.location.inventory.remove(itemSubject2);

                        Inventory locationInventoryDest = new Inventory();
                        Location locationDest = new Location("Пустыня", "Конец",
                                locationInventoryDest, new HashMap<>());
                        locationDest.directions.put(Direction.SOUTH, this.location);
                        this.location.directions.put(Direction.NORTH, locationDest);

                    }
                    else if (combo.resulting().message.equals("Вы заправили и включили генератор")) {
                        this.location.inventory.add(new Item("Генератор", "Включенный генератор", Moveable.STATIONARY));
                        this.location.inventory.remove(itemSubject2);
                        Item itemElevatorUp = new Item("Лифт", "Лифт, ведет обратно вниз", Moveable.STATIONARY);
                        Item itemLockedDoor = new Item("ЗакрытаяДверь", "В дверь встроен большой замок, без ключа ее не открыть", Moveable.STATIONARY);
                        Inventory locationInventoryElevatorUp = new Inventory();
                        locationInventoryElevatorUp.add(itemElevatorUp);
                        locationInventoryElevatorUp.add(itemLockedDoor);
                        Location locationElevatorUp = new Location("Верхний этаж лифта", "В этой комнате находится только лифт, в надписью \"ВХОД В БУНКЕР\"",
                                locationInventoryElevatorUp, new HashMap<>());
                        locationElevatorUp.directions.put(Direction.DOWN, this.location.directions.get(Direction.NORTH).directions.get(Direction.EAST));
                        this.location.directions.get(Direction.NORTH).directions.get(Direction.EAST).directions.put(Direction.UP, locationElevatorUp);
                    }
                    else {
                        this.inventory.items.add(combo.resulting().result);
                        if (itemObject.getMoveable().equals(Moveable.MOBILE) && !(itemObject.getName().equals("Ломик"))) {
                            this.inventory.remove(itemObject);
                        }
                        if (itemSubject1 != null) {
                            if (itemSubject1.getMoveable().equals(Moveable.MOBILE)) {
                                this.inventory.items.remove(itemSubject1);
                            }
                        }
                        else if (itemSubject2 != null) {
                            if (itemSubject2.getMoveable().equals(Moveable.MOBILE)) {
                                this.location.inventory.items.remove(itemSubject2);
                            }
                        }
                    }
                    System.out.println(combo.message);
                }
            }
        }
    }
}
