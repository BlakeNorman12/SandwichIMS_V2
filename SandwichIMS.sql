DROP DATABASE IF EXISTS SandwichIMS;

CREATE DATABASE SandwichIMS;

USE SandwichIMS;

CREATE TABLE Product (
	ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(50),
    LastUpdated DATE,
    Quantity INT,
    UpdatedBy VARCHAR(50)
);

INSERT INTO Product (ProductID, ProductName, LastUpdated, Quantity, UpdatedBy) VALUES
(1, "Bread", '2024-04-08', 25, "Jordan Levercom"),
(2, "Provolone", '2024-04-08', 50, "Blake Norman"),
(3, "Mayo", '2024-04-08', 40, "Blake Norman"),
(4, "Ham", '2024-04-08', 35, "Jordan Levercom"),
(5, "Turkey", '2024-04-08', 20, "Jordan Levercom"),
(6, "Avocado", '2024-04-08', 10, "Blake Norman"),
(7, "Peppers", '2024-04-08', 28, "Blake Norman"),
(8, "Cucumbers", '2024-04-08', 20, "Blake Norman"),
(9, "Cheddar", '2024-04-08', 80, "Blake Norman");

CREATE TABLE Employee (
	employeeID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    username VARCHAR(50),
    password VARCHAR(255),
    isManager BOOLEAN
);

INSERT INTO Employee (firstName, lastName, username, password, isManager) VALUES
('Jordan', 'Levercom', 'jlevercom', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE),
('Blake', 'Norman', 'bnorman', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE),
('Derrick', 'Strover-Duncan', 'dstrover', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE),
('Elias', 'Gomez', 'egomez', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE);

