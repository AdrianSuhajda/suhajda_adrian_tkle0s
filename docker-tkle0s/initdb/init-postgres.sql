CREATE ROLE tkle0s_dog_toys WITH
    LOGIN
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    REPLICATION
    CONNECTION LIMIT -1
    PASSWORD '12345678';

CREATE TABLE toy
(
    id    SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image  VARCHAR(100) NOT NULL,
    description  VARCHAR(100) NOT NULL,
    cost  INTEGER NOT NULL
);

CREATE TABLE cart
(
    id    SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image  VARCHAR(100) NOT NULL,
    description  VARCHAR(100) NOT NULL,
    cost  INTEGER NOT NULL,
    quantity  INTEGER NOT NULL
);

CREATE TABLE payment
(
    id    SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cardNumber INTEGER NOT NULL,
    expireDate INTEGER NOT NULL,
    cvc INTEGER NOT NULL
);

INSERT INTO toy(name, image, description, cost)
VALUES ('duck', 'duck.png', 'duck duck duck duck', 150),
       ('ball', 'ball.png', 'ball ball ball ball', 80),
       ('rope', 'rope.png', 'rope rope rope rope', 120),
       ('bone', 'bone.png', 'bone bone bone bone', 315);