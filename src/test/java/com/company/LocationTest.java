package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    @org.junit.jupiter.api.Test
    void findNext() {
        LOGGER.info("Начало работы программы");
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

        Location actual = locationKitchen.findNext("Юг");
        Location expected = locationGeneratorRoom;
        assertEquals(expected,actual);
    }
}