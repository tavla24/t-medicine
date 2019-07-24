DROP DATABASE IF EXISTS hospital;
CREATE DATABASE hospital;
USE hospital;

# TODO  table doctors - is it need?
# TODO  periodic, duration - in other table?
# TODO unique
# TODO catch exceptions (delete void or unique add for example)
# TODO lazy query - how grab objects

DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS healings;
DROP TABLE IF EXISTS events;


# for tests ========================================
DROP TABLE IF EXISTS accounts_simple;
CREATE TABLE accounts_simple
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;


CREATE TABLE accounts
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  extend_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

DROP TABLE IF EXISTS accounts_ext;
CREATE TABLE accounts_ext
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  access_level TINYINT UNSIGNED NOT NULL,
  login VARCHAR(31) NOT NULL,
  password_hash BIGINT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

ALTER TABLE accounts 
  ADD CONSTRAINT accounts_accounts_ext_fk 
  FOREIGN KEY (extend_id) 
  REFERENCES accounts_ext(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;
     
INSERT INTO accounts_ext (access_level, login, password_hash) VALUES 
(0, 'user', 5486),
(1, 'root', 2614),
(2, 'admin', 4194);

INSERT INTO accounts (name, extend_id) VALUES 
('pasha', 1),
('pasha', 2),
('pasha', 3),
('masha', 1),
('masha', 3);

INSERT INTO accounts_simple (name) VALUES 
('pasha'),
('masha');
# ==================================================

/*
CREATE TABLE accounts
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  access_level TINYINT UNSIGNED NOT NULL,
  login VARCHAR(31) NOT NULL,
  password_hash BIGINT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;
*/

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
