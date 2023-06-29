create table users (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(20) NOT NULL,
                        active BOOLEAN,
                        status int,
                        address_id INT,
                        creation_date date,
                        last_login_date date,
                        name VARCHAR(50)
);

create table address (
                         id SERIAL PRIMARY KEY,
                         users INT REFERENCES users(id),
                         street VARCHAR(250),
                         zip VARCHAR(250),
                         number VARCHAR(250),
                         consignee VARCHAR(250)
);