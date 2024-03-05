/*Написать запрос для поиска товара с названием начинающимся на “А”*/
SELECT name FROM product WHERE name LIKE 'a%';

/*Написать запрос с вложенным запросом*/
SELECT DISTINCT name FROM product;
SELECT SUM(price) FROM product;
SELECT name, price FROM product WHERE price = (SELECT MIN(price) FROM product);
/*Написать запросы с JOIN*/
SELECT product.name AS name, product.price, count
FROM product INNER JOIN warehouse w on product.warehouse_id = w.product_id;