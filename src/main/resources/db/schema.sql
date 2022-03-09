CREATE TABLE warehouse
(
    product_id INT PRIMARY KEY,
    count      INT
);

INSERT INTO warehouse(product_id, count)
VALUES (1, 20),
       (2, 30),
       (3, 70),
       (4, 89),
       (5, 50),
       (6, 100),
       (7, 75),
       (8, 78),
       (9, 25),
       (10, 65);



CREATE TABLE product
(
    product_id   BIGSERIAL NOT NULL PRIMARY KEY,
    name         VARCHAR(30),
    price        DECIMAL(8, 2),
    isDiscount   BOOLEAN,
    warehouse_id INT,
    FOREIGN KEY (warehouse_id) REFERENCES warehouse (product_id)
);

INSERT INTO product(name, price, isDiscount, warehouse_id)
VALUES ('milk', '123', FALSE, 1),
       ('apple', '57', TRUE, 2),
       ('meat', '432', FALSE, 3),
       ('Orange', '34', TRUE, 4),
       ('cucumbers', '13.12', FALSE, 5),
       ('bananas', '12.1', TRUE, 6),
       ('Cherry', '23.2', FALSE, 7),
       ('pineapple', '7.12', TRUE, 8),
       ('crisps', '8.12', FALSE, 9),
       ('tangerines', '41.12', TRUE, 10);

CREATE TABLE cart
(
    cart_id  BIGSERIAL NOT NULL PRIMARY KEY,
    discount DECIMAL(8, 2)
);

INSERT INTO cart(discount)
VALUES ('12.5'),
       ('13'),
       ('13.2'),
       ('9.5'),
       ('8.5'),
       ('0');