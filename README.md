# Vacation Calculator
REST API, вычисляющий размер отпускных сотрудника 


## Содержание
  - [Технологии](#технологии)
  - [Функционал](#функционал)
  - [Входящие параметры](#входящие-параметры)
  - [Покрытие тестами](#покрытие-тестами)
  - [Автор проекта](#автор-проекта)


## Технологии
- [Java 11](https://www.java.com/ru/)
- [Spring Boot 3.2.4](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html)


## Функционал
Приложение принимает на вход среднегодовую заработную плату сотрудника, а также, дату начала и дату окончания отпуска. На основании этих данных вычисляется размер отпускных. Если в период отпуска есть официальные государственные праздники, оплата за этот день не рассчитывается, так как отпуск продлевается на количество таких дней.


## Входящие параметры
Передача входящиих данных осуществляется путем осуществления GET HTTP запроса на URL
```
http://localhost:8080/calculate
```
и передачи среднегодовой зарплаты, даты начала и окончания отпуска в качестве параметров salary, start, end соответственно. Например, 

```
localhost:8080/calculate?salary=100000&start=01.01.2024&end=15.01.2024
```
В случае корректного переданного запроса, ответом  будет являться размер отпускных сотрудника за указанный период с HTTP статусом "200 OK" в формате
```
23890,78
```
### Ограничения входящего сообщения:
- **Не должно быть пустым**

  В случае, если даты пустые, выбрасывается кастомное исключение EmptyVacationPeriodException с HTTP статусом "400 Bad Request" c указанием причины возникновения и времени возникновения
  ```
  {
    "responseMessageText": "Vacation start and end dates must not be empty",
    "time": "29-03-2024 20:10:11"
  }
  ```

- **Должно соответствовать формату даты "дд.мм.гггг"**
  
  В случае, если формат даты отличается от указанного, содержит другие разделители, указаны даты и месяцы, которых не WrongDateFormatException с HTTP статусом "400 Bad Request" c указанием причины возникновения и времени возникновения
  ```
  {
    "responseMessageText": "Wrong date format in request. You should use 'dd.mm.yyyy' date format",
    "time": "29-03-2024 20:13:20"
  }
  ```

- **Дата начала отпуска должна находиться раньше даты окончания отпуска, или совпадать с ней**
  
  В случае, если дата окончания отпуска находится раньше даты его начала, выбрасывается кастомное исключение WrongVacationPeriodException с HTTP статусом "400 Bad Request" c указанием причины возникновения и времени возникновения
  ```
  {
    "responseMessageText": "Start vacation date must be earlier than end vacation date",
    "time": "29-03-2024 20:17:43"
  } 
  ```
  

## Покрытие тестами
Приложение покрыто модульными тестами, проверяющими корректность работы сервисного класса, а именно:
- Проверка корректности вычислений отпускных
- Проверка корректности учета праздников в период отпуска

## Автор проекта
  - [Мылов Сергей](https://github.com/Mylov91) — Java-developer (Mylov91@yandex.ru)
