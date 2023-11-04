DROP TABLE IF EXISTS renting;
DROP TABLE IF EXISTS cats;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS addresses;


CREATE TABLE IF NOT EXISTS addresses
(address_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
, street VARCHAR(50) 
, city VARCHAR(50) 
, post_code VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS app_user
(user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY 
, username VARCHAR(50) 
, email VARCHAR(50) 
, password VARCHAR(250)
, f_name VARCHAR(50) 
, l_name VARCHAR(50)
, role VARCHAR(10)
, address_id BIGINT
, FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

CREATE TABLE IF NOT EXISTS cats
(cat_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY 
, name VARCHAR(50) NOT NULL
, breed VARCHAR(50) NOT NULL
, toy VARCHAR(50) NOT NULL
, address_id BIGINT
, user_id BIGINT
, FOREIGN KEY (address_id) REFERENCES addresses(address_id)
, FOREIGN KEY (user_id) REFERENCES app_user(user_id)
);

CREATE TABLE IF NOT EXISTS renting
(renting_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
, rental_date DATE
, rental_duration INT
, cat_id BIGINT
, user_id BIGINT
, FOREIGN KEY (user_id) REFERENCES app_user(user_id)
, FOREIGN KEY (cat_id) REFERENCES cats(cat_id)
);

