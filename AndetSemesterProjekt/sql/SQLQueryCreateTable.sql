use mockdatabase
go

drop table if exists ;
drop table if exists ;
drop table if exists ;
drop table if exists ;
drop table if exists ;
drop table if exists ;
drop table if exists ;
drop table if exists ;
drop table if exists ;


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

	-- constraint pk_Employee_id Primary key(id),
	-- constraint fk_Emplyee_addressId Foreign key (addressId references [Address](id)
)

create table Customer(
	id int identity(1,1),
	fName varchar(40),
	lName varchar(40),
	phoneNo char(10),
	email varchar(50)
)

create table Supplier(
	id int identity(1,1),
	[name] varchar(50),
	phoneNo char(10),
	email varchar(50)
)

create table Item(
	id int identity(1,1),
	brand varchar(25),
	barcode char (25),
	stockId int,
)

create table ItemSupplier(
	id int identity(1,1),
	supplierId int,
	itemTypeId int,
)

create table ItemType(
	id int identity(1,1),
	name varchar(50),
)

create table Shop(
	id int identity(1,1),
	[name] varchar(50),
	addressId int,
	zipcode varchar(12)
)

create table Stock(
	id int identity(1,1),
	minStock int,
	maxStock int,
	currentStock int,
	itemId int,
	shopId int,
)

create table [Service](
	id int identity(1,1),
	trimType varchar(50),
	bookingId int,
)

create table Sale(
	id int identity(1,1),
	totalPrice money, -- or decimal
	saleNo int identity (1,1), -- Vi har muligvis ikke brug for saleNo når vi har en saleId.
	itemId int,
)

create table SaleLine(
	id int identity(1,1),
	quantity int,
	saleId int,
	serviceId int,
	itemId int,
)

create table Booking(
	id int identity(1,1),
	[service] varchar(50),
	bookingDate dateTime,
	note varchar(300),
)

create table BookingUnit(
	id int identity(1,1),
	[date] dateTime,
	timeSlot dateTime,
	[type] varchar(50), -- if the bookingtime is available, booked or other
	employeeId int,
	customerId int,
) 

create table price(
	id int identity(1,1),
	[date] dateTime,
	price money,
	discount money, -- hvilken data type skal det være når vi skal beregne discount
	serviceId int,
	itemId int,
	)

create table Payment(
	id int identity(1,1),
	creditInfo varchar(100),
	customerInfo varchar (100),
	paymentMethod varchar (100),
)

create table Zipcode(
	zipcode varchar(12),
	city nvarchar(50),
	country nvarchar (50)
	
)

create table [Address](
	id int identity(1,1),
	[address] varchar(100),
	zipcode nvarchar(50)
)