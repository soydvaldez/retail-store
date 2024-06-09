-- Insertando Customers
INSERT INTO Customers (FirstName, LastName, Email, Phone, Address, DateOfBirth, RegisterDate) VALUES
('John', 'Doe', 'john.doe@example.com', '555-1234', '123 Elm St', '1980-05-15', '2022-01-10'),
('Jane', 'Smith', 'jane.smith@example.com', '555-5678', '456 Oak St', '1985-08-20', '2022-02-15'),
('Jim', 'Brown', 'jim.brown@example.com', '555-8765', '789 Pine St', '1990-02-10', '2022-03-20'),
('Emily', 'Johnson', 'emily.johnson@example.com', '555-2345', '234 Maple St', '1975-11-30', '2022-04-25'),
('Michael', 'Williams', 'michael.williams@example.com', '555-3456', '345 Cedar St', '1988-09-25', '2022-05-30'),
('Sarah', 'Garcia', 'sarah.garcia@example.com', '555-4321', '432 Birch St', '1983-04-18', '2022-06-05');

-- Insertando Employees
INSERT INTO Employees (FirstName, LastName, Email, Phone, HireDate) VALUES
('Alice', 'Johnson', 'alice.johnson@example.com', '555-2345', '2020-01-15'),
('Bob', 'Davis', 'bob.davis@example.com', '555-6789', '2019-03-20'),
('Carol', 'Wilson', 'carol.wilson@example.com', '555-9876', '2018-07-22');

INSERT INTO Suppliers (CompanyName, ContactName, ContactTitle, Address, City, Country, Phone) VALUES
('Supplier One', 'Michael Scott', 'Manager', '123 Industrial Ave', 'Scranton', 'USA', '555-1111'),
('Supplier Two', 'Dwight Schrute', 'Assistant Manager', '456 Warehouse Rd', 'Scranton', 'USA', '555-2222'),
('Supplier Three', 'Jim Halpert', 'Sales Representative', '789 Office Blvd', 'Scranton', 'USA', '555-3333');


INSERT INTO Categories (CategoryName, description) VALUES
('Electronics', 'Devices and gadgets'),
('Furniture', 'Household furniture'),
('Clothing', 'Apparel and accessories');

INSERT INTO Products (ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued) VALUES
('Smartphone', 1, 1, '1 unit', 699.99, 50, 10, 5, FALSE),
('Laptop', 1, 1, '1 unit', 999.99, 30, 5, 3, FALSE),
('Desk', 2, 2, '1 unit', 199.99, 20, 2, 2, FALSE),
('Chair', 2, 2, '1 unit', 89.99, 100, 10, 10, FALSE),
('T-shirt', 3, 3, '1 unit', 19.99, 200, 20, 20, FALSE);


-- INSERT INTO Orders (CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry) VALUES
-- (1, 1, '2024-06-01', '2024-06-05', '2024-06-03', 1, 15.00, 'John Doe', '123 Elm St', 'Hometown', 'State', '12345', 'USA'),
-- (2, 2, '2024-06-02', '2024-06-06', '2024-06-04', 2, 25.00, 'Jane Smith', '456 Oak St', 'Hometown', 'State', '12345', 'USA'),
-- (3, 3, '2024-06-03', '2024-06-07', '2024-06-05', 3, 20.00, 'Jim Brown', '789 Pine St', 'Hometown', 'State', '12345', 'USA');

-- INSERT INTO OrderDetails (OrderID, ProductID, UnitPrice, Quantity, Discount) VALUES
-- (1, 1, 699.99, 1, 0.00),
-- (1, 4, 89.99, 2, 0.10),
-- (2, 2, 999.99, 1, 0.05),
-- (2, 5, 19.99, 3, 0.00),
-- (3, 3, 199.99, 1, 0.00),
-- (3, 1, 699.99, 2, 0.15);
