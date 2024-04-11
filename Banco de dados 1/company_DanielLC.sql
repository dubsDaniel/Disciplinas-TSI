--Nome: Daniel Luis Cabrera - RA: 2096072
-----------PARTE 1 - Consultas Basicas
--1- Liste os escritórios com os seus objetivos (targets) e as vendas reais (sales).
SELECT office, target, sales
	FROM offices;
-------------------------------------------------------
--2 - Listar os nomes, escritórios e datas de contratação de todos os vendedores.
SELECT name, office, hire_date 
	FROM salesreps, offices;
-------------------------------------------------------
--3 -  Quais são o nome, a quota, e as vendas do empregado número 107?
SELECT empl_num, name, quota, sales 
 	FROM salesreps
 		WHERE empl_num IN (107);
-------------------------------------------------------
--4 - Liste o nome e data de admissão de qualquer pessoa com vendas acima de R$ 300,000 (300 mil).
SELECT name, hire_date
	FROM salesreps
		WHERE sales < 300000;
-------------------------------------------------------
--5 - Liste a cidade, região e diferença (acima/abaixo) entre vendas e alvo (targets) para cada escritório
SELECT city, region, (sales - target) AS "Diferenca"
	FROM OFFICES;
-------------------------------------------------------
--6 -  Mostrar o valor do inventário para cada produto.
SELECT mfr_id, product_id, description, (qty_on_hand * price)
	FROM products;
-------------------------------------------------------
--7 - Mostre-me o resultado se eu aumentar a cota de cada vendedor em 3% de suas vendas.
SELECT  ROUND ((0.03 * sales)+ QUOTA, 2)  
	FROM salesreps;
-------------------------------------------------------
--8 - Mostre-me todos os dados na tabela escritórios (offices).
SELECT * 
	FROM offices;
-------------------------------------------------------
--9 - Listar o atributo (número de funcionário) de todos os gerentes de escritório de vendas (Sem duplicatas)
SELECT DISTINCT mgr 
	FROM offices;
-------------------------------------------------------
--10 - Mostre-me os escritórios onde as vendas ultrapassaram a meta.
SELECT office 
	FROM offices
		WHERE sales > target;
-------------------------------------------------------
--11 - Mostre-me o nome, vendas e quota de empregado número 105.
SELECT name, sales, quota 
	FROM salesreps 
		WHERE empl_num IN (105);
-------------------------------------------------------
--12 - Encontrar o pessoal de vendas contratados antes de 2006.
SELECT name 
	from salesreps 
		where hire_date < '1-JAN-2006';
-------------------------------------------------------
--13 - Recuperar o nome e limite de crédito do cliente número 2107
SELECT company, credit_limit 
	FROM customers 
		WHERE cust_num IN (2107);
-------------------------------------------------------
--14 - Encontrar as encomendas feitas no último trimestre de 2007 (usar between).
SELECT order_num 
	FROM orders 
		WHERE order_date BETWEEN '2007-10-01' AND '2007-12-31';
-------------------------------------------------------
--15 - Liste os vendedores que trabalham em Nova York (11), Atlanta (13), ou Denver (22).
SELECT name 
	FROM SALESREPS 
		WHERE rep_office IN (11,13,22);
-------------------------------------------------------
--16 - Exibir o limite de crédito para empresa com um nome que combine com Smith (usar like).
SELECT * 
	FROM customers
		WHERE company LIKE 'Smith%';
-------------------------------------------------------
--17 - Encontrar um vendedor ainda não atribuído a um escritório (CUIDADO).
SELECT name, rep_office 
	FROM salesreps 
		WHERE rep_office is null;
-------------------------------------------------------
--18 - Encontrar vendedores que estão abaixo da quota e com vendas abaixo de $ 300,000.
SELECT name, sales 
	FROM salesreps WHERE (quota > sales) 
		AND sales < 300000;
-------------------------------------------------------
--19 - Liste os escritórios, classificados em ordem decrescente pelo desempenho de vendas, de modo que os escritórios com o melhor desempenho apareçam em primeiro lugar.
SELECT office, sales  
	FROM  offices 
		ORDER BY sales DESC;
