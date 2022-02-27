/*Написать запрос для поиска товара с названием начинающимся на “А”*/
SELECT name FROM trip WHERE name LIKE 'А%';

/* Написать запрос с вложенным запросом */

/*Написать запрос с вложенным запросом*/
/*Выводит всех сотрудников*/
SELECT DISTINCT name FROM trip;
/*Для каждого города расчитывает, сколько раз сотрудники в нем были*/
SELECT city, COUNT(name) AS Количество FROM trip GROUP BY city ORDER BY city;
/*Выводит два города, в которых чаще всего были в командировках сотрудники*/
SELECT city, COUNT(city) AS Количество FROM trip GROUP BY city ORDER BY Количество DESC LIMIT 2;
/*Выводит информацию о командировках во все города кроме Москвы и Санкт-Петербурга
  (фамилии и инициалы сотрудников, город , длительность командировки в днях,
  при этом первый и последний день относится к периоду командировки).
  Информацию выводит в упорядоченном по убыванию длительности поездки,
  а потом по убыванию названий городов (в обратном алфавитном порядке).*/
SELECT name, city, (date_mi(date_last, date_first)) + 1 AS Длительность FROM trip WHERE city <> 'Москва' AND city <> 'Санкт-Петербург' ORDER BY Длительность DESC, city DESC;
/*Вывести информацию о командировках сотрудника(ов), которые были самыми короткими по времени.*/
SELECT name, city, date_first, date_last FROM trip WHERE date_mi(date_last, date_first) = (SELECT MIN(date_mi(date_last, date_first)) FROM trip);

/*Написать запросы с JOIN*/
SELECT foo.name, foo.department_id AS deportament
FROM (SELECT DISTINCT name, department_id FROM trip) AS foo INNER JOIN department
ON foo.department_id = department.department_id;