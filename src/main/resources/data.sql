
INSERT INTO addresses (street, city, post_code) VALUES
("katu", "Helsinki", "00940"),
("kuja", "Vantaa", "05550"),
("katu", "Espoo", "03330");

INSERT INTO app_user  (username, email, password, role, f_name , l_name , address_id) VALUES
("user","mikko.mallika", "$2a$10$XPMxMF40UOfpo1jxytJ8g.B3uEA9VfqvqHa/WBqTI.HpUew3uzlEa", "USER", "Mikko", "Mallikas", 1),
("admin", "malla.mollamaija", "$2a$10$M8I15ZmXIBC2tFTBP/cdR.//tCZ51EXm34tA2/Q/xqyGL2HYyYY9i", "ADMIN","Malla", "Mollamaija", 3);

INSERT INTO cats (name, breed, toy, available, address_id, user_id) VALUES
("Miri", "Scottish long hair", "pallo", true, 1, 2),
("Musti", "Persian", "naru", true, 2, 2),
("Molla", "Thai Siamese", "Hiiri", true, 3, 2);

INSERT INTO renting (rental_date, rental_duration, user_id, cat_id) VALUES
("2024-01-01", 5, 1, 1);
