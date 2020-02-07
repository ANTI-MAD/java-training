# Пивной магазин

## Overview

Приложение для предоставления возможности покупки и продажи пива.

## User Stories

### GPBS-1 Как "Покупатель" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь

Request:

`POST /beerShop-app/customer/sign-up`
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

`POST /beerShop-app/customer/sign-in`
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

`GET /beerShop-app/beer/list`

Response:
`200 OK`
```json
[
  {
    "id" : 1, 
    "type" : "Трипель",
    "name" : "Maredsous 10° Triple",
    "alcohol": "10.00%",
    "price" : "3",
    "description" : "Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.",
    "brewery" : "Abbaye de Maredsous",
    "stockBalance" : 20
  }
  {
    "id" : 2, 
    "type" : "Пшеничное",
    "name" : "Paulaner Hefe-Weißbier",
    "alcohol": "5.5%",
    "price" : "2.2",
    "description" : "Цвет золотой, непрозрачный. Обильная мелкая пена.",
    "brewery" : "Paulaner Brauerei",
    "stockBalance" : 32
  }
]
```

### GPBS-4 Как "Покупатель" я хочу добавить пиво из списка в список заказов, и в результате добавляю

Request:

`GET /beerShop-app/beer/${beerId}/orderList`

`Headers: userId=1` 


Response:
`200 OK`
