INSERT INTO PhotoItem(id, category, name) VALUES (nextval('hibernate_sequence'), 'animals', 'Odie')
INSERT INTO PhotoItem(id, category, name) VALUES (nextval('hibernate_sequence'), 'animals', 'Garfield')
INSERT INTO PhotoItem(id, category, name) VALUES (nextval('hibernate_sequence'), 'buildings', 'Empire state building')

INSERT INTO LikesItem(id, likes) VALUES (1, 5)
INSERT INTO LikesItem(id, likes) VALUES (2, 10)

INSERT INTO QueryItem(id, name, category, likes) VALUES (1, 'Odie', 'animals', 5)
INSERT INTO QueryItem(id, name, category, likes) VALUES (2, 'Garfield', 'animals', 10)
INSERT INTO QueryItem(id, name, category, likes) VALUES (3, 'Empire state building', 'buildings', 0)
