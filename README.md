# Inventory Manager (JDBC)

A simple console-based Inventory Manager built in Java using JDBC to connect to a MySQL database. It allows you to add, view, update, and delete items in an inventory.

## ✨ Features
- ✅ Add new items (name, quantity, price)
- ✅ View all items in a tabular format
- ✅ Update item quantity by ID
- ✅ Delete items by ID
- ✅ Proper SQLException handling
- ✅ Uses try-with-resources to auto-close connections
- ✅ Uses PreparedStatement to prevent SQL Injection

## 🛠️ Technologies Used
- Java (JDK 21)
- MySQL Database
- JDBC (Java Database Connectivity)

## 🗄️ Database Setup
Run the following SQL commands before running the app:
```sql
CREATE DATABASE InventoryDB;
USE InventoryDB;
CREATE TABLE Item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL
);
