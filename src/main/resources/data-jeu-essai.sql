INSERT INTO country (name) values ('france'),('allemagne');

INSERT INTO role (name) values ('admin'),('editeur'),('lecteur');

INSERT INTO user (login, country_id) values ('franck',1),('admin',2);

INSERT INTO user_role (user_id, role_id) values (1,2),(1,3),(2,1);