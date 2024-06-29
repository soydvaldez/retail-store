-- Tabla de Clientes
CREATE TABLE Customers (
    customerID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(200),
    DateOfBirth Date,
    RegisterDate Date DEFAULT CURRENT_DATE

);

-- Tabla de Empleados
CREATE TABLE Employees (
    employeeID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    hireDate DATE
);

-- Tabla de Proveedores
CREATE TABLE Suppliers (
    supplierID INT PRIMARY KEY AUTO_INCREMENT,
    companyName VARCHAR(100),
    contactName VARCHAR(100),
    contactTitle VARCHAR(50),
    address VARCHAR(200),
    city VARCHAR(50),
    country VARCHAR(50),
    phone VARCHAR(20)
);

-- Tabla de Categorías
CREATE TABLE Categories (
    categoryID INT PRIMARY KEY AUTO_INCREMENT,
    categoryName VARCHAR(50),
    description VARCHAR(255)
);

-- Tabla de Productos
CREATE TABLE Products (
    productID INT PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(100),
    description VARCHAR(255),
    supplierID INT,
    categoryID INT,
    quantityPerUnit VARCHAR(50),
    unitPrice DECIMAL(10, 2),
    unitsInStock INT,
    unitsOnOrder INT,
    reorderLevel INT,
    discontinued BOOLEAN,
    -- FOREIGN KEY (SupplierID) REFERENCES Suppliers(supplierID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(categoryID)
);

-- -- -- Tabla de Órdenes
-- CREATE TABLE Orders (
--     orderID INT PRIMARY KEY AUTO_INCREMENT,
--     customerID INT,
--     employeeID INT,
--     orderDate DATE,
--     requiredDate DATE,
--     shippedDate DATE,
--     shipVia INT,
--     freight DECIMAL(10, 2),
--     shipName VARCHAR(100),
--     shipAddress VARCHAR(200),
--     shipCity VARCHAR(50),
--     shipRegion VARCHAR(50),
--     shipPostalCode VARCHAR(20),
--     shipCountry VARCHAR(50),
--     FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
--     FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
-- );

-- -- -- Tabla de Detalles de Órdenes
-- CREATE TABLE OrderDetails (
--     orderDetailID INT PRIMARY KEY AUTO_INCREMENT,
--     orderID INT,
--     productID INT,
--     unitPrice DECIMAL(10, 2),
--     quantity INT,
--     discount DECIMAL(5, 2),
--     FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
--     FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
-- );
