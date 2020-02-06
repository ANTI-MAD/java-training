# Пивной магазин

## Overview

Приложение для предоставления возможности покупки и продажи пива.

## User Stories

В первую очередь начнем с работы "Покупатель" с системой.
Сложная аутентификация  и работа с токенами пока вне скоупа, предпологается что пользователь будет передавать свой id как header.

### GPBS-1 Как "Покупатель" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь

Request:

`POST /java-training-app/buyer/sign-up`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty",
  "fio" : "Пупкин Василий Иванович",
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

`POST /java-training-app/buyer/sign-in`
```json
{
  "email" : "vasya@email.com",
  "password" : "qwerty"
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

`GET /java-training-app/beer/list`

Response:
`200 OK`
```json
[
  {
    "id" : 1, 
    "title" : "Жигулевское",
    "description" : "Цена, объём, крепость",
    "manufacturer" : "ПивоБеларусь" 
  }
]
```

### GPBS-4 Как "Покупатель" я хочу добавить пиво из списка в список заказов, и в результате добавляю

Request:

`GET /java-training-app/beer/${beerId}/orderList`

`Headers: userId=1` 


Response:
`200 OK`
