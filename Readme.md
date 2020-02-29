# Пивной магазин

## Overview

Приложение для предоставления возможности покупки и продажи пива.

## Сущности

### Пиво:

Поля:   
- Тип
- Название
- Крепость
- Объем
- Цена
- Описание
- Пивоварня
- Количество на складе
    
### Покупатель:

Поля:
- ФИО
- Email
- Пароль
- Дата рождения

### Заказ:

Поля:
- Покупатель
- Статус
- Общая стоимость
- Список покупок
     
### Администратор:

Пользователь, который следит за поступлениями заказов, добавляет и удаляет сорта пива из списка, а также, при необходимости, изменяет цены.

Поля:
- ФИО
- Email
- Пароль
- Дата рождения

## User Stories

### GPBS-1 Как "Покупатель" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь

Request:

`POST /beer-shop-app/customer/sign-up`
```json
{
  "email" : "example@email.com",
  "password" : "password",
  "fio" : "Петров Петр Петрович",
  "birthDate" : "19.01.1995",
}
```

Response:
`201 CREATED`
```json
{
  "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlQGVtYWlsLmNvbSIsImV4cCI6MTU4Mjg0NzQzOSwiaWF0IjoxNTgyODExNDM5fQ.bjRI9p-17lPdNJUSrMlL3OfAPwpvOmVIlkqiw-0Jf8I"
}
```

Если пользоветель с таким email уже существует, то будет возвращено:

Response:
`403 Forbidden`
```json
{
  "message" : "User with that email already exists"
}
```

### GPBS-2 Как "Покупатель", будучи зарегистрированным пользователем, я хочу войти в систему, и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

`POST /beer-shop-app/customer/sign-in`
```json
{
  "email" : "example@email.com",
  "password" : "password"
}
```

Response:
`200 OK`
```json
{
  "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlQGVtYWlsLmNvbSIsImV4cCI6MTU4Mjg0NzQzOSwiaWF0IjoxNTgyODExNDM5fQ.bjRI9p-17lPdNJUSrMlL3OfAPwpvOmVIlkqiw-0Jf8I"
}
```

Если email либо пароль неверные, то пользователь получит следующий ответ:

Response:
`403 Forbidden`
```json
{
  "message" : "Wrong email or password"
}
```

### GPBS-3 Как "Покупатель" я хочу получить список продаваемого пива, и в результате получаю его   

Request:

`GET /beer-shop-app/beers`

Response:
`200 OK`
```json
[
  {
    "id" : 1, 
    "type" : "Трипель",
    "name" : "Maredsous 10° Triple",
    "alcohol": "10.00%",
    "volume" : "0.5",
    "price" : 3,
    "description" : "Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.",
    "brewery" : "Abbaye de Maredsous",
    "stockBalance" : 20
  }
  {
    "id" : 2, 
    "type" : "Пшеничное",
    "name" : "Paulaner Hefe-Weißbier",
    "alcohol": "5.5%",
    "volume" : "0.5",
    "price" : 2.2,
    "description" : "Цвет золотой, непрозрачный. Обильная мелкая пена.",
    "brewery" : "Paulaner Brauerei",
    "stockBalance" : 32
  }
]
```

### GPBS-4 Как "Покупатель" я хочу добавить пиво из списка в список заказов, и в результате добавляю

Request:

`POST /beer-shop-app/beers/${beerId}/order`

```
{
    "id": 1,
    "customer_id": 1,
    "processed": false,
    "totalCost": 6,
    "shoppingList": [
        {
            "beer_id" : 1, 
            "amount": 2
        }
    ]
},
``` 


Response:
`201 CREATED`
```json
{
  "response" : "Beer Maredsous 10° Triple successfully added"
}
```

### GPBS-5 Как "Администратор" я хочу добавить новый сорт пива в список продаж, и, если такого пива нет, добавляю его

Request: 
    
`POST /beer-shop-app/beers/`

```    
{
    "type" : "Пшеничное",
    "name" : "Hoegaarden Wit-blanche",
    "alcohol": "4.9%",
    "volume" : "0.47",
    "price" : 1.5,
    "description" : "Цвет бледный, мутный. Пены почти нет.",
    "brewery" : "Paulaner Brauerei",
    "stockBalance" : 50
}
```

Response: `201 CREATED`
```json
{
   "response" : "Hoegaarden Wit-blanche Triple successfully added"
}
```

### GPBS-6 Как "Администратор" я хочу удалить сорт пива из списка продаж, и, если такой сорт присутствует, удаляю его

Request: 
    
`DELETE /beer-shop-app/beers/${beerId}`

`Headers: beerId=1`

Response: `200 OK`
```json
{
   "response" : "Maredsous 10° Triple"
}
```

### GPBS-7 Как "Администратор" я хочу изменить статус заказа на "Обработано", и, если заказ не обработан, меняю его статус

Request: 
    
`PUT /beer-shop-app/admin/orders/1`

`Headers: orderId=1`

```
{
    "processed": true
}
```
    
Response: `200 OK`

```
{
    "response": "Maredsous 10° Triple - true"
}
```

### GPBS-8 Как "Администратор" я хочу изменить цену на пиво, и если такой сорт есть, меняю цену

Request: 
    
`PUT /beer-shop-app/beers/${beerId}`

`Headers: beerId=1`
    
```    
{
    "price": 3.15
}
```

Response: `200 OK`

```
{
    "response" : "Price changed to 3.15"
}
```

### GPBS-9 Как "Администратор" я хочу изменить количество пива на складе, и если такой сорт пива есть, меняю количество

Request: 
    
`PUT /beer-shop-app/beers/${beerId}`

`Headers: beerId=1`
    
```    
{
    "stockBalance": 30
}
```

Response: `200 OK`

```
{
    "response" : "stockBalance successfully changed to 30"
}
```

Если такого пива не найдено, то будет возвращено

Response: `404 Not Found`

### GPBS-10 Как "Администратор" я хочу изменить название пива, и если такое название есть, меняю его

Request: 
    
`PUT /beer-shop-app/beers/${beerId}`

`Headers: beerId=1`
    
```    
{
    "name": "Hoegaarden Wit-blanche New"
}
```

Response: `200 OK`

```
{
    "response" : "Maredsous 10° Triple successfully changed to Hoegaarden Wit-blanche New"
}
```

Если такого пива не найдено, то будет возвращено

Response: `404 Not Found`
