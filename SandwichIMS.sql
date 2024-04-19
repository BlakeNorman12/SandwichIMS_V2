DROP DATABASE IF EXISTS SandwichIMS;

CREATE DATABASE SandwichIMS;

USE SandwichIMS;


CREATE TABLE Product (
	ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(50),
    LastUpdated DATETIME,
    Quantity INT,
    UpdatedBy VARCHAR(50),
    ShelfLife VARCHAR(50)
);


CREATE TABLE Employee (
	employeeID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    isManager BOOLEAN
);

INSERT INTO Employee (firstName, lastName, username, password, isManager) VALUES
('Jordan', 'Levercom', 'jlevercom', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE),
('Blake', 'Norman', 'bnorman', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE),
('Derrick', 'Strover-Duncan', 'dstrover', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE),
('Elias', 'Gomez', 'egomez', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE);

INSERT INTO Product (ProductName, LastUpdated, Quantity, UpdatedBy, ShelfLife) VALUES
("White Bread", "2024-04-18", 0, "Jordan Levercom", "1 day"),
("Wheat Bread", "2024-04-18", 0, "Blake Norman", "1 day"),
("Cheddar Cheese", "2024-04-18", 0, "Derrick Strover-Duncan", "1 week"),
("Turkey", "2024-04-18", 0, "Elias Gomez", "1 week");


DROP DATABASE IF EXISTS TestSandwichIMS;

CREATE DATABASE TestSandwichIMS;

USE TestSandwichIMS;

CREATE TABLE Product (
	ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(50),
    LastUpdated DATETIME,
    Quantity INT,
    UpdatedBy VARCHAR(50),
    ShelfLife VARCHAR(50)
);

CREATE TABLE Employee (
	employeeID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    isManager BOOLEAN
);

INSERT INTO Employee (firstName, lastName, username, password, isManager) VALUES
('test', 'guy', 'testGuy', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', TRUE),
('add', 'test', 'testGuyNew', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', TRUE);

INSERT INTO Product (ProductName, LastUpdated, Quantity, UpdatedBy, ShelfLife) VALUES
("Test Product", "2024-04-18", 1, "Test Employee", ""),
("Test Add Product", "2024-04-18", 1, "Test Employee", "Test Shelf Life");
