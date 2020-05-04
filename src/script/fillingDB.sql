/* Filling city table */

INSERT INTO City (label, zipCode)
VALUES ('Bruxelles', 1000);

INSERT INTO City (label, zipCode)
VALUES ('Anvers', 2000);

INSERT INTO City (label, zipCode)
VALUES ('Louvain', 3000);

INSERT INTO City (label, zipCode)
VALUES ('Glain', 4000);

INSERT INTO City (label, zipCode)
VALUES ('Liège', 4000);

INSERT INTO City (label, zipCode)
VALUES ('Rocourt', 4000);

INSERT INTO City (label, zipCode)
VALUES ('Namur', 5000);

INSERT INTO City (label, zipCode)
VALUES ('Beez', 5000);

INSERT INTO City (label, zipCode)
VALUES ('Charleroi', 6000);

INSERT INTO City (label, zipCode)
VALUES ('Mons', 7000);

INSERT INTO City (label, zipCode)
VALUES ('Brugge', 8000);

INSERT INTO City (label, zipCode)
VALUES ('Gand', 9000);

/* Filling VATCode table */

INSERT INTO VATCode (rate)
VALUES (21);

/* Filling rank table */

INSERT INTO `Rank` (label, creditLimit)
VALUES ('New', null);

INSERT INTO `Rank` (label, creditLimit)
VALUES ('Loyal', 500);

INSERT INTO `Rank` (label, creditLimit)
VALUES ('Prioritised', 1500);

/* Filling Status table */

INSERT INTO `Status` (label)
VALUES ('En attente');

INSERT INTO `Status` (label)
VALUES ('Confirmé');

INSERT INTO `Status` (label)
VALUES ('Annulé');

INSERT INTO `Status` (label)
VALUES ('Terminé');

/* Filling PaymentMethod */

INSERT INTO PaymentMethod (label)
VALUES ('Bank');

INSERT INTO PaymentMethod (label)
VALUES ('Cheque');

INSERT INTO PaymentMethod (label)
VALUES ('Cash');

INSERT INTO PaymentMethod (label)
VALUES ('Gift card');

/* Filling Role table */

INSERT INTO Role (name)
VALUES ('Employé');

INSERT INTO Role (name)
VALUES ('Manager');

/* Filling Entity table */

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('etuXXXXX@henallux.be', 'John Doe', '0470/21.01.02', 30, 'Rue de Bruxelles', 'BE00111122223333', 'Namur', 5000);

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('peter@gmail.be', 'Peter Mark', '0475/16.65.64', 30, 'Rue de Bruxelles', 'BE02111122223333', 'Namur', 5000);

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('venom@gmail.be', 'Peter Dinklage', '0473/12.31.12', 30, 'Rue de Bruxelles', 'BE07111122223333', 'Namur', 5000);

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('daenerys@gmail.be', 'Daenerys Targa', '0870/65.73.22', 30, 'Rue de Bruxelles', 'BE03111122223333', 'Namur', 5000);

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('ragnarloth@gmail.be', 'Ragnar Lothbrok', '0650/21.03.02', 30, 'Rue de Bruxelles', 'BE04111122223333', 'Namur', 5000);

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('lagertha@gmail.be', 'Lagertha Ingstad', '0470/21.17.02', 30, 'Rue de Bruxelles', 'BE05111122223333', 'Namur', 5000);

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('tony@stark.be', 'Tony Stark', '0484/61.16.82', 30, 'Rue de Bruxelles', 'BE06111122223333', 'Namur', 5000);

INSERT INTO Entity (mail, contactName, phoneNumber, houseNumber, street, bankAccountNumber, CityLabel, CityZipCode)
VALUES ('bill@microsoft.com', 'Bill Gates', '0484/65.16.82', 30, 'Rue de Bruxelles', 'BE08111122223333', 'Namur', 5000);

/* Filling Customer table */

INSERT INTO Customer (EntityId, RankId, subscriptionDate)
VALUES (1, 3, '2020-03-01');

INSERT INTO Customer (EntityId, RankId, subscriptionDate)
VALUES (2, 2, '2020-03-03');

INSERT INTO Customer (EntityId, RankId, subscriptionDate)
VALUES (4, 1, '2020-03-02');

INSERT INTO Customer (EntityId, RankId, subscriptionDate)
VALUES (5, 1, '2020-03-04');

INSERT INTO Customer (EntityId, RankId, subscriptionDate)
VALUES (6, 1, '2020-03-05');

INSERT INTO Customer (EntityId, RankId, subscriptionDate)
VALUES (7, 1, '2020-03-06');

INSERT INTO Customer (EntityId, RankId, subscriptionDate)
VALUES (3, 1, '2020-03-07');

/* Filling Employee table */

INSERT INTO Employee (EntityId, RoleId,password)
VALUES (1, 1, 'JeSuisUnPoisson');

INSERT INTO Employee (EntityId, RoleId,password)
VALUES (2, 1, 'admin');

INSERT INTO Employee (EntityId, RoleId,password)
VALUES (8, 1, 'test');

/* Filling Provider table */

INSERT INTO Provider (entityId, providerType)
VALUES (2, 'Artisan');

/* Filling Order table */

INSERT INTO `Order` (startingDate, isPaid, StatusNumber, paymentMethodId, CustomerEntityId)
VALUES ('2019-06-14', false, 1, 2, 1);

/* Filling Product table */

INSERT INTO Product (label, unitPrice, currentStock, minStock, VATCodeRate)
VALUES ('Jupiler 25cl', 2.50, 156, 30, 21);

INSERT INTO Product (label, unitPrice, currentStock, minStock, VATCodeRate)
VALUES ('Vodka 75cl', 12.50, 156, 30, 21);

INSERT INTO Product (label, unitPrice, currentStock, minStock, VATCodeRate)
VALUES ('Amaretto 1l', 13.99, 156, 30, 21);

INSERT INTO Product (label, unitPrice, currentStock, minStock, VATCodeRate)
VALUES ('Caiser Jupiler 24 x 25cl', 13.65, 156, 30, 21);

INSERT INTO Product (label, unitPrice, currentStock, minStock, VATCodeRate)
VALUES ('Maes 25cl', 2.50, 156, 30, 21);

INSERT INTO Product (label, unitPrice, currentStock, minStock, VATCodeRate)
VALUES ('Mojito canette 25cl', 2.50, 156, 30, 21);

INSERT INTO Product (label, unitPrice, currentStock, minStock, VATCodeRate)
VALUES ('Carapils 25cl', 0.99, 156, 30, 21);

/* Filling Delivery table */

INSERT INTO Delivery (plannedDate, OrderReference, EmployeeEntityId)
VALUES ('2020-06-21', 1, 1);

/* Filling OrderLine table */

INSERT INTO OrderLine (Productcode, Orderreference, quantity, salesUnitPrice)
VALUES (1,1,32,2.70);