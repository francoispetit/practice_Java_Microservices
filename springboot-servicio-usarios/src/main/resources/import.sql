INSERT INTO `users` (username, password, enabled, first_name, last_name, email) VALUES ('robert','$2a$10$2DQlkKb3M/bzXi.E/7rBouafKsi3s3zJf4ZNuP1KwB2i4qF29ishm',1, 'Robert', 'Redford','rober@mail.com');
INSERT INTO `users` (username, password, enabled, first_name, last_name, email) VALUES ('ella','$2a$10$bR4eQE7BdfK2J5jqeglPoO7dzTdASRPGo1ivtJgEHVh55jpaVN8Eq',1, 'Ella', 'Fitzgerald','ella@mail.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);