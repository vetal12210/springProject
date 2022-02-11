DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS roles (
    id   IDENTITY PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

INSERT INTO roles (id, name) VALUES (DEFAULT, 'admin');
INSERT INTO roles (id, name) VALUES (DEFAULT, 'user');


CREATE TABLE IF NOT EXISTS users (
    id        IDENTITY PRIMARY KEY,
    login     VARCHAR(64) UNIQUE NOT NULL,
    password  VARCHAR(255) NOT NULL,
    email     VARCHAR(64) UNIQUE NOT NULL,
    firstName VARCHAR(64) NOT NULL,
    lastName  VARCHAR(64) NOT NULL,
    birthday  DATE        NOT NULL,
    roles_id   BIGINT      NOT NULL,
    FOREIGN KEY (roles_id) REFERENCES roles (id)
);

INSERT INTO users (login, password, email, firstname, lastname, birthday, roles_id) VALUES ('admin', '$2a$12$CK8tybGqhRvV2vnphpcbbudqH8wLwcR8jc/Z0oSCFtUJaruNhzakK', 'admin@email', 'admin', 'admin', '1990-01-01', 1);
INSERT INTO users (login, password, email, firstname, lastname, birthday, roles_id) VALUES ('user', '$2a$12$FCfzobAxgBNKq6UP7a5dZeNmhfwVPHLaerd6eilU420c2u4jlNChW', 'user@email', 'user', 'user', '2000-02-02', 2);
