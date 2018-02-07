DROP DATABASE IF EXISTS divulgaeditais;

create database divulgaeditais;
use divulgaeditais;

CREATE TABLE modalities (
modality_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
acronyms varchar(10) NOT NULL UNIQUE,
description varchar(50)NOT NULL
);

CREATE TABLE notices (
notice_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
modality_id Integer NOT NULL,
number varchar (8) NOT NULL,
object varchar (50) NOT NULL,
status varchar (20) NOT NULL,
trading_date date NOT NULL,
publishing_date date NOT NULL,
closing_date date NOT NULL,
company_type INTEGER NOT NULL,
FOREIGN KEY(modality_id) REFERENCES modalities (modality_id)
);

CREATE TABLE providers (
provider_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar (50) NOT NULL,
url varchar (50) NULL
);

CREATE TABLE roles (
role_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(80) NOT NULL UNIQUE,
observation varchar(200)NOT NULL
);

CREATE TABLE users (
user_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(100) NOT NULL,
fantasy_name varchar(100) NOT NULL,
email varchar(50) NOT NULL UNIQUE,
password varchar (50) NOT NULL,
cpf varchar (14) NOT NULL UNIQUE,
type varchar (10) NOT NULL,
company_type varchar (10) NOT NULL,
branch varchar (50) NOT NULL,
cnae varchar (10) NOT NULL,
zip_code varchar(50) NOT NULL,
address varchar(50) NOT NULL,
number varchar(5) NOT NULL,
complement varchar(20),
neighborhood varchar(35) NOT NULL,
city varchar(50) NOT NULL,
state varchar(2) NOT NULL,
phone_primary varchar(14) NOT NULL,
phone_secondary varchar(14),
responsible varchar(50)
);

CREATE TABLE users_roles (
user_role_id Integer AUTO_INCREMENT NOT NULL,
user_id Integer NOT NULL,
role_id Integer NOT NULL,
start_date date NOT NULL,
end_date date NOT NULL,
PRIMARY KEY (user_role_id,user_id,role_id,start_date,end_date),
FOREIGN KEY(user_id) REFERENCES users (user_id),
FOREIGN KEY(role_id) REFERENCES roles (role_id)
);