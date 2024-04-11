--Nome: Daniel Luis Cabrera - RA: 2096072
-----PARTE 3.1 - Complementar sobre Joins
--1 -  Liste os meninos e meninas na mesma cidade, incluindo as meninas e meninos que não tem correspondências.
SELECT boys.name, girls.name
	FROM boys FULL JOIN girls
	ON boys.city = girls.city;
-------------------------------------------------------
--2 - Liste os meninos e meninas na mesma cidade, incluindo as meninas não correspondidas.
SELECT boys.name, girls.name
	FROM boys RIGHT JOIN girls
	ON boys.city = girls.city;