# Hibernate E-Commerce

A simple e-commerce application developed using Java, Hibernate ORM, JPA, Maven, and MySQL.

## Technologies Used

* Java 25
* Hibernate ORM 7.1.4.Final
* JPA
* MySQL
* Maven
* JUnit 5
* IntelliJ IDEA

## Entities

The project contains the following entities:

* Category
* Product
* Users
* Orders
* OrderDetails

## Entity Relationships

* One Category can have many Products.
* Many Products belong to one Category.
* One User can have many Orders.
* Many Orders belong to one User.
* One Order can have many OrderDetails.
* Many OrderDetails belong to one Order.
* Each OrderDetail is associated with one Product.

## Features

* Hibernate ORM configuration
* MySQL database connectivity
* Entity mapping using JPA annotations
* CRUD operations for Categories
* CRUD operations for Products
* CRUD operations for Users
* CRUD operations for Orders
* Password hashing using SHA-256
* Creation of orders with multiple order details
* Fetching orders with associated users and products
* JUnit test for CRUD operations and entity relationships

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.ecommerce
│   │       ├── Main.java
│   │       ├── entity
│   │       │   ├── Category.java
│   │       │   ├── Product.java
│   │       │   ├── Users.java
│   │       │   ├── Orders.java
│   │       │   └── OrderDetails.java
│   │       ├── crud
│   │       │   ├── CategoryCRUD.java
│   │       │   ├── ProductCRUD.java
│   │       │   ├── UsersCRUD.java
│   │       │   └── OrdersCRUD.java
│   │       └── util
│   │           └── HibernateUtil.java
│   └── resources
│       ├── hibernate.cfg.example.xml
│       └── schema.sql
│
└── test
    └── java
        └── com.ecommerce.test
            └── HibernateTest.java
```

## Database Setup

1. Install and start MySQL.
2. Create the database:

```sql
CREATE DATABASE hibernate_ecommerce;
```

3. Copy `src/main/resources/hibernate.cfg.example.xml` and create a local file named `hibernate.cfg.xml`.
4. Update the MySQL username and password in the local `hibernate.cfg.xml`.
5. Run the application or tests.

The local `hibernate.cfg.xml` is ignored by Git because it contains database credentials.

Hibernate is configured to automatically create or update the required tables using:

```xml
<property name="hibernate.hbm2ddl.auto">
    update
</property>
```

The project also contains `schema.sql` as the database schema reference.

## Running the Tests

From IntelliJ IDEA, run the Maven `test` lifecycle goal.

If Maven is installed on the system, you can also run:

```bash
mvn test
```

The test creates sample categories, products, users, orders, and order details, then fetches the order and verifies the associated user and products.

## Password Security

User passwords are hashed using SHA-256 before being persisted in the database.

## Author

Chirashri Karmakar
