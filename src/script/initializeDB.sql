set @@global.time_zone = '+00:00';
set @@session.time_zone = '+00:00';

/* DROP TABLE OrderLine; */
/* DROP TABLE delivery; */
/* DROP TABLE employee; */
/* DROP TABLE `order`; */
/* DROP TABLE paymentmethod; */
/* DROP TABLE product; */
/* DROP TABLE provider; */
/* DROP TABLE `role`; */
/* DROP TABLE `status`; */
/* DROP TABLE vatcode; */
/* DROP TABLE customer; */
/* DROP TABLE entity; */
/* DROP TABLE city; */
/* DROP TABLE `rank`; */
/* DROP TABLE News; */

CREATE TABLE City (
	label varchar(255) NOT NULL, 
	zipCode int NOT NULL CHECK (zipCode BETWEEN 1000 AND 9999),
	PRIMARY KEY (label, zipCode));

CREATE TABLE VATCode (
	rate int PRIMARY KEY);

CREATE TABLE `Rank` (
	id int PRIMARY KEY AUTO_INCREMENT, 
	label varchar(60) UNIQUE NOT NULL,
	creditLimit int CHECK (creditLimit > 0),
	minAmountOrder int CHECK (minAmountOrder >= 0));

CREATE TABLE Status (
	id int PRIMARY KEY AUTO_INCREMENT, 
	label varchar(255) NOT NULL);

CREATE TABLE PaymentMethod (
	id int PRIMARY KEY AUTO_INCREMENT,
	label varchar(255) NOT NULL);

CREATE TABLE Role (
	id int PRIMARY KEY AUTO_INCREMENT, 
	name varchar(255) NOT NULL UNIQUE);

CREATE TABLE Entity (
	id int PRIMARY KEY AUTO_INCREMENT, 
	mail varchar(155) UNIQUE, 
	contactName varchar(255) NOT NULL, 
	phoneNumber varchar(50) NOT NULL UNIQUE, 
	houseNumber int NOT NULL CHECK (houseNumber > 0), /* mettre en varchar ? 17A ? */ 
	street varchar(255) NOT NULL,
	bankAccountNumber varchar(50) UNIQUE,
    businessNumber varchar(50) UNIQUE,
	Citylabel varchar(255) NOT NULL, 
	CityZipCode int NOT NULL, /* Pas besoin de check car sera toujours sélectionner par ComboBox */
	CONSTRAINT city_fk FOREIGN KEY (CityLabel, CityZipCode) REFERENCES city (label, zipCode));

CREATE TABLE Employee (
	EntityId int PRIMARY KEY, 
	RoleId int NOT NULL, 
	password varchar(255) NOT NULL,
	CONSTRAINT EmployeeEntity_fk FOREIGN KEY (EntityId) REFERENCES Entity (id),
	CONSTRAINT role_fk FOREIGN KEY (RoleId) REFERENCES Role (id));

/*CREATE TABLE Customer (
	EntityId int PRIMARY KEY, 
	RankId int NOT NULL, 
	subsciptionDate date NOT NULL CHECK (subscriptionDate <= sysdate()),
	CONSTRAINT CustomerEntity_fk FOREIGN KEY (EntityId) references Entity (id),
	CONSTRAINT rank_fk FOREIGN KEY (RankId) REFERENCES `Rank` (id));*/
    
CREATE TABLE Customer (
	EntityId int NOT NULL, 
	RankId int NOT NULL, 
	subscriptionDate date NOT NULL,
	PRIMARY KEY (EntityId),
	CONSTRAINT CustomerEntity_fk FOREIGN KEY (EntityId) references Entity (id),
	CONSTRAINT rank_fk FOREIGN KEY (RankId) REFERENCES `Rank` (id));

CREATE TABLE Provider (
	entityId int PRIMARY KEY, 
	providerType char(255) NOT NULL,
	CONSTRAINT entity_fk FOREIGN KEY (entityId) REFERENCES Entity (id));

CREATE TABLE `Order` (
	`reference` int PRIMARY KEY AUTO_INCREMENT, 
	startingDate date NOT NULL, /* Check pour que la date de départ ne soit pas dans le futur ? Ou starting date tj à sysdate ? */
	isPaid bit NOT NULL, 
	StatusNumber int NOT NULL, 
	paymentMethodId int NOT NULL, 
	CustomerEntityId int,
	CONSTRAINT status_fk FOREIGN KEY (StatusNumber) REFERENCES `Status` (id),
	CONSTRAINT PaymentMethod_fk FOREIGN KEY (paymentMethodId) REFERENCES PaymentMethod (id),
	CONSTRAINT OrderCustomerEntity_fk FOREIGN KEY (CustomerEntityId) REFERENCES Customer (EntityId));

CREATE TABLE Product (
	code int PRIMARY KEY AUTO_INCREMENT, 
	label char(255) NOT NULL, 
	unitPrice real NOT NULL CHECK (unitPrice >= 0), 
	currentStock int NOT NULL CHECK (currentStock >= 0), 
	maxStock int CHECK (maxStock >= 0), 
	minStock int CHECK (minStock >= 0), 
	VATCodeRate int NOT NULL, 
	ProviderEntityId int,
	CONSTRAINT VATCodeRate_fk FOREIGN KEY (VATCodeRate) REFERENCES VATCode (rate),
	CONSTRAINT ProviderEntityId FOREIGN KEY (ProviderEntityId) REFERENCES Provider (EntityId));

CREATE TABLE Delivery (
	id int PRIMARY KEY AUTO_INCREMENT, 
	plannedDate date NOT NULL, /* Check if planned date est bien dans le futur */
	deliveredDate date, 
	OrderReference int NOT NULL, 
	EmployeeEntityId int NOT NULL,
	CONSTRAINT EmployeeId_fk FOREIGN KEY (EmployeeEntityId) REFERENCES Employee (EntityId),
	CONSTRAINT OrderReference_fk FOREIGN KEY (OrderReference) REFERENCES `Order` (reference));

CREATE TABLE OrderLine (
	Productcode int NOT NULL, 
	Orderreference int NOT NULL, 
	quantity int CHECK (quantity > 0), 
	salesUnitPrice real CHECK (salesUnitPrice >= 0), 
	PRIMARY KEY (Productcode, Orderreference),
	CONSTRAINT Productcode_fk FOREIGN KEY (Productcode) REFERENCES Product (code),
	CONSTRAINT Orderreference FOREIGN KEY (OrderReference) REFERENCES `Order` (reference));

CREATE TABLE News (
    id int PRIMARY KEY AUTO_INCREMENT,
    title varchar(100) NOT NULL,
    content char(255) NOT NULL,
    startingDate datetime NOT NULL,
    endDate datetime NOT NULL,
    EmployeeEntityId int NOT NULL,
    CONSTRAINT EmployeeEntityId FOREIGN KEY (EmployeeEntityId) REFERENCES Employee (EntityId)
);

    
