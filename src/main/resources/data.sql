INSERT INTO users (id, username, password) VALUES (1, 'user1', '$2a$10$JW5g2EIZ09mqsLE0OSV.ieLoYUGMB7vnYoCmZF06pwvdthE1fPMju');
INSERT INTO users (id, username, password) VALUES (2, 'user2', '$2a$10$oS2RhAasIrkO6UsZmIwKquT7j.WqzFXbwt8Ynrnj3NpXmfDvDYYVu');

INSERT INTO roles (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT INTO roles (id, description, name) VALUES (5, 'User role', 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 5);