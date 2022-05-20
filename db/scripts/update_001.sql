CREATE TABLE post (
    id   SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TEXT,
    visible boolean,
    city_id int
);