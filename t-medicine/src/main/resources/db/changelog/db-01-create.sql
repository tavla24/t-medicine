--liquibase formatted sql
--changeset milaev:create-test-table

--DROP DATABASE IF EXISTS hospital;
CREATE SCHEMA IF NOT EXISTS hospital;
USE hospital;

--DROP TABLE IF EXISTS persistent_logins;
--DROP TABLE IF EXISTS accounts;
--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS roles;
--DROP TABLE IF EXISTS doctors;
--DROP TABLE IF EXISTS patients;
--DROP TABLE IF EXISTS recipes;
--DROP TABLE IF EXISTS recipes_day_parts;
--DROP TABLE IF EXISTS recipes_day_names;
--DROP TABLE IF EXISTS healings;
--DROP TABLE IF EXISTS events;

CREATE TABLE accounts
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(127) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  type VARCHAR(31) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE persons
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  patronymic VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(255),
  account_id INT UNSIGNED UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE doctors
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  specialization VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE patients
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  doctor_id INT UNSIGNED NOT NULL,
  diagnosis VARCHAR(255) NOT NULL,
  insuranceid VARCHAR(255) NOT NULL,
  status VARCHAR(127) NOT NULL,
  PRIMARY KEY (id)
);

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
);

CREATE TABLE events
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  recipe_id INT UNSIGNED NOT NULL,
  status VARCHAR(127) NOT NULL,
  date TIMESTAMP NOT NULL,
  info VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE persistent_logins (
  series VARCHAR(64) NOT NULL,
  username VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL,
  PRIMARY KEY (series)
);

CREATE TABLE articles (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  imgpath VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  text LONGTEXT NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE accounts
  ADD CONSTRAINT accounts_roles_fk
  FOREIGN KEY (role_id)
  REFERENCES roles(id);
--     ON UPDATE CASCADE
--     ON DELETE RESTRICT;

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