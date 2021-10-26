INSERT INTO app_role(id, name)
VALUES (1, 'ROLE_USER');

INSERT INTO app_user (id, name, password, active)
VALUES (1, 'userA', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', TRUE),
       (2, 'userB', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', TRUE);

INSERT INTO user_role (app_user, app_role)
VALUES (1, 1),
       (2, 1);