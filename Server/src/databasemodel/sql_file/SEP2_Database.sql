DROP SCHEMA If EXISTS renting_system cascade;
CREATE SCHEMA renting_system;
SET SCHEMA 'renting_system';

CREATE TYPE rent_type AS ENUM ('Apartment','House','Room');
CREATE TYPE Role AS ENUM ('Landlord','Tenant');
CREATE DOMAIN offer_title AS VARCHAR(50) NOT NULL;
CREATE DOMAIN description AS VARCHAR(500) NOT NULL;
CREATE DOMAIN location AS VARCHAR(50) NOT NULL;
CREATE DOMAIN floor AS INTEGER;
CREATE DOMAIN deposit AS DOUBLE PRECISION NOT NULL;
CREATE DOMAIN price_per_month AS DOUBLE PRECISION NOT NULL;
CREATE DOMAIN area AS DOUBLE PRECISION NOT NULL;
CREATE DOMAIN date AS DATE NOT NULL;
CREATE DOMAIN Username AS VARCHAR(30) NOT NULL;
CREATE DOMAIN noOfRooms AS INTEGER NOT NULL;

CREATE TABLE User1
(
    username              Username NOT NULL ,
    password              VARCHAR(30) NOT NULL ,
    first_name            VARCHAR(50) NOT NULL ,
    middle_name           VARCHAR(50),
    last_name             VARCHAR(50) NOT NULL ,
    role                  Role NOT NULL ,
    phone                 INTEGER NOT NULL ,
    PRIMARY KEY (username)
);
CREATE TABLE Offer
(
    id                  integer UNIQUE,
    title               offer_title UNIQUE,
    description         description UNIQUE,
    location            location UNIQUE,
    type                rent_type UNIQUE,
    floor               floor CHECK (floor > 0) UNIQUE,
    price_per_month     price_per_month CHECK (price_per_month > 0) UNIQUE,
    deposit             deposit CHECK (deposit > 0) UNIQUE,
    area                area CHECK (area > 0) UNIQUE,
    number_of_rooms     noOfRooms UNIQUE,
    available_date_from date UNIQUE,
    landlord_username   Username UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (landlord_username) REFERENCES User1 (username)
);


CREATE TABLE Renting
(
    id                  INTEGER NOT NULL,
    renting_title       offer_title,
    description         description,
    location            location,
    type                rent_type,
    floor               floor CHECK (floor > 0),
    price_per_month     price_per_month CHECK (price_per_month > 0),
    deposit             deposit CHECK (deposit > 0),
    area                area CHECK (area > 0),
    available_date_from date UNIQUE,
    landlord_username   Username,
    tenant_username     Username,
    tenant_feedback     VARCHAR(100),
    landlord_feedback   VARCHAR(100),
    PRIMARY KEY (id),
    FOREIGN KEY (landlord_username) REFERENCES User1 (username),
    FOREIGN KEY (tenant_username) REFERENCES User1 (username)
);
CREATE TABLE Message
(
    sender_username   Username,
    receiver_username Username,
    body_message      VARCHAR(200),
    PRIMARY KEY (sender_username, receiver_username),
    FOREIGN KEY (sender_username) REFERENCES User1 (username),
    FOREIGN KEY (receiver_username) REFERENCES User1 (username)

);

INSERT INTO User1(username, password, first_name, middle_name, last_name, role, phone)  VALUES ('johny123','alandalo','Johny','','Andreius','Tenant',1231231);
INSERT INTO Offer(id, title, description, location, type, floor, price_per_month, deposit, area,number_of_rooms,
                  available_date_from,
                  landlord_username)
VALUES (1, 'title1', 'description1', 'center', 'House', 4, 5000, 20000, 150.4, 4, '2021-11-01', 'johny123'
        );
INSERT INTO Offer(id, title, description, location, type, floor, price_per_month, deposit, area,number_of_rooms,
                  available_date_from,
                  landlord_username)
VALUES (2, 'title2', 'description1', 'center', 'House', 4, 5000, 20000, 150.4, 4, '2021-11-01', 'johny123'
        );

SELECT * FROM offer WHERE title LIKE 'title2';
DELETE FROM Offer WHERE title = 'title2' AND landlord_username = 'johny123';
SELECT * FROM Offer