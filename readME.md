# 🥪 DELI-cious

> *A modern, interactive sandwich ordering web app inspired by your favorite delivery services.*

---

## 🎯 Project Overview

DELI-cious is a Spring Boot (Java 21, Spring 3.4.6) web application that simulates an online sandwich ordering experience. It features:

* **Dynamic menu builder**: choose bread, size, meats, cheeses, toppings, sauces, sides.
* **Reactive preview**: instant price updates as you select options (powered by Alpine.js & TailwindCSS).
* **Order summary**: elegant confirmation screen mimicking leading food‑delivery UIs.
* **REST + Thymeleaf**: DTO‑driven controllers with both JSON endpoints (`/api/...`) and server‑rendered HTML (`/orders/...`).
* **PostgreSQL persistence**: JPA entities (`Order`, `Sandwich`, `Drink`, `Chip`) stored in a robust, relational schema.

---

## 🚀 Key Features

| Feature               | Description                                                                                |
| --------------------- | ------------------------------------------------------------------------------------------ |
| **Bread & Size**      | Pick from Wheat, White, Rye, Sourdough. Sizes: 4", 8", 12" with tiered pricing.            |
| **Meats & Cheeses**   | Select from an array of premium meats & cheeses. Extra‑charge logic scales with your size. |
| **Toppings & Sauces** | Load up on free veggies and sauces (lettuce, tomato, ranch, vinaigrette, and more).        |
| **Sides & Drinks**    | Add au jus or sauce; choose drink size (Small/Medium/Large) with preset prices.            |
| **Live Pricing**      | Real‑time total calculation as you customize (no page reload).                             |
| **Order Persistence** | Save and retrieve orders via `/api/orders` (DTO JSON) or `/orders` (Thymeleaf form).       |

---

## 📦 Architecture & Tech

* **Backend**: Java 21, Spring Boot 3.4.6, Spring Data JPA, Lombok
* **Database**: PostgreSQL (via HikariCP)
* **Frontend**: Thymeleaf + Alpine.js + TailwindCSS
* **Packaging**: Maven (`pom.xml`)

**Layers:**

1. **Controller** (REST & MVC)
2. **Service** (business logic)
3. **Repository** (JPA interfaces)
4. **Model/DTO** (entities, data transfer)

---

## 🔧 Setup & Run

1. **Clone** the repo:

   ```bash
   git clone https://github.com/yourorg/deli-cious.git
   cd deli-cious
   ```
2. **Configure** PostgreSQL in `src/main/resources/application.yml`:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/delicious
       username: youruser
       password: yourpass
     jpa:
       hibernate:
         ddl-auto: update
   ```
3. **Build & Run**:

   ```bash
   ./mvnw clean package
   ./mvnw spring-boot:run
   ```
4. **Browse**:

    * **Home**: `http://localhost:8080/`
    * **Order form**: `http://localhost:8080/orders/new`
    * **API docs**: browse `/api/...` endpoints via Postman or curl.

---

## 🔗 Endpoints

* **Thymeleaf MVC**

    * `GET  /` → home page
    * `GET  /orders/new` → create form
    * `POST /orders` → submit and show summary

* **JSON REST**

    * `GET  /api/orders` → list all orders
    * `POST /api/orders` → create order (JSON body)
    * `GET  /api/orders/{id}` → fetch order by ID
    * `PUT  /api/orders/{id}` → update order
    * `DELETE /api/orders/{id}` → delete order

—and similarly for `/api/sandwiches`, `/api/drinks`, `/api/chips`.

---

## 🎨 Frontend Pages

1. **Home**: bold call‑to‑action with door‑dash style button.
2. **Create Order**: two‑column layout (form & live preview).
3. **Summary**: confirmation card with order breakdown and total.

---

## 🤝 Contributing

1. Fork the repo
2. Create a branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add feature'`)
4. Push (`git push origin feature/YourFeature`)
5. Open a Pull Request

Please follow the existing code style and write unit tests for new features.

---

## 📄 License



This project is released under the MIT License. See `LICENSE` for details.
