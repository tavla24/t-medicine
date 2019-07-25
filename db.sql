DROP DATABASE IF EXISTS hospital;
CREATE DATABASE hospital;
USE hospital;

# TODO  table doctors - is it need?
# TODO  periodic, duration - in other table?
# TODO unique
# TODO catch exceptions (delete void or unique add for example)
# TODO lazy query - how grab objects
# TODO serialize class into db

DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS healings;
DROP TABLE IF EXISTS events;


CREATE TABLE accounts
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  access_level TINYINT UNSIGNED NOT NULL,
  login VARCHAR(31) NOT NULL,
  password_hash BIGINT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

# for tests ========================================
INSERT INTO accounts (name, access_level, login, password_hash) VALUES 
('name_root', 1, 'root', 1111),
('name_admin', 2, 'admin', 2222),
('name_user', 3, 'user', 3333);
# ==================================================

CREATE TABLE persistent_logins (
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL,
  PRIMARY KEY (series)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE doctors
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE patients
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  doctor_id INT UNSIGNED NOT NULL,
  diagnosis VARCHAR(255) NOT NULL,
  insuranceid BIGINT UNSIGNED NOT NULL,
  status TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE healings
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  type TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE recipes
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  patient_id INT UNSIGNED NOT NULL,
  healing_id INT UNSIGNED NOT NULL,
  dose FLOAT,
  periodic DATETIME NOT NULL,
  duration DATETIME NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE events
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  recipe_id INT UNSIGNED NOT NULL,
  status TINYINT UNSIGNED NOT NULL,
  date TIMESTAMP NOT NULL,
  info VARCHAR(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

ALTER TABLE patients 
  ADD CONSTRAINT patients_doctors_fk 
  FOREIGN KEY (doctor_id) 
  REFERENCES doctors(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;
  
ALTER TABLE recipes 
  ADD CONSTRAINT recipes_patients_fk 
  FOREIGN KEY (patient_id) 
  REFERENCES patients(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;
  
ALTER TABLE recipes 
  ADD CONSTRAINT recipes_healings_fk 
  FOREIGN KEY (healing_id) 
  REFERENCES healings(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;
  
ALTER TABLE events 
  ADD CONSTRAINT events_recipes_fk 
  FOREIGN KEY (recipe_id) 
  REFERENCES recipes(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;
