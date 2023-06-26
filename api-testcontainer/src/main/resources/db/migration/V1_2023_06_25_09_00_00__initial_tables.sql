create table users (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        user_name VARCHAR(50) NOT NULL,
                        email VARCHAR(20) NOT NULL,
                        active BOOLEAN,
                        status int,
                        address_id INT,
                        creation_date date,
                        last_login_date date,
                        name VARCHAR(50)
);

create table address (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         users INT REFERENCES users(id),
                         street VARCHAR(250),
                         zip VARCHAR(250),
                         number VARCHAR(250),
                         consignee VARCHAR(250)
);