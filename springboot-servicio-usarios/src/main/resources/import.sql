INSERT INTO `users` (username, password, enabled, first_name, last_name, email) VALUES ('robert','password',1, 'Robert', 'Redford','rober@mail.com');
INSERT INTO `users` (username, password, enabled, first_name, last_name, email) VALUES ('ella','password',1, 'Ella', 'Fitzgerald','ella@mail.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);