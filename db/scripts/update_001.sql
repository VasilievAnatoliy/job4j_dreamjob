CREATE TABLE IF NOT EXISTS post (
     id          SERIAL PRIMARY KEY,
     name        TEXT,
     description TEXT,
     created     TEXT,
     visible     boolean,
     city_id     int
);

CREATE TABLE IF NOT EXISTS candidate (
     id          SERIAL PRIMARY KEY,
     name        TEXT,
     description TEXT,
     photo       BYTEA,
     created     TEXT,
     visible     boolean,
     city_id     int
);

CREATE TABLE IF NOT EXISTS users (
     id         SERIAL PRIMARY KEY,
     name       TEXT,
     email      VARCHAR,
     password   TEXT
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email)