# E-Commerce Order System

DDD modular monolith built with Spring Boot, Gradle, Java 17, JPA, Flyway, Swagger/OpenAPI, HATEOAS, H2, and PostgreSQL.

## Modules

- `order` is the core/orchestration module.
- `customer`, `product`, and `inventory` are supporting modules.

## Run locally

```powershell
.\gradlew.bat bootRun
```

Default profile is `h2`.

Swagger UI:

`http://localhost:8080/swagger-ui.html`

H2 Console:

`http://localhost:8080/h2-console`

## Run with Docker

```powershell
docker compose up --build
```

Swagger UI in Docker:

`http://localhost:8080/swagger-ui.html`

## Key workflows

- CRUD for customers, products, inventory items, and orders.
- `POST /api/orders` validates customer existence, product existence, and inventory stock before creating an order.
- Order responses include aggregated customer and product information plus HATEOAS links.

## Design artifacts

- `docs/ecommerce-order-system.puml`
- `docs/ecommerce-order-system.png`
