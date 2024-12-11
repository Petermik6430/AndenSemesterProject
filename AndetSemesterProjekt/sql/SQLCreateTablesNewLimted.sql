use mockdatabase;	

go

drop table if exists Price;
drop table if exists BookingDate;
drop table if exists [Service];
drop table if exists Employee;
drop table if exists Item;
drop table if exists Customer;
drop table if exists [Address];
drop table if exists Zipcode;



create table Zipcode(
	zipcode varchar(12),
	city nvarchar(50),
	country nvarchar(2)
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
	constraint pk_Customer_id Primary key(id)
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
	--saleNo int,
	constraint pk_Employee_id Primary key(id),
	constraint fk_Emplyee_addressId Foreign key (addressId) references [Address](id),
	constraint fk_Employee_zipcode Foreign key (zipcode) references Zipcode(zipcode),
--	constraint fk_Employee_saleNo Foreign key (saleNo) references Sale(saleNo)
--	 on delete cascade
)


create table [Service](
	id int identity(1,1),
	[name] varchar(25),--trimTypeId varchar(50),
	duration int,
	--bookingId int,
	constraint pk_Service_id Primary key(id)
)



create table BookingDate ( -- burde hedde booking
	id int identity(1,1),
	bookingDate dateTime,
	[type] varchar(20),
	note varchar(300),
	employeeId int,
	customerId int,
	[serviceId] int, --skal denne v�re en fk til service?
	constraint pk_BookingDate_id Primary key(id),
	constraint fk_BookingDate_employeeId Foreign key (employeeId) references Employee(id)
		on delete cascade,
		constraint fk_BookingDate_customerId Foreign key (customerId) references Customer(id)
		on delete cascade,
	constraint fk_BookingId_service Foreign key (serviceId) references [Service](id)
)
