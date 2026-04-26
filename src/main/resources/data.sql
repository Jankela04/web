INSERT INTO users (username, email, password, birth_date, role, blocked)
values ('dzankela', 'tempmail123@yes.no', 'pass123', CURRENT_TIMESTAMP, 'USER', false);

INSERT INTO users (username, email, password, birth_date, role, blocked)
values ('npc', 'stagod@domain.ok', 'amdin123', '2002-01-03', 'USER', true);

INSERT INTO users (username, email, password, birth_date, registration_date, role, blocked)
 values ('Rastko', 'arastko@brate.ok', 'nppjmajstor', '2004-01-09', '2022-10-15', 'ADMIN', false);



INSERT into GAME_CATEGORY (id, name, description)
values (1, 'Rougelike', 'procedurally generated levels where every run is different while having permanent death');

INSERT into GAME_CATEGORY (id, name, description)
values (2, 'RPG', 'Role playing game where player is playing a role bla bla');

INSERT into GAME_CATEGORY (id, name, description)
values (3, 'First-Person shooter', 'Action games with shooting elements played in first perspective');



 INSERT INTO GAME (name, description, path, category_id, added_date, active)
 values ('Slay the Spire', 'goat deckbuilding roguelike', '/src/static/game/sts/index.html', 1, CURRENT_TIMESTAMP, true);

INSERT INTO GAME (name, description, path, image, category_id, added_date, active)
values ('The binding of isaac', 'rougelike', '/src/static/game/tboi/index.html', '/src/static/game/tboi/img.png', 1, CURRENT_TIMESTAMP, true);

 INSERT INTO GAME (name, description, path, image, category_id, added_date, active)
 values ('Skyrim', 'RPG set in the elder scrolls universe', '/src/static/game/skyrim/index.html', '/src/static/game/skyrim/img.png', 2, CURRENT_TIMESTAMP, false);

 INSERT INTO GAME (name, description, path, image, category_id, added_date, active)
values ('Counter-Strike', 'competitive tactical First person shooter', '/src/static/game/cs/index.html', '/src/static/game/cs/img.png', 3, CURRENT_TIMESTAMP, true);



INSERT into REVIEW (rating, game_id, user_id, description)
values (5, 2, 1, 'thats why hes the goat');

INSERT into REVIEW (rating, game_id, user_id)
values (4, 2, 2);

INSERT into REVIEW (rating, game_id, user_id, description)
values (3, 4, 2, 'Csgo better');

INSERT into SESSION(game_id, user_id, started_at, ended_at)
values (2,1, '2025-07-30 13:14:11', '2025-07-30 14:11:10');

INSERT into SESSION(game_id, user_id, started_at, ended_at)
values (2,1, '2026-04-26 13:14:11', CURRENT_TIMESTAMP);

INSERT into SESSION(game_id, user_id, started_at, ended_at)
values (4,3, '2026-02-03 09:01:32', '2026-02-03 10:01:32');

INSERT into ACHIEVEMENT(id, description, name, condition)
values (1, 'Play a game for the first time', 'Baby steps', 'sessions >= 1');

INSERT into ACHIEVEMENT(id, description, name, condition)
values (2, 'Play game for more than 1 hour', 'Noob', 'playTimeHours >= 1');

INSERT into ACHIEVEMENT(id, description, name, condition)
values (3, 'Play a game for more than 6 hours in one sitting', 'Unemployed', 'sessionDurationHours >= 6');

INSERT into ACHIEVEMENT(id, description, name, condition)
values (4, 'Play a game for more than 10 hours', 'Hooked', 'playTimeHours >= 10');

INSERT into USER_ACHIEVEMENT(user_id, game_id, achievement_id)
values (1,2,1);

INSERT into USER_ACHIEVEMENT(user_id, game_id, achievement_id)
values (1,2,2);

INSERT into USER_ACHIEVEMENT(user_id, game_id, achievement_id)
values (1,2,3);

INSERT into USER_ACHIEVEMENT(user_id, game_id, achievement_id)
values (2,4,1);