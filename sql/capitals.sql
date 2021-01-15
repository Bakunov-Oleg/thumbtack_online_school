DROP DATABASE IF EXISTS `capitals`;
CREATE DATABASE `capitals`;
USE `capitals`;

CREATE TABLE `capitalcity`(
`id` INT(11)NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50)NOT NULL,
UNIQUE KEY capitalcity(name),
PRIMARY KEY(`id`))ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO capitals.capitalcity(`name`)
VALUES ('Yerevan');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Brussels');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Gaborone');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Brasilia');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Ottawa');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Bratislava');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Ljubljana');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Warsaw');
INSERT INTO capitals.capitalcity(`name`)
VALUES ('Moscow');