CREATE TABLE categories (
    id serial primary key,
    name character varying(255),
    description character varying(255)
);

CREATE TABLE suppliers (
    id serial primary key,
    name character varying(255),
    contactname character varying(255),
    address character varying(255),
    city character varying(255),
    postalcode character varying(20),
    country character varying(50),
    phone character varying(50)
);

CREATE TABLE products (
    id serial primary key,
    name character varying(255),
    supplierid integer not null references suppliers(id),
    categoryid integer not null references categories(id),
    unit character varying(255),
    price numeric(10,2)
);

CREATE TABLE customers (
    id serial primary key,
    name character varying(255),
    contactname character varying(255),
    address character varying(255),
    city character varying(255),
    postalcode character varying(20),
    country character varying(50)
);

CREATE TABLE employees (
    id serial primary key,
    lastname character varying(255),
    firstname character varying(255),
    birthdate date,
    photo character varying(255),
    notes text
);

CREATE TABLE shippers (
    id serial primary key,
    name character varying(255),
    phone character varying(50)
);