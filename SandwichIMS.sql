DROP DATABASE IF EXISTS SandwichIMS;

CREATE DATABASE SandwichIMS;

USE SandwichIMS;


CREATE TABLE Product (
	ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(50),
    LastUpdated DATETIME,
    Quantity INT
);


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
('Blake', 'Norman', 'bnorman', 'MasterPassword', TRUE),
('Derrick', 'Strover-Duncan', 'dstrover', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE),
('Elias', 'Gomez', 'egomez', '73567e977fbeb14cb66974484e2cf1ae7596b8744bdb7ddfd33457a0a44d5e44', TRUE);

