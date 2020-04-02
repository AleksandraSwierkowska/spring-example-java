INSERT INTO users(login, email, password, role)
VALUES ('test', 'test@test.pl', 'test123', 'USER');

INSERT INTO users(login, email, password, role)
VALUES ('admin', 'admin@test.pl', 'admin', 'ADMIN');

INSERT INTO ingredients(name)
VALUES ('cukier');
INSERT INTO ingredients(name)
VALUES ('mąka');
INSERT INTO ingredients(name)
VALUES ('mleko');
INSERT INTO ingredients(name)
VALUES ('woda');
INSERT INTO ingredients(name)
VALUES ('jajka');

INSERT INTO recipes(title, description)
VALUES ('Naleśniki', 'Najlepsze naleśniki babuni');

INSERT INTO recipes_has_ingredient(recipe_id, ingredient_id, quantity, unit)
VALUES (1, 1, 0.5, 'szklanki');
INSERT INTO recipes_has_ingredient(recipe_id, ingredient_id, quantity, unit)
VALUES (1, 2, 2.5, 'szklanki');
INSERT INTO recipes_has_ingredient(recipe_id, ingredient_id, quantity, unit)
VALUES (1, 3, 0.5, 'litra');
INSERT INTO recipes_has_ingredient(recipe_id, ingredient_id, quantity, unit)
VALUES (1, 4, 0.5, 'sklanki');
INSERT INTO recipes_has_ingredient(recipe_id, ingredient_id, quantity, unit)
VALUES (1, 5, 3, 'szt');

INSERT INTO favorites(users_id, recipes_id)
VALUES (1, 1);