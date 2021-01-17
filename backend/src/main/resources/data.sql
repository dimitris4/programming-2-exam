INSERT INTO supervisors (id, email, first_name, last_name) VALUES (1, 'tomasp@kea.com', 'Tomas', 'Pesek') ON DUPLICATE KEY UPDATE id=1;
INSERT INTO supervisors (id, email, first_name, last_name) VALUES (2, 'erikm@kea.com', 'Erik', 'Møller') ON DUPLICATE KEY UPDATE id=2;
INSERT INTO supervisors (id, email, first_name, last_name) VALUES (3, 'arturor@kea.com', 'Arturo', 'Rioja') ON DUPLICATE KEY UPDATE id=3;
INSERT INTO supervisors (id, email, first_name, last_name) VALUES (4, 'douglasb@kea.com', 'Douglas', 'Beaver') ON DUPLICATE KEY UPDATE id=4;
INSERT INTO supervisors (id, email, first_name, last_name) VALUES (5, 'oscart@kea.com', 'Oscar', 'Tuska') ON DUPLICATE KEY UPDATE id=5;
