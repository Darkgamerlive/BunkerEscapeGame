# BunkerEscapeGame

## Текстовый квест

### Краткий сюжет
Главный герой просыпается в заброшенном бункере, ему необходимо выбраться из него.

#### Возможности квеста (команды)

***Осмотреться***
Выводит в терминал описание текущей локации: список предметов и направлений.


***Осмотреть "Предмет"***
1. Выводит в терминал описание предмета.
2. Если в предмете что-то находилось, добавляется в инвентарь локации.
3. Изменяется описание предмета.


***Инвентарь***
Выводит в терминал описание предметов в инвентаре.


***Идти "Направление"***

Всего имеется 6 направлений:
1. Север
2. Юг
3. Восток
4. Запад
5. Наверх
6. Вниз
После перехода на следующую локацию выводит описание локации.


***Взять "Предмет"***

Проверяет, есть ли предмет на локации
Проверяет, можно ли взять предмет.
Если есть и можно взять, удаляет предмет из локации и добавяет в инвентарь игрока.   


***Использовать "Объект" "Субъект"***

1. Проверяет, находится ли объект в инвентаре игрока.
2. Проверяет, существует ли субъект.
3. Если существуют и субъект и объект, то проверяет возможность их взаимодействия.
4. Результатом выполнения команды может быть различным:
1) Появление новых локаций.
2) Появление результирующего предмета в инвентаре игрока.
5. Выводит в терминал сообщение после успешного взаимодействия.


***Выйти из игры***

Завершает работу программы.