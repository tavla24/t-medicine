DROP DATABASE IF EXISTS hospital;
CREATE DATABASE hospital;
USE hospital;

# TODO table doctors - is it need?
# TODO periodic, duration - in other table?
# TODO unique
# TODO catch exceptions (delete void or unique add for example)
# TODO lazy query - how grab objects
# TODO serialize class into db

DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS healings;
DROP TABLE IF EXISTS events;

CREATE TABLE accounts
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(31) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE roles
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  type TINYINT UNSIGNED NOT NULL UNIQUE,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

/* ManyToMany relation
CREATE TABLE accounts_roles (
    account_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (account_id, role_id),
    CONSTRAINT accounts_fk FOREIGN KEY (account_id) REFERENCES accounts (id),
    CONSTRAINT roles_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);
*/

CREATE TABLE persons
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255),
  patronymic VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  phone VARCHAR(255) UNIQUE,
  account_id INT UNSIGNED UNIQUE,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE doctors
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  specialization VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;
  
ALTER TABLE doctors 
  ADD CONSTRAINT doctors_persons_fk 
  FOREIGN KEY (id) 
  REFERENCES persons(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;

CREATE TABLE patients
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  doctor_id INT UNSIGNED NOT NULL,
  diagnosis VARCHAR(255) NOT NULL,
  insuranceid BIGINT UNSIGNED NOT NULL,
  status TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

ALTER TABLE patients 
  ADD CONSTRAINT patients_persons_fk 
  FOREIGN KEY (id) 
  REFERENCES persons(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;

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

CREATE TABLE persistent_logins (
  series VARCHAR(64) NOT NULL,
  username VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL,
  PRIMARY KEY (series)
) ENGINE=InnoDB CHARACTER SET=UTF8;

ALTER TABLE accounts 
  ADD CONSTRAINT accounts_roles_fk 
  FOREIGN KEY (role_id) 
  REFERENCES roles(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;
     
ALTER TABLE persons 
  ADD CONSTRAINT persons_accounts_fk 
  FOREIGN KEY (account_id) 
  REFERENCES accounts(id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;

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

# for tests ========================================
INSERT INTO roles (type) VALUES 
(0), (1), (2), (3), (4), (5);

INSERT INTO accounts (login, password, role_id) VALUES 
('root', '1111', 1),
('admin', '2222', 2),
('user', '3333', 3),
('doctor', '4444', 4),
('nurse', '5555', 5),
('patient', '6666', 6);

INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Ivanov', 'Sergey', 'Aleksandrovich', 'ivanov@mail.ru', '+79214737482', 1);
INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Petrov', 'Arseniy', 'Konstantinovich', 'petrov@mail.ru', '+79219373549', 5);
INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Sidorov', 'Konstantin', 'Victorovich', 'sidorov@mail.ru', '+79219823575', 6);

INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Bochkareva', 'Ekaterina', 'Sergeevna', 'bochkareva@mail.ru', '+79218365208', 4);

INSERT INTO doctors (id, specialization) VALUES
(4, 'okulist');

INSERT INTO patients (id, doctor_id, diagnosis, insuranceid, status) VALUES
(2, 4, 'dalnozorkost', 9872350982357879635, 1),
(3, 4, 'astigmatizm', 876358327659328755, 1);
# ==================================================