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
)