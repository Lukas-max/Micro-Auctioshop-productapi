DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS product CASCADE;

CREATE TABLE IF NOT EXISTS product_category (
category_id BIGSERIAL PRIMARY KEY,
category_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
product_id BIGSERIAL PRIMARY KEY,
sku VARCHAR (255),
name VARCHAR (255),
description TEXT,
unit_price NUMERIC (19,2),
product_image bytea,
active boolean,
units_in_stock INTEGER,
date_time_created TIMESTAMP without time zone,
date_time_updated TIMESTAMP without time zone,
category_id BIGINT REFERENCES product_category(category_id)
);
