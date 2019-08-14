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

ALTER TABLE events 
  ADD CONSTRAINT events_recipes_simple_fk
  FOREIGN KEY (recipe_id) 
  REFERENCES recipes_simple(id);

# for tests ========================================
INSERT INTO roles (type) VALUES 
('ADMIN'), ('USER'), ('DOCTOR'), ('NURSE'), ('PATIENT');

INSERT INTO accounts (login, password, role_id) VALUES 
('admin', 'PW_2222', 1),
('user', 'PW_3333', 2),
('doctor', 'PW_4444', 3),
('nurse', 'PW_5555', 4),
('patient', 'PW_6666', 5);

INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Ivanov', 'Sergey', 'Aleksandrovich', 'ivanov@mail.ru', '+79214737482', 1);
INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Petrov', 'Arseniy', 'Konstantinovich', 'petrov@mail.ru', '+79219373549', 4);
INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Sidorov', 'Konstantin', 'Victorovich', 'sidorov@mail.ru', '+79219823575', 5);

INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Bochkareva', 'Ekaterina', 'Sergeevna', 'bochkareva@mail.ru', '+79218365208', 3);

INSERT INTO doctors (id, specialization) VALUES
(4, 'okulist');

INSERT INTO patients (id, doctor_id, diagnosis, insuranceid, status) VALUES
(2, 4, 'dalnozorkost', '98723509', 'ILL'),
(3, 4, 'astigmatizm', '87635832', 'ILL');

INSERT INTO recipes_simple (patient_id, healing_name, healing_type, healthful, date_from, date_to, doze, day_names, day_parts) VALUES
#(2, 'ochki', 'PROCEDURE', false, STR_TO_DATE('01/02/2019', '%m/%d/%Y'), STR_TO_DATE('05/06/2019', '%m/%d/%Y'), '3 time', 'MONDAY;TUESDAY;FRIDAY;', 'MORNING;EVENING;'),
#(3, 'drug bitter', 'DRUG', false, STR_TO_DATE('01/02/2018', '%m/%d/%Y'), STR_TO_DATE('05/06/2018', '%m/%d/%Y'), '880 pills', 'SATURDAY;SUNDAY;', 'DAY;EVENING;NIGHT;');
(2, 'ochki', 'PROCEDURE', false, '2019-08-12', '2019-08-30', '3 time', 'MONDAY;TUESDAY;FRIDAY;', 'MORNING;EVENING;'),
(3, 'drug bitter', 'DRUG', false, '2019-08-14', '2019-08-25', '880 pills', 'SATURDAY;SUNDAY;', 'DAY;EVENING;NIGHT;');

INSERT INTO healings (name, type) VALUES
('ochki', 'PROCEDURE'),
('drug bitter', 'DRUG'),
('drug sweed', 'DRUG');

# ==================================================