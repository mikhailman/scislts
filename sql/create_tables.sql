create table if not exists sensor
(
    id   INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    key VARCHAR(50),
    sensor_date_time TIMESTAMP
);
-- insert into organization(name, address)
-- values ('roga', 'inf');
-- insert into organization(name, address)
-- values ('kopyta', 'bla-bla');
-- insert into organization(name, address)
-- values ('brosok', 'kobry');

create table if not exists measurment
(
    id      INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    value   INT NOT NULL,
    raining BOOLEAN NOT NULL,
    sensor  INT REFERENCES sensor(id) ON DELETE CASCADE NOT NULL,
    measurement_date_time TIMESTAMP
);
-- insert into employee(name, position, org_id)
-- values ('misha', 'mishin', 1);
-- insert into employee(name, position, org_id)
-- values ('sasha', 'sashin', 2);
-- insert into employee(name, position, org_id)
-- values ('kostya', 'kostin', 3);
--
