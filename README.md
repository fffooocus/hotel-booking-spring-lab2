# Hotel Booking System - Spring Lab2

## Опис

Система замовлення готелю, реалізована за допомогою Spring Boot, Spring Data JPA, Hibernate, PostgreSQL та Liquibase.

Система дозволяє:
- створювати бронювання;
- знаходити доступні номери;
- підтверджувати заявки;
- формувати рахунок.

## Технології

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Liquibase
- Maven

## Структура проєкту

```text
hotel-booking-spring-lab2/
 ├── src/
 ├── pom.xml
 └── README.md
```

## Налаштування БД

Створити базу даних:

```sql
CREATE DATABASE hotel_db;
```

Таблиці створюються автоматично через Liquibase.

## Запуск

```bash
mvn spring-boot:run
```

## Endpoints

### Створення бронювання

```http
POST /api/bookings
```

Приклад JSON:

```json
{
  "userId": 1,
  "places": 2,
  "roomClass": "STANDARD",
  "checkIn": "2026-05-20",
  "checkOut": "2026-05-25"
}
```

### Підтвердження бронювання

```http
PUT /api/bookings/1/approve
```

### Перегляд бронювань

```http
GET /api/bookings
```