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
)