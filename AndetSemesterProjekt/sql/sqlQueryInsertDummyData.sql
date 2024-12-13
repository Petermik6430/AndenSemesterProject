use [DMA-CSD-V24_10526043];
go

-- Tilføj data til Zipcode tabellen
INSERT INTO Zipcode (zipcode, city, country) VALUES 
('1000', 'København', 'DK'),
('8000', 'Aarhus', 'DK'),
('5000', 'Odense', 'DK');

-- Tilføj data til Address tabellen
INSERT INTO [Address] ([address], zipcode) VALUES 
('Rådhuspladsen', '1000'),
('Store Torv', '8000'),
('H.C. Andersens Boulevard', '5000');

-- Tilføj data til Customer tabellen
INSERT INTO Customer (fName, lName, phoneNo, email) VALUES 
('John', 'Doe', '12345678', 'john.doe@example.com'),
('Jane', 'Smith', '0987654321', 'jane.smith@example.com'),
('Alice', 'Johnson', '5551234567', 'alice.johnson@example.com'),
('Anna', 'Dinh', '31374305', 'Anna@live.dk');

-- Tilføj data til Employee tabellen
INSERT INTO Employee (fName, lName, phoneNo, email, cprNo, salary, contactPerson, addressId, zipcode) VALUES 
('Hans', 'Andersen', '11223344', 'hans.andersen@example.com', '1234567890', 50000, 'Niels Bohr', 1, '1000'),
('Grethe', 'Sørensen', '22334455', 'grethe.sorensen@example.com', '0987654321', 45000, 'Marie Curie', 2, '8000'),
('Karl', 'Hansen', '33445566', 'karl.hansen@example.com', '5551234567', 55000, 'Isaac Newton', 3, '5000');

-- Tilføj data til Service tabellen
INSERT INTO [Service] ([name], duration) VALUES 
('Haircut', 10),
('Shave', 20),
('Facial', 25),
('Haircut + Shave', 30);

-- Tilføj data til BookingDate tabellen
INSERT INTO BookingDate (bookingDate, [type], note, employeeId, customerId, serviceId) VALUES 
('2024-12-08 10:00:00', 'booked', 'Regular haircut', 1, 1, 1),
('2024-12-08 11:00:00', 'booked', 'Full shave', 2, 2, 2),
('2024-12-08 12:00:00', 'booked', 'Deep cleansing facial', 3, 3, 3),
('2024-12-10 12:00:00', 'booked', 'Deep cleansing facial', 3, 2, 3),
('2024-12-09 12:00:00', 'booked', 'Deep cleansing facial', 3, 4, 3);

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
('ShopA', 1, '1000'),
('ShopB', 2, '5000');


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
('SupplierA', '1112223333', 'supplierA@example.com', '1000', 1),
('SupplierB', '4445556666', 'supplierB@example.com', '5000', 2);

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

-- Vis indholdet af tabellen Zipcode
SELECT * FROM Zipcode;

-- Vis indholdet af tabellen Address
SELECT * FROM [Address];

-- Vis indholdet af tabellen Customer
SELECT * FROM Customer;

-- Vis indholdet af tabellen Supplier
SELECT * FROM Supplier;

-- Vis indholdet af tabellen Item
SELECT * FROM Item;

-- Vis indholdet af tabellen ItemSupplier
SELECT * FROM ItemSupplier;

-- Vis indholdet af tabellen ItemType
SELECT * FROM ItemType;

-- Vis indholdet af tabellen Shop
SELECT * FROM Shop;

-- Vis indholdet af tabellen Stock
SELECT * FROM Stock;

-- Vis indholdet af tabellen Sale
SELECT * FROM Sale;

-- Vis indholdet af tabellen Employee
SELECT * FROM Employee;

-- Vis indholdet af tabellen BookingDate
SELECT * FROM BookingDate;

-- Vis indholdet af tabellen Service
SELECT * FROM [Service];

-- Vis indholdet af tabellen SaleLine
SELECT * FROM SaleLine;

-- Vis indholdet af tabellen Price
SELECT * FROM Price;
