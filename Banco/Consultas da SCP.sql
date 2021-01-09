SELECT * FROM enderecos;
SELECT * FROM produtos;
SELECT * FROM telefones;

ALTER TABLE produtos ADD id_cliente INT; 

SELECT * FROM produtos LEFT OUTER JOIN clientes ON produtos.id_cliente = clientes.id;
SELECT * FROM produtos RIGHT OUTER JOIN clientes ON produtos.id_cliente = clientes.id;

SELECT * FROM enderecos LEFT OUTER JOIN clientes ON enderecos.id_cliente = clientes.id;
SELECT * FROM enderecos RIGHT OUTER JOIN clientes ON enderecos.id_cliente = clientes.id;

SELECT * FROM telefones LEFT OUTER JOIN clientes ON telefones.id_cliente = telefones.id;
SELECT * FROM telefones RIGHT OUTER JOIN clientes ON telefones.id_cliente = telefones.id;

SELECT * FROM produtos LEFT OUTER JOIN clientes ON produtos.id_cliente = clientes.id 
UNION SELECT * FROM clientes RIGHT OUTER JOIN produtos ON produtos.id_cliente = clientes.id;

SELECT * FROM enderecos LEFT OUTER JOIN clientes ON enderecos.id_cliente = clientes.id 
UNION SELECT * FROM clientes RIGHT OUTER JOIN enderecos ON enderecos.id_cliente = clientes.id;

SELECT * FROM telefones LEFT OUTER JOIN clientes ON telefones.id_cliente = clientes.id 
UNION SELECT * FROM clientes RIGHT OUTER JOIN telefones ON telefones.id_cliente = clientes.id;

SELECT e.id, e.cidade, e.estado, e.logradouro, e.bairro, e.numero, e.cep, e.id_cliente FROM enderecos AS e CROSS JOIN clientes AS c;
SELECT p.id, p.discriminacao, p.valor_unitario, p.id_cliente FROM produtos AS p CROSS JOIN clientes AS c;
SELECT t.id, t.numero, t.id_cliente FROM telefones AS t CROSS JOIN clientes AS c;
