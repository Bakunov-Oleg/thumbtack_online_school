DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`;
USE `ttschool`;

CREATE TABLE `school`(
`id` INT(11)NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50)NOT NULL,
`year` int(11)NOT NULL,
 UNIQUE KEY school (name, year),
 KEY `name` (`name`),
 KEY `year` (`year`),
PRIMARY KEY(`id`))ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `group`(
`id` INT(11)NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50)NOT NULL,
`room` VARCHAR(50)NOT NULL,
`school_id` INT(11)NOT NULL,
KEY `name` (`name`),
KEY `room` (`room`),
PRIMARY KEY(`id`),
FOREIGN KEY(`school_id`)references ttschool.`school`(`id`)on
delete cascade)
ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `trainee` (`id` INT(11) NOT NULL AUTO_INCREMENT,
 `firstName` VARCHAR(50) NOT NULL,
 `lastName` VARCHAR(50) NOT NULL,
 `rating` INT(11) DEFAULT NULL,
 `group_id` INT(11) DEFAULT NULL,
KEY `firstName` (`firstName`),
KEY `lastName` (`lastName`),
KEY `rating` (`rating`),
FOREIGN KEY(`group_id`)references `group`(`id`) ON DELETE SET NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `subject` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  UNIQUE KEY name (name),
  PRIMARY KEY (`id`)) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `group_subject`(
`id` INT(11)NOT NULL AUTO_INCREMENT,
`group_id` INT(11)NOT NULL,
`subject_id` int(11)NOT NULL,
UNIQUE KEY subject_group(subject_id, group_id),
FOREIGN KEY (subject_id) REFERENCES `subject`(id) ON DELETE CASCADE,
FOREIGN KEY (group_id) REFERENCES `group`(id) ON DELETE CASCADE,
  PRIMARY KEY (`id`)) ENGINE=INNODB DEFAULT CHARSET=utf8;
   
