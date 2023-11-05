
INSERT INTO addresses (street, city, post_code) VALUES
("Annankatu 10 a 3", "Helsinki", "00940"),
("Kapellikuja 2 f 158", "Vantaa", "05550"),
("Liisankatu 28 a 7", "Espoo", "03330");

INSERT INTO app_user  (username, email, password, role, f_name , l_name , address_id) VALUES
("user","mikko@example.fi", "$2a$10$XPMxMF40UOfpo1jxytJ8g.B3uEA9VfqvqHa/WBqTI.HpUew3uzlEa", "USER", "Mikko", "Mallikas", 1),
("admin", "malla.mollamaija@example.fi", "$2a$10$M8I15ZmXIBC2tFTBP/cdR.//tCZ51EXm34tA2/Q/xqyGL2HYyYY9i", "ADMIN","Malla", "Mollamaija", 3),
("usermikko","mikko.mallika@example.fi", "$2a$10$fl1hON2ofpXJWbVgtLnSc.cuYP0nZymYZVO0u87cX33cUi1QydkKO", "USER", "Mikko", "Mallikas", 2),
("Mirva", "mirva@example.fi", "$2a$10$drU4XAgdch68gv8U5CmHx.VCFfQu/cuMY4vnk20E4sQIoSA.a3DJi", "ADMIN","Mirva", "Samuelsson", 3),
("Jaana","jaana.mallika@example.fi", "$2a$10$XPMxMF40UOfpo1jxytJ8g.B3uEA9VfqvqHa/WBqTI.HpUew3uzlEa", "USER", "Jaana", "Mallikas", 2),
("Jussi","jussi@example.fi", "$2a$10$XPMxMF40UOfpo1jxytJ8g.B3uEA9VfqvqHa/WBqTI.HpUew3uzlEa", "USER", "Jussi", "Mallikas", 1);


INSERT INTO cats (name, breed, toy, address_id, user_id, image) VALUES
("Miri", "Scottish long hair", "pallo", 1, 1, null),
("Musti", "Persian", "naru",  2, 1, null),
("Molla", "Thai Siamese", "Hiiri", 3, 1, null),
("Maukka", "Scottish fold", "pallo", 1, 1, null),
("Maukku", "Persian", "naru",  2, 1, null),
("Maukki", "Thai Siamese", "Hiiri", 3, 1, null);


INSERT INTO renting (rental_date, rental_duration, user_id, cat_id) VALUES
("2024-01-01", 5, 1, 1);
