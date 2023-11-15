INSERT INTO family (name) values ('rosé'),('blanc'),('rouge'),('pétillant');

INSERT INTO role (name) values ('admin'),('fournisseur'),('client'),('gestionnaire');

INSERT INTO user (login, password ,role_id) values
('admin','root',1),
('fournisseur 1','root',2),
('fournisseur 2','root',2),
('franck','root',4);

INSERT INTO product (name, quantity, supplier_id) values
('champagne cave 1',50,1),
('champagne cave 2',30,2),
('lambrusco',10,2);

INSERT INTO family_product (product_id, family_id) values
(1,4),
(1,2),
(2,4),
(2,2),
(3,3),
(3,4);

INSERT INTO purchase_order (date,status, user_id) VALUES
('2023-11-15 14:29:44', "EN ATTENTE", 1);

INSERT INTO purchase_order_line (quantity, order_id, product_id) VALUES
(10, 1, 1),(20, 1, 2);

