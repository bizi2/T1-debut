-- liquibase formatted sql

-- changeset author:your_name id:v2-admin-user
-- precondition: not (table_has_rows('users') and table_has_rows('user_roles'))
INSERT INTO users (username, password, email)
VALUES ('admin', '$2a$10$xJwL5vZPe.KzY3VjTsdS.Ok7Pe7JdH2WXqjZ7cF3zR9Tk0JQY6XaK', 'admin@example.com');

-- changeset author:your_name id:v2-admin-roles
INSERT INTO user_roles (user_id, role)
VALUES
((SELECT id FROM users WHERE username = 'admin'), 'ROLE_ADMIN'),
((SELECT id FROM users WHERE username = 'admin'), 'ROLE_USER');