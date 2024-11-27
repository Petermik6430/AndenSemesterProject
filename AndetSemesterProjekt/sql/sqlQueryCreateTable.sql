use mockdatabase
go
--dropper tabel hvis den eksistere
drop table if exists Price;
drop table if exists SaleLine;
drop table if exists [Service];
drop table if exists BookingUnit;
drop table if exists BookingDate;
drop table if exists Employee;
drop table if exists Sale;
drop table if exists Payment;
drop table if exists Stock;
drop table if exists Shop;
drop table if exists ItemType;
drop table if exists ItemSupplier;
drop table if exists Item;
drop table if exists Supplier;
drop table if exists Customer;
drop table if exists [Address];
drop table if exists Zipcode;

--oprettelse af tabeller

create table Zipcode(
	zipcode varchar(12),
	city nvarchar(50),
	country nvarchar (50),
	constraint pk_Zipcode_zipcode Primary key (zipcode)

	
)



create table [Address](
	id int identity(1,1),
	[address] varchar(100),
	zipcode varchar(12),
	constraint pk_Address_id Primary key(id),
	constraint fk_Address_zipcode Foreign key (zipcode) references Zipcode(zipcode),
)



create table Customer(
	id int identity(1,1),
	fName varchar(40),
	lName varchar(40),
	phoneNo char(10),
	email varchar(50),
	constraint pk_Customer_id Primary key(id),

)



create table Supplier(
	id int identity(1,1),
	[name] varchar(50),
	phoneNo char(10),
	email varchar(50),
	zipcode varchar(12),
	addressId int,
	constraint pk_Supplier_id Primary key(id),
	constraint fk_Supplier_zipcode Foreign key (zipcode) references Zipcode(zipcode),
	constraint fk_Supplier_address Foreign key (addressId) references [Address](id)
)



create table Item(
	id int identity(1,1),
	brand varchar(25),
	barcode char (25),
	stockId int,
	constraint pk_Item_id Primary key(id),
)



create table ItemSupplier(
	id int identity(1,1),
	supplierId int,
	itemId int,
	constraint pk_ItemSupplier_id Primary key(id),
	constraint fk_ItemSupplier_itemId Foreign key (itemId) references Item(id),
	constraint fk_ItemSupplier_supplierId Foreign key (supplierId) references Supplier(id),
)



create table ItemType(
	id int identity(1,1),
	[name] varchar(50),
	constraint pk_ItemType_id Primary key(id)
)



create table Shop(
	id int identity(1,1),
	[name] varchar(50),
	addressId int,
	zipcode varchar(12),
	constraint pk_Shop_id Primary key(id),
	constraint fk_Shop_zipcode Foreign key (zipcode) references Zipcode(zipcode),
	constraint fk_Shop_addressId Foreign key (addressId) references [Address](id)
)



create table Stock(
	id int identity(1,1),
	minStock int,
	maxStock int,
	currentStock int,
	itemId int,
	shopId int,
	constraint pk_Stock_id Primary key(id),
	constraint fk_Stock_itemId Foreign key (itemId) references Item(id),
	constraint fk_Stock_shopId Foreign key (shopId) references Shop(id)
)


create table Payment(
	id int identity(1,1),
	creditInfo varchar(100),
	customerInfo varchar (100),
	paymentMethod varchar (100),
	constraint pk_Payment_id Primary key(id)
)



create table Sale(
	saleNo int identity(1,1),
	totalPrice money, -- or decimal
	paymentId int,
	constraint pk_Sale_saleNo Primary key(saleNo),
	constraint fk_Sale_paymentId Foreign key (paymentId) references Payment(id),

)



create table Employee(
	id int identity(1,1),
	fName varchar (40),
	lName varchar(40),
	phoneNo char(8),
	email varchar(50),
	cprNo char(10),
	salary decimal,
	contactPerson varchar(40),
	addressId int,
	zipcode varchar(12),
	saleNo int,
	constraint pk_Employee_id Primary key(id),
	constraint fk_Emplyee_addressId Foreign key (addressId) references [Address](id),
	constraint fk_Emplouee_zipcode Foreign key (zipcode) references Zipcode(zipcode),
	constraint fk_Employee_saleNo Foreign key (saleNo) references Sale(saleNo)
	 on delete cascade
)



create table BookingDate(
	id int identity(1,1),
	[service] varchar(50), --skal denne være en fk til service?
	bookingDate date,
	note varchar(300),
	constraint pk_BookingDate_id Primary key(id)
)



create table BookingUnit(
	id int identity(1,1),
	timeSlot dateTime,
	[type] varchar(50), -- if the bookingtime is available, booked or other
	employeeId int,
	customerId int,
	bookingDateId int,
	constraint pk_BookingUnit_id Primary key(id),
	constraint fk_BookingUnit_employeeId Foreign key (employeeId) references Employee(id)
		on delete cascade,
	constraint fk_BookingUnit_customerId Foreign key (customerId) references Customer(id)
		on delete cascade,
	constraint fk_BookingUnit_bookingDateId Foreign key (bookingDateId) references BookingDate(id)
		on delete cascade
) 

create table [Service](
	id int identity(1,1),
	trimType varchar(50),
	bookingId int,
	constraint pk_Service_id Primary key(id),
	constraint fk_Service_bookingId Foreign key (bookingId) references BookingDate(id),
)



create table SaleLine(
	id int identity(1,1),
	quantity int,
	saleNo int,
	serviceId int,
	itemId int,
	constraint pk_SaleLine_id Primary key(id),
	constraint fk_SaleLine_saleNo Foreign key (saleNo) references Sale(saleNo)
		on delete cascade,
	constraint fk_SaleLine_serviceId Foreign key (serviceId) references [Service](id),
	constraint fk_SaleLine_itemId Foreign key (itemId) references Item(id),
)



create table Price(
	id int identity(1,1),
	[date] dateTime,
	price money,
	discount money, -- hvilken data type skal det være når vi skal beregne discount
	serviceId int,
	itemId int,
	constraint pk_Price_id Primary key(id),
	constraint fk_Price_serviceId Foreign key (serviceId) references [Service](id),
	constraint fk_Price_itemId Foreign key (itemId) references Item(id),
	)
