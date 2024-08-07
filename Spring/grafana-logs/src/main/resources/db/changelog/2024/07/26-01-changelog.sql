-- liquibase formatted sql

-- changeset Classic:1721987602214-1
CREATE TABLE food
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_food PRIMARY KEY (id)
);

-- changeset Classic:1721987602214-2
CREATE TABLE menu
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    restaurant_id BIGINT,
    food_id       BIGINT,
    price         DOUBLE PRECISION,
    CONSTRAINT pk_menu PRIMARY KEY (id)
);

-- changeset Classic:1721987602214-3
CREATE TABLE restaurant
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name   VARCHAR(255),
    street VARCHAR(255),
    city   VARCHAR(255),
    state  VARCHAR(255),
    zip    VARCHAR(255),
    CONSTRAINT pk_restaurant PRIMARY KEY (id)
);

-- changeset Classic:1721987602214-4
ALTER TABLE menu
    ADD CONSTRAINT FK_MENU_ON_FOOD FOREIGN KEY (food_id) REFERENCES food (id);

-- changeset Classic:1721987602214-5
ALTER TABLE menu
    ADD CONSTRAINT FK_MENU_ON_RESTAURANT FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

