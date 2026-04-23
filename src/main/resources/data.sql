INSERT INTO users (username, email, password, birth_date, role, blocked)
 values ('dzankela', 'tempmail123@yes.no', 'pass123', CURRENT_TIMESTAMP, 'USER', false);

INSERT INTO users (username, email, password, birth_date, role, blocked)
 values ('npc', 'stagod@domain.ok', 'amdin123', '2002-01-03', 'USER', true);

INSERT INTO users (username, email, password, birth_date, registration_date, role, blocked)
 values ('Rastko', 'arastko@brate.ok', 'nppjmajstor', '2004-01-09', '2022-10-15', 'ADMIN', false);



 INSERT INTO GAME (name, description, path, category_id, added_date, active) 
 values ('Slay the Spire', 'goat deckbuilding roguelike', '/src/static/game/sts/index.html', 1, CURRENT_TIMESTAMP, true);

 INSERT INTO GAME (name, description, path, image, category_id, added_date, active) 
 values ('The binding of isaac', 'rougelike', '/src/static/game/tboi/index.html', '/src/static/game/tboi/img.png', 1, CURRENT_TIMESTAMP, true);

 INSERT INTO GAME (name, description, path, image, category_id, added_date, active) 
 values ('Skyrim', 'RPG set in the elder scrolls universe', '/src/static/game/skyrim/index.html', '/src/static/game/skyrim/img.png', 2, CURRENT_TIMESTAMP, false);

 INSERT INTO GAME (name, description, path, image, category_id, added_date, active) 
 values ('Counter-Strike', 'competitive tactical First person shooter', '/src/static/game/cs/index.html', '/src/static/game/cs/img.png', 3, CURRENT_TIMESTAMP, true);