use mockdatabase
go

-- Insert into Zipcode
INSERT INTO Zipcode (zipcode, city, country)
VALUES 
('9000', 'Aalborg', 'Denmark'),
('9200', 'Aalborg SV', 'Denmark'),
('9210', 'Aalborg SØ', 'Denmark'),
('9220', 'Aalborg Øst', 'Denmark');

-- Insert into Address
INSERT INTO [Address] ([address], zipcode)
VALUES 
('Vesterbro 56', '9000'),
('Søndergade 12', '9210'),
('Hadsundvej 45', '9220'),
('Skansevej 14', '9200');

-- Insert into Customer
INSERT INTO Customer (fName, lName, phoneNo, email)
VALUES 
('John', 'Doe', '12345678', 'john.doe@example.com'),
('Jane', 'Smith', '87654321', 'jane.smith@example.com'),
('Peter', 'Hansen', '22334455', 'peter.hansen@example.com'),
('Emma', 'Larsen', '55667788', 'emma.larsen@example.com');

-- Insert into Supplier
INSERT INTO Supplier ([name], phoneNo, email, zipcode, addressId)
VALUES 
('HairPro Supplies', '11223344', 'contact@hairpro.dk', '9000', 1),
('ScissorWorld', '99887766', 'sales@scissorworld.dk', '9210', 2);

-- Insert into Item
INSERT INTO Item (brand, barcode, stockId)
VALUES 
('Andis', '1234567890123456789012345', 1),
('Wahl', '9876543210987654321098765', 2);

-- Insert into ItemSupplier
INSERT INTO ItemSupplier (supplierId, itemId)
VALUES 
(1, 1),
(2, 2);

-- Insert into ItemType
INSERT INTO ItemType ([name])
VALUES 
('Hårprodukter'),
('Skægprodukter');

-- Insert into Shop
INSERT INTO Shop ([name], addressId, zipcode)
VALUES 
('Barberakji', 1, '9000');

-- Insert into Stock
INSERT INTO Stock (minStock, maxStock, currentStock, itemId, shopId)
VALUES 
(5, 50, 10, 1, 1),
(3, 30, 5, 2, 1);

-- Insert into Payment
INSERT INTO Payment (creditInfo, customerInfo, paymentMethod)
VALUES 
('1234-5678-9012-3456', 'John Doe', 'Credit Card'),
('1111-2222-3333-4444', 'Jane Smith', 'MobilePay');

-- Insert into Sale
INSERT INTO Sale (totalPrice, paymentId)
VALUES 
(350.00, 1),
(200.00, 2);

-- Insert into Employee
INSERT INTO Employee (fName, lName, phoneNo, email, cprNo, salary, contactPerson, addressId, zipcode, saleNo)
VALUES 
('Mark', 'Olsen', '33445566', 'mark.olsen@example.com', '1010101234', 28000.00, 'Anna Olsen', 3, '9000', 1),
('Laura', 'Jensen', '66778899', 'laura.jensen@example.com', '2020205678', 25000.00, 'Jens Jensen', 4, '9210', 2);

-- Insert into BookingDate
INSERT INTO BookingDate ([service], bookingDate, note)
VALUES 
('Haircut', '2024-12-01', 'Preferred stylist: Mark'),
('Beard Trim', '2024-12-01', 'Include hot towel treatment');

-- Insert into BookingUnit
INSERT INTO BookingUnit  (timeSlot, [type], employeeId, customerId, bookingDateId)
VALUES 
('2024-12-01 10:30:00', 'Booked', 1, 1, 1),
('2024-12-01 12:00:00', 'Booked', 2, 2, 2);

-- Insert into Service
INSERT INTO [Service] (trimType, bookingId)
VALUES 
('Haircut', 1),
('Beard Trim', 2);

-- Insert into SaleLine
INSERT INTO SaleLine (quantity, saleNo, serviceId, itemId)
VALUES 
(1, 1, 1, 1),
(1, 2, 2, 2);

-- Insert into Price
INSERT INTO Price ([date], price, discount, serviceId, itemId)
VALUES 
('2024-11-01', 300.00, 50.00, 1, 1),
('2024-11-01', 150.00, 20.00, 2, 2);


-- Vis data fra Zipcode
SELECT * FROM Zipcode;

-- Vis data fra Address
SELECT * FROM [Address];

-- Vis data fra Customer
SELECT * FROM Customer;

-- Vis data fra Supplier
SELECT * FROM Supplier;

-- Vis data fra Item
SELECT * FROM Item;

-- Vis data fra ItemSupplier
SELECT * FROM ItemSupplier;

-- Vis data fra ItemType
SELECT * FROM ItemType;

-- Vis data fra Shop
SELECT * FROM Shop;

-- Vis data fra Stock
SELECT * FROM Stock;

-- Vis data fra Payment
SELECT * FROM Payment; -- muligvis slettes

-- Vis data fra Sale
SELECT * FROM Sale;

-- Vis data fra Employee
SELECT * FROM Employee; -- saleNo skal muligvis se anderledes ud

-- Vis data fra BookingDate
SELECT * FROM BookingDate;

-- Vis data fra BookingUnit
SELECT * FROM BookingUnit;

-- Vis data fra Service
SELECT * FROM [Service];

-- Vis data fra SaleLine
SELECT * FROM SaleLine;

-- Vis data fra Price
SELECT * FROM Price;
