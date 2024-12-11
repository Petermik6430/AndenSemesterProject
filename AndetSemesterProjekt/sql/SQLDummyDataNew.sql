-- Brug mockdatabase
use mockdatabase;
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
('Anna', 'Dinh', '31374305','Anna@live.dk');

-- Tilføj data til Employee tabellen
INSERT INTO Employee (fName, lName, phoneNo, email, cprNo, salary, contactPerson, addressId, zipcode) VALUES 
('Hans', 'Andersen', '11223344', 'hans.andersen@example.com', '1234567890', 50000, 'Niels Bohr', 1, '1000'),
('Grethe', 'Sørensen', '22334455', 'grethe.sorensen@example.com', '0987654321', 45000, 'Marie Curie', 2, '8000'),
('Karl', 'Hansen', '33445566', 'karl.hansen@example.com', '5551234567', 55000, 'Isaac Newton', 3, '5000');

-- Tilføj data til Service tabellen
INSERT INTO [Service] ([name], duration) VALUES 
('Haircut', 30),
('Shave', 20),
('Facial', 45);

-- Tilføj data til BookingDate tabellen
INSERT INTO BookingDate ( bookingDate, [type], note, employeeId, customerId, serviceId) VALUES 
('2024-12-08 10:00:00','booked', 'Regular haircut', 1, 1, 1),
('2024-12-08 11:00:00', 'booked', 'Full shave', 2, 2, 2),
('2024-12-08 12:00:00','booked', 'Deep cleansing facial', 3, 3, 3);


select * from Customer;

select * from BookingDate;