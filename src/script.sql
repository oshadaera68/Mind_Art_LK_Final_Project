#------database creation in final project---------

DROP DATABASE IF EXISTS TimberMill;
CREATE DATABASE IF NOT EXISTS TimberMill;
SHOW DATABASES;
USE TimberMill;
#-------------

#-----table creation in final project-----------

#1.Employee Table

DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee(
    EmpId VARCHAR(15) PRIMARY KEY NOT NULL,
    EmpName VARCHAR(12),
    Address VARCHAR(15),
    TeleNo VARCHAR(10)
    );
SHOW TABLES;
DESCRIBE Employee;

#2.EmployeePayment Table

DROP TABLE IF EXISTS EmployeePayment;
CREATE TABLE IF NOT EXISTS EmployeePayment(
    Id VARCHAR(15) PRIMARY KEY NOT NULL,
    Date DATE,
    Amount DOUBLE,
    CONSTRAINT FOREIGN KEY (Id) REFERENCES Employee(EmpId)
    );
SHOW TABLES;
DESCRIBE EmployeePayment;

#3.Customer Table

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CusID VARCHAR(15) PRIMARY KEY NOT NULL,
    CusName VARCHAR(25),
    CusAddress VARCHAR(25),
    CusTelNo VARCHAR(10)
    );
SHOW TABLES;
DESCRIBE Customer;

#4.Orders Table

DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(
    OrderID VARCHAR(15) PRIMARY KEY NOT NULL,
    CustomerID VARCHAR(15),
    Date DATE,
    Time VARCHAR(20),
    Cost DOUBLE
    );
SHOW TABLES;
DESCRIBE Orders;

#5.Expense table

DROP TABLE IF EXISTS Expense;
CREATE TABLE IF NOT EXISTS Expense(
    ExpenID VARCHAR(15) PRIMARY KEY NOT NULL,
    ExpenType VARCHAR(15),
    ExpenAmount DOUBLE
    );
SHOW TABLES;
DESCRIBE Expense;

#6.Item table

DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    ItemCode VARCHAR(15) PRIMARY KEY NOT NULL,
    ItemName VARCHAR(20),
    QtyOnHand INT,
    UnitPrice double
    );
SHOW TABLES;
DESCRIBE Item;

#7.Supplier table

DROP TABLE IF EXISTS Supplier;
CREATE TABLE IF NOT EXISTS Supplier(
    SupplierID VARCHAR(15) PRIMARY KEY NOT NULL,
    SupplierName VARCHAR(25),
    SupplierAddress VARCHAR(30),
    TelNo VARCHAR(35)
    );
SHOW TABLES;
DESCRIBE Supplier;


#8.SupplierOrder Table

DROP TABLE IF EXISTS SupplierOrder;
CREATE TABLE IF NOT EXISTS SupplierOrder(
    SupplierOrderID VARCHAR(15) PRIMARY KEY NOT NULL,
    SupplierOrderName VARCHAR(25),
    SuppliierOrderdate DATE
    );
SHOW TABLES;
DESCRIBE SupplierOrder;

#9.OrderDetails Table

DROP TABLE IF EXISTS OrderDetails;
CREATE TABLE IF NOT EXISTS OrderDetails(
    ItemCode VARCHAR(15) PRIMARY KEY NOT NULL,
    OrderID VARCHAR(15) NOT NULL,
    Qty INT,
    price double,
    CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Item(ItemCode),
    CONSTRAINT FOREIGN KEY(OrderID) REFERENCES Orders(OrderID)
    );
SHOW TABLES;
DESCRIBE OrderDetails;

#10.Wood type table

DROP TABLE IF EXISTS WoodType;
CREATE TABLE IF NOT EXISTS WoodType(
    WoodTypeID VARCHAR(15) PRIMARY KEY NOT NULL,
    WoodName VARCHAR(30)
    );
SHOW TABLES;
DESCRIBE WoodType;

#11.SupplierDetail table

DROP TABLE IF EXISTS SupplierDetail;
CREATE TABLE IF NOT EXISTS SupplierDetail(
    SupplierOrderID VARCHAR(15) PRIMARY KEY NOT NULL,
    WoodTypeID VARCHAR(15) NOT NULL,
    QTY INT,
    Size INT,
    CONSTRAINT FOREIGN KEY(SupplierOrderID) REFERENCES SupplierOrder(SupplierOrderID),
    CONSTRAINT FOREIGN KEY(WoodTypeID) REFERENCES WoodType(WoodTypeID)
    );
SHOW TABLES;
DESCRIBE SupplierDetail;

#12.machinery table

DROP TABLE IF EXISTS Machinery;
CREATE TABLE IF NOT EXISTS Machinery(
    MachineID VARCHAR(15) PRIMARY KEY NOT NULL,
    MachineName VARCHAR(30),
    MachineQty int,
    Model VARCHAR(30)
    );
SHOW TABLES;
DESCRIBE Machinery;