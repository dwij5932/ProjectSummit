CREATE SCHEMA IF NOT EXISTS master_data;

CREATE TABLE IF NOT EXISTS master_data.product_details (
    prdId VARCHAR(20) NOT NULL,
    name VARCHAR(255) NOT NULL,
    sellerId VARCHAR(20) NOT NULL,
    price DOUBLE  PRECISION NOT NULL,
    discount DOUBLE  PRECISION,
    description VARCHAR(255),
    amount INTEGER,
    deleted BOOL,
    PRIMARY KEY (prdId)
);

CREATE TABLE IF NOT EXISTS master_data.image_urls (
    imageId VARCHAR(100) NOT NULL,
    prdId VARCHAR(20) NOT NULL,
    imageUrl VARCHAR(255) NOT NULL,
    altText VARCHAR(255),
    deleted BOOL,
    PRIMARY KEY (imageId),
    FOREIGN KEY (prdId) REFERENCES master_data.product_details(prdId) 
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