-------------------------------------------------------
--20 -  Listar todos os produtos cujo o preço for superior a $ 2000 ou onde mais de $ 30000 do produto foi pedido em um único pedido.
SELECT product, amount
	FROM orders
		WHERE (amount / qty > 2000) OR  (amount > 30000);
-------------------------------------------------------
--21 - Quais são as quotas totais e vendas totais para todos os vendedores?
SELECT quota, sales 
	FROM salesreps;
-------------------------------------------------------
--22 - Calcule o preço médio dos produtos do fabricante ACI
SELECT ROUND(AVG (price), 2) AS "Preco Medio"
	FROM products
		WHERE mfr_id LIKE 'ACI%';
-------------------------------------------------------
--23 - Qual a data do pedido mais antigo no banco de dados?
SELECT MIN (order_date) AS "Data mais antiga"
	 FROM orders;
-------------------------------------------------------
--24 -  Quantos vendedores estão acima da cota?
SELECT COUNT(name) AS "Quantidade Vendedores" 
	FROM salesreps WHERE sales > quota;
-------------------------------------------------------
--25 - Quantos clientes diferentes são atendidos por cada vendedor?
SELECT DISTINCT cust_rep, COUNT(cust_rep) AS "Clientes que atendem" 
	FROM customers GROUP BY cust_rep;
-------------------------------------------------------
--26 - Calcule o valor total dos pedidos para cada cliente de cada vendedor.
SELECT cust, rep,  SUM(amount) 
	FROM orders  
		GROUP BY cust, rep  
			ORDER BY rep;
-------------------------------------------------------
--27 - Qual é o total médio de pedidos para cada vendedor cujos pedidos totalizam de mais de R$ 30000?
SELECT rep, ROUND(AVG (amount), 2)
	FROM orders WHERE amount > 30000 GROUP BY rep;
-------------------------------------------------------
--28 - Para cada escritório com duas ou mais pessoas, calcular as quotas e o total de vendas para todos os vendedores que trabalham no escritório.
SELECT rep_office, SUM(quota), SUM(sales)
	FROM salesreps GROUP BY rep_office 
		HAVING COUNT (rep_office) >= 2;
-------------------------------------------------------
----------PARTE 2 - Subconsultas
--1 - Liste o vendedor cuja meta é inferior a 10 por cento da meta total da empresa.
SELECT name AS "quota insuficiente"
	FROM SALESREPS
	 	WHERE quota < (SELECT SUM(TARGET) * 0.1 FROM offices);
-------------------------------------------------------
--2 - Liste os escritórios onde a meta de vendas para o escritório excede a soma das quotas dos vendedores.
SELECT office 
	FROM offices
		WHERE target > (SELECT SUM(quota) FROM salesreps WHERE rep_office = office);
-------------------------------------------------------
--3 - Liste todos os clientes atendidos por Bill Adams.
SELECT company, cust_num, cust_rep
	FROM customers
		 WHERE cust_rep = (SELECT empl_num FROM salesreps WHERE name = 'Bill Adams');
-------------------------------------------------------
--4 - Liste todos os produtos do fabricante ACI onde a quantidade disponível está acima da quantidade disponível de produto ACI-41004.
SELECT * FROM products 
		WHERE mfr_id = 'ACI' AND qty_on_hand > 
			(SELECT qty_on_hand FROM products 
				WHERE mfr_id = 'ACI' AND product_id = '41004');
-------------------------------------------------------
-- 5 - Liste as vendas das pessoas que não trabalham em escritórios gerenciados por Larry Fitch.
SELECT name, sales
	FROM salesreps 
		WHERE rep_office NOT IN (SELECT office FROM offices 
			WHERE mgr = (SELECT empl_num FROM salesreps WHERE name = 'Larry Fitch'));
-------------------------------------------------------
--6 - Liste todos os clientes atribuídos a Sue Smith que ainda não fizeram uma encomenda de mais de R$ 3000.
SELECT amount, cust 
	FROM orders WHERE rep = 102 AND cust NOT IN 
		(SELECT cust FROM  orders WHERE amount > 3000);
-------------------------------------------------------
--7 - Liste as os vendedores que tomaram um pedido que representa mais de 10 por cento da sua quota (usar o operador any).
SELECT rep 
	FROM orders 
		WHERE amount > ANY  
		(SELECT quota * 0.1 FROM salesreps where empl_num = rep);  
