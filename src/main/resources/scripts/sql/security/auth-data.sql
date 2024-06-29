INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_WITH_NOT_PRIVILEGES');

INSERT INTO users (username, password) VALUES ('user', '$2a$10$j2RTyMWPxEfWrTtlYd7L/O3Gt8R9UUHubjPtf3zNi/xq41vHdD0fq'); -- userpassword
INSERT INTO users (username, password) VALUES ('admin', '$2a$10$j2RTyMWPxEfWrTtlYd7L/O3Gt8R9UUHubjPtf3zNi/xq41vHdD0fq'); -- adminpassword
INSERT INTO users (username, password) VALUES ('user2', '$2a$10$g/NPJC0mhk4/TlcrobrWxODLxYyRv8/BzLCbUt.vrXLXKOkuEJDT2'); -- adminpassword

INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));
INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'user2'), (SELECT id FROM roles WHERE name = 'ROLE_WITH_NOT_PRIVILEGES'));