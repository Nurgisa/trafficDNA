## Testing task from interview TrafficDNA

Средствами java реализовать:
HTTP сервер, умеющий обрабатывать 3 запроса:
1. Get /userinfo/{user_id} - возвращает топ 20 результатов пользователя на всех уровнях в порядке убывания result, level_id (формат: JSON).
2. Get /levelinfo/{level_id} - возвращает топ 20 пользователей и их результаты на выбранном уровне в порядке убывания result, user_id (формат: JSON).
3. Put /setinfo - принимает 3 параметра в json (user_id, level_id, result) устанавливает результат пользователя на уровне.

пример:
/setinfo
body: {"user_id":1,"level_id":1,"result":55}

/setinfo
body: {"user_id":1,"level_id":2,"result":8}

/setinfo
body: {"user_id":1,"level_id":3,"result":15}

/setinfo
body: {"user_id":2,"level_id":3,"result":22}

/levelinfo/3
[
{"user_id":2,"level_id":3,"result":22},
{"user_id":1,"level_id":3,"result":15}
]

/userinfo/1
[
{"user_id":1,"level_id":1,"result":55},
{"user_id":1,"level_id":3,"result":15},
{"user_id":1,"level_id":2,"result":8}
]

--- Общие требования к приложению:
-Должны выводиться только топ результаты.
-Приложение должно корректно работать в многопоточной среде.

--- Дополнительные требования к приложению, не обязательны но будут плюсом:
-Вся логика построения топ результатов должна быть реализована на java.
-Все данные должны храниться в памяти.
-При выполнении задания база данных не используется.
-Должны храниться только топ результаты.

--- Требования к коду:
- Выбор библиотек логирования, тестирования, а так же сборщика проекта свободный.
- Должен прилагаться скрипт сборки и скрипт запуска приложения.
- Приложение должно быть совместимо с JDK11.
- Выбор веб сервера свободный.


