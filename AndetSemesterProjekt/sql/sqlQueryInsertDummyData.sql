use [DMA-CSD-V24_10526043];
go

-- Tilføj data til Zipcode tabellen
INSERT INTO Zipcode (zipcode, city, country) VALUES 
('9000', 'Aalborg', 'DK'),
('9800', 'Hjørring', 'DK'),
('9400', 'Nørresundby ', 'DK');

-- Tilføj data til Address tabellen
INSERT INTO [Address] ([address], zipcode) VALUES 
('Rådhuspladsen', '9000'),
('Store Torv', '9800'),
('H.C. Andersens Boulevard', '9400');

-- Tilføj data til Customer tabellen
INSERT INTO Customer (fName, lName, phoneNo, email) VALUES 
('John', 'Doe', '12345678', 'john.doe@example.com'),
('Jane', 'Smith', '87654321', 'jane.smith@example.com'),
('Alice', 'Johnson', '11223344', 'alice.johnson@example.com');

-- Tilføj data til Employee tabellen
INSERT INTO Employee (fName, lName, phoneNo, email, cprNo, salary, addressId, zipcode) VALUES 
('Ali', 'B', '32165487', 'Ali@example.com', '1234567890', 50000, 1, '9000'),
('Carloz', 'S', '2334455', 'Carloz@example.com', '0987654321', 45000, 2, '9800'),
('Ahmad', 'A', '33445566', 'Ahmad@example.com', '5551234567', 55000, 3, '9400');

-- Tilføj data til Service tabellen
INSERT INTO [Service] ([name], duration) VALUES 
('Haircut', 10),
('Shave', 20),
('Facial', 25),
('Haircut + Shave', 30);

-- Tilføj data til BookingDate tabellen
INSERT INTO Booking (bookingDate, [type], note, employeeId, customerId, serviceId) VALUES 
('2024-12-08 10:00:00', 'booked', 'Regular haircut', 1, 1, 1),
('2024-12-08 11:00:00', 'booked', 'Full shave', 2, 2, 2),
('2024-12-08 12:00:00', 'booked', 'Deep cleansing facial', 3, 3, 3);

-- Tilføj data til Sale tabellen
INSERT INTO Sale (totalPrice, employeeId) VALUES 
(1000.00, 1),
(1500.00, 2);

-- Tilføj data til Item tabellen
INSERT INTO Item (brand, barcode, stockId) VALUES 
('Park', '123456', 1),
('ID', '789012', 2);

-- Tilføj data til Shop tabellen
INSERT INTO Shop ([name], addressId, zipcode) VALUES 
('ShopA', 1, '9000'),
('ShopB', 3, '9400');


-- Tilføj data til Stock tabellen
INSERT INTO Stock (minStock, maxStock, currentStock, itemId, shopId) VALUES 
(10, 100, 50, 1, 1),
(5, 50, 25, 2, 2);



-- Tilføj data til ItemType tabellen
INSERT INTO ItemType ([name]) VALUES 
('Hår'),
('skæg');



-- Tilføj data til Supplier tabellen
INSERT INTO Supplier ([name], phoneNo, email, zipcode, addressId) VALUES 
('SupplierA', '1112223333', 'supplierA@example.com', '9000', 1),
('SupplierB', '4445556666', 'supplierB@example.com', '9800', 2);

-- Tilføj data til ItemSupplier tabellen
INSERT INTO ItemSupplier (supplierId, itemId) VALUES 
(1, 1),
(2, 2);


-- Tilføj data til Price tabellen
INSERT INTO Price ([date], price, discount, serviceId, itemId) VALUES 
('2024-01-01', 100.00, 10.00, 1, 1),
('2024-01-02', 200.00, 20.00, 2, 2);

-- Tilføj data til SaleLine tabellen
INSERT INTO SaleLine (quantity, saleNo, serviceId, itemId) VALUES 
(1, 1, 1, 1),
(2, 2, 2, 2);


SELECT * FROM Zipcode;


SELECT * FROM [Address];


SELECT * FROM Customer;


SELECT * FROM Supplier;


SELECT * FROM Item;


SELECT * FROM ItemSupplier;


SELECT * FROM ItemType;


SELECT * FROM Shop;


SELECT * FROM Stock;


SELECT * FROM Sale;


SELECT * FROM Employee;


SELECT * FROM Booking;


SELECT * FROM [Service];


SELECT * FROM SaleLine;


SELECT * FROM Price;
