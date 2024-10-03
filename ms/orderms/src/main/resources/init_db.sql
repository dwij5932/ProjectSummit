CREATE SCHEMA IF NOT EXISTS master_data;

CREATE TYPE order_status AS ENUM ('add', 'submit', 'accept', 'delivered', 'fulfilled');

CREATE TABLE IF NOT EXISTS master_data.order_details (
    orderId VARCHAR(20) NOT NULL,
    sellerId VARCHAR(20) NOT NULL,
    customerId VARCHAR(20) NOT NULL,
    prdId VARCHAR(20) NOT NULL,
    amount INTEGER,
    discount DOUBLE  PRECISION,
    price DOUBLE  PRECISION NOT NULL,
    address VARCHAR(255) NOT NULL,
    deliveryDate VARCHAR(255) NOT NULL,
    oderStatus order_status,
    deleted BOOL,
    PRIMARY KEY (orderId)
);
