DROP DATABASE IF EXISTS hospital;
CREATE DATABASE hospital;
USE hospital;

# TODO table doctors - is it need?
# TODO periodic, duration - in other table?
# TODO unique
# TODO catch exceptions (delete void or unique insert for example)
# TODO lazy query - how grab objects
# TODO serialize class into db

DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS recipes_day_parts;
DROP TABLE IF EXISTS recipes_day_names;
DROP TABLE IF EXISTS healings;
DROP TABLE IF EXISTS events;

CREATE TABLE accounts
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(127) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE roles
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  type VARCHAR(31) NOT NULL UNIQUE,
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
  surname VARCHAR(255) NOT NULL,
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

CREATE TABLE patients
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  doctor_id INT UNSIGNED NOT NULL,
  diagnosis VARCHAR(255) NOT NULL,
  insuranceid VARCHAR(255) NOT NULL,
  status VARCHAR(127) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE healings
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(127) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE recipes
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  patient_id INT UNSIGNED NOT NULL,
  healing_id INT UNSIGNED NOT NULL,
  date_from DATE NOT NULL,
  date_to DATE NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE recipes_simple
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  patient_id INT UNSIGNED NOT NULL,
  healing_name VARCHAR(255) NOT NULL,
  healing_type VARCHAR(127) NOT NULL,
  healthful BOOL NOT NULL,
  date_from DATE NOT NULL,
  date_to DATE NOT NULL,
  doze VARCHAR(127) NOT NULL,
  day_names VARCHAR(255) NOT NULL,
  day_parts VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE recipes_day_names
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(127) NOT NULL,
  recipe_id INT UNSIGNED,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE recipes_day_parts
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  part VARCHAR(127) NOT NULL,
  time TIME,
  doze VARCHAR(127) NOT NULL,
  day_name_id INT UNSIGNED,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE events
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  recipe_id INT UNSIGNED NOT NULL,
  status VARCHAR(127) NOT NULL,
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
  REFERENCES roles(id);
#     ON UPDATE CASCADE
#     ON DELETE RESTRICT;
     
ALTER TABLE persons 
  ADD CONSTRAINT persons_accounts_fk 
  FOREIGN KEY (account_id) 
  REFERENCES accounts(id);

ALTER TABLE doctors
  ADD CONSTRAINT doctors_persons_fk
    FOREIGN KEY (id)
      REFERENCES persons(id);

ALTER TABLE patients
  ADD CONSTRAINT patients_persons_fk
    FOREIGN KEY (id)
      REFERENCES persons(id);


ALTER TABLE patients
  ADD CONSTRAINT patients_doctors_fk
    FOREIGN KEY (doctor_id)
      REFERENCES doctors(id);

ALTER TABLE recipes_simple
  ADD CONSTRAINT recipes_simple_patients_fk
    FOREIGN KEY (patient_id)
      REFERENCES patients(id);
  
ALTER TABLE recipes 
  ADD CONSTRAINT recipes_patients_fk 
  FOREIGN KEY (patient_id) 
  REFERENCES patients(id);

ALTER TABLE recipes_day_names
  ADD CONSTRAINT recipes_day_names_recipes_fk
    FOREIGN KEY (recipe_id)
      REFERENCES recipes(id);

ALTER TABLE recipes_day_parts
  ADD CONSTRAINT recipes_day_parts_names_fk
    FOREIGN KEY (day_name_id)
      REFERENCES recipes_day_names(id);
  
ALTER TABLE recipes 
  ADD CONSTRAINT recipes_healings_fk 
  FOREIGN KEY (healing_id) 
  REFERENCES healings(id);
  
ALTER TABLE events 
  ADD CONSTRAINT events_recipes_fk 
  FOREIGN KEY (recipe_id) 
  REFERENCES recipes(id);

# for tests ========================================
INSERT INTO roles (type) VALUES 
('ROOT'), ('ADMIN'), ('USER'), ('DOCTOR'), ('NURSE'), ('PATIENT');

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
(2, 4, 'dalnozorkost', '98723509', 'ILL'),
(3, 4, 'astigmatizm', '87635832', 'ILL');

INSERT INTO recipes_simple (patient_id, healing_name, healing_type, healthful, date_from, date_to, doze, day_names, day_parts) VALUES
(2, 'ochki', 'PROCEDURE', false, '2019-01-01', '2019-09-15', '3 time', 'MONDAY;TUESDAY;FRIDAY;', 'MORNING;EVENING;'),
(3, 'drug bitter', 'DRUG', false, '2019-06-25', '2019-08-11', '880 pills', 'SATURDAY;SUNDAY;', 'DAY;EVENING;NIGHT;');

INSERT INTO healings (name, type) VALUES
('ochki', 'PROCEDURE'),
('drug bitter', 'DRUG'),
('drug sweed', 'DRUG');

INSERT INTO recipes (patient_id, healing_id, date_from, date_to) VALUES
(2, 1, '2019-01-01', '2019-09-15'),
(3, 2, '2019-06-25', '2019-08-11');

INSERT INTO recipes_day_names (name, recipe_id) VALUES
('Monday', 1),
('Thursday', 1),
('Friday', 2),
('Saturday', 2),
('Sunday', 2);

INSERT INTO recipes_day_parts (part, time, doze, day_name_id) VALUES
('Morning', '083000', '0.5', 1),
('Day', '134500', '15', 1),
('Evening', '181000', '1', 2),
('Day', '134500', '15', 3),
('Evening', '180000', '3', 4),
('Evening', '190000', '85', 5),
('Night', '010000', '85', 5),
('Morning', '070000', '85', 5);
# ==================================================