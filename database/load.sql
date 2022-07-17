INSERT INTO app_role(id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_GUEST');

-- testpass
INSERT INTO app_user (id, name, password, active)
VALUES (1, 'userA', '$2a$10$jzp2nZsRRFpQyWfv/AgCzO9AEklEN.bkd/nS6Sbuvxtbb95yI.N2C', TRUE),
       (2, 'userB', '$2a$10$jzp2nZsRRFpQyWfv/AgCzO9AEklEN.bkd/nS6Sbuvxtbb95yI.N2C', TRUE);

INSERT INTO user_role (app_user, app_role)
VALUES (1, 1),
       (2, 1);