# Пивной магазин

## Overview

Приложение для предоставления возможности покупки и продажи пива.

## Сущности

### Пиво (Beer):

Поля:   
- Тип
- Название
- Крепость
- Объем
- Цена
- Описание
- Пивоварня
- Количество на складе
    
### Покупатель (Customer):

Поля:
- ФИО
- Email
- Пароль
- Дата рождения

### Заказ (Order):

Поля:
- Покупатель
- Статус
- Общая стоимость

### Администратор (Administrator):

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
  "id" : 1
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
  "id" : 1
}
```

### GPBS-3 Как "Покупатель" я хочу получить список продаваемого пива, и в результате получаю его   

Request:

`GET /beer-shop-app/beer`

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

`POST /beer-shop-app/сustomer/order/beer/${beerId}`

```
{
    "id": 1,
    "fio": "Петров Петр Петрович",
    "email": "example@email.com",
    "processed": false,
    "totalCost": 6,
    "order": [
        {
            "id" : 1, 
            "type" : "Трипель",
            "name" : "Maredsous 10° Triple",
            "alcohol": "10.00%",
            "volume" : "0.5",
            "price" : 3,
            "description" : "Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.",
            "brewery" : "Abbaye de Maredsous",
            "count": 2
        }
    ]
},
``` 


Response:
`201 CREATED`

### GPBS-5 Как "Администратор" я хочу добавить новый сорт пива в список продаж, и, если такого пива нет, добавляю его

Request: 
    
`POST /beer-shop-app/beer`

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

```
{
   "id" : 3
}
```

### GPBS-6 Как "Администратор" я хочу удалить сорт пива из списка продаж, и, если такой сорт присутствует, удаляю его

Request: 
    
`DELETE /beer-shop-app/beer/${beerId}`

`Headers: beerId=1`

Response: `200 OK`

### GPBS-7 Как "Администратор" я хочу изменить статус заказа на "Обработано", и, если заказ не обработан, меняю его статус

Request: 
    
`PATCH /beer-shop-app/admin/orders/1`

`Headers: orderId=1`

```
{
    "processed": true
}
```
    
Response: `200 OK`

```
{
    "id": 1
}
```

### GPBS-8 Как "Администратор" я хочу изменить цену на пиво, и если такой сорт есть, меняю цену

Request: 
    
`PATCH /beer-shop-app/beer/${beerId}`

`Headers: beerId=1`
    
```    
{
    "price": 3.15
}
```

Response: `200 OK`

```
{
    "id" : 1
}
```