-------------------------------------------------------
--8 -  Liste os escritórios e os seus alvos onde todos os vendedores têm vendas que excedem 50% (por cento) do -alvo do escritório (usar o operador all).
SELECT office, target
	FROM offices 
		WHERE target * 0.5 < ALL 
		(SELECT sales FROM salesreps WHERE rep_office = office);
-------------------------------------------------------
 --9 - Listar os clientes cujos vendedores são atribuídos a escritórios na região de vendas do Leste.
SELECT cust_num 
	FROM customers WHERE cust_rep IN 
		(SELECT empl_num FROM salesreps WHERE  rep_office in 
		(SELECT office FROM offices WHERE region = 'Eastern'));
-------------------------------------------------------
--10 - Listar os gerentes que tem mais de 40 anos e que gerem um vendedor que está acima da cota e que não trabalha no mesmo escritório de vendas como o gerente.
SELECT mgr 
	FROM offices WHERE mgr IN 
	(SELECT manager FROM salesreps WHERE sales > quota AND office <> rep_office) 
		AND mgr IN (SELECT empl_num FROM salesreps WHERE age > 40);
-------------------------------------------------------
--11 -  Liste os vendedores cujo tamanho médio de pedidos para os produtos fabricados por ACI é maior do que o tamanho de pedido médio global.
SELECT rep
	FROM orders 
		WHERE mfr = 'ACI' GROUP BY rep HAVING avg(amount) > (SELECT avg(amount) FROM orders); 
-------------------------------------------------------
--12 - Liste os vendedores cuja tamanho médio de pedidos para os produtos fabricados pela ACI é pelo menos tão grande quanto o tamanho médio de seus pedidos.
SELECT rep
	FROM orders
		WHERE mfr = 'ACI' GROUP BY rep 
		HAVING avg(amount) > (SELECT avg(amount) FROM orders AS med WHERE rep = med.rep);
-------------------------------------------------------
----------PARTE 3 - Joins
--1 - Liste cada vendedor, cidade e região onde trabalham.
SELECT name, city, region
	FROM offices, salesreps 
		WHERE rep_office = office;
-------------------------------------------------------
--2 - Liste os escritórios com um target maior que $ 600000 e as informações do seu gerente.
SELECT office, mgr, age, hire_date
	FROM offices, salesreps
		WHERE target > 600000 AND mgr = empl_num;
-------------------------------------------------------
--3 - A tabela ORDERS e a tabela Products estão relacionadas por um chave estrangeira composta. Liste todas os pedidos, mostrando valores e descrições do produto.
SELECT amount, description, price    
	FROM orders, products
		WHERE mfr_id = mfr AND product_id = product;
-------------------------------------------------------
--4 - Liste os pedidos acima de $ 25.000, incluindo o nome do vendedor que tomou o pedido e o nome do o cliente que fez o pedido.
SELECT name, product, company
	FROM orders, salesreps, customers
	WHERE amount > 25000 AND empl_num = rep AND cust_num = cust;
-------------------------------------------------------
--5 - Liste todas as combinações de vendedores e escritórios onde a quota vendedores é mais do que o target de vendas do escritório , independentemente se o vendedor trabalha lá.
SELECT empl_num, office 
	FROM salesreps
	JOIN offices ON rep_office <> office OR rep_office = office
		WHERE quota > target;
-------------------------------------------------------
--6 - Listar os nomes dos vendedores e seus gerentes.
SELECT vendedor.name, gerente.name
	FROM  salesreps AS vendedor, salesreps as gerente
		WHERE vendedor.empl_num = gerente.manager;
-------------------------------------------------------
--7 - Listar os vendedores que trabalham em escritórios diferentes do que seu gerente, mostrando o nome e escritório onde cada um trabalha.
SELECT saler.name, manager.name, saler.rep_office, manager.rep_office
	FROM  salesreps AS saler, salesreps AS manager
		WHERE saler.empl_num = manager.manager AND saler.rep_office <> manager.rep_office;
-------------------------------------------------------
--8 - Liste os vendedores e as cidades onde trabalham. Apresentar vendedores que não foram assinalados com cidades ainda.
SELECT name, city
	FROM  offices FULL JOIN salesreps
		ON rep_office = office;
-------------------------------------------------------
