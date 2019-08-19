DROP DATABASE IF EXISTS hospital;
CREATE DATABASE hospital;
USE hospital;

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
  email VARCHAR(255),
  phone VARCHAR(255),
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
('ADMIN'), ('DOCTOR'), ('NURSE');

INSERT INTO accounts (login, password, role_id) VALUES 
('admin', 'PW_2222', 1),
('ivanov', 'PW_4444', 2),
('petrov', 'PW_4444', 2),
('sidorov', 'PW_5555', 3);

INSERT INTO persons (name, surname, patronymic, email, phone, account_id) VALUES 
('Sergey', 'Ivanov', 'Aleksandrovich', 'ivanov@mail.ru', '7-921-5486257', 2),
('Arseniy', 'Petrov', 'Konstantinovich', 'petrov@mail.ru', '7-921-2548763', 3),
('Konstantin', 'Sidorov', 'Victorovich', 'sidorov@mail.ru', '7-911-4587625', null),
('Ekaterina', 'Bochkareva', 'Sergeevna', 'bochkareva@mail.ru', '7-968-6145752', null),
('Natalia', 'Chernova', 'Igorevna', 'chern@mail.ru', '7-963-1354628', null);

INSERT INTO doctors (id, specialization) VALUES
(1, 'Ophthalmologist'),
(2, 'Therapist');

INSERT INTO patients (id, doctor_id, diagnosis, insuranceid, status) VALUES
(3, 1, 'Astigmatism 22dgr', 'FGR1458762', 'ILL'),
(4, 1, 'Hyperopia LE+4, RE+3.5', 'HFT4245876', 'ILL'),
(5, 2, 'Loss of muscle tone', 'KDE2458762', 'ILL');


INSERT INTO recipes_simple (patient_id, healing_name, healing_type, healthful, date_from, date_to, doze, day_names, day_parts) VALUES
(3, 'Wear black glasses', 'PROCEDURE', false, '2019-08-21', '2019-08-27', '10 minutes', 'MONDAY;TUESDAY;FRIDAY;', 'MORNING;EVENING;'),
(4, 'Exercises for eyes', 'PROCEDURE', false, '2019-08-24', '2019-08-30', '3 time for 5 mimutes', 'MONDAY;FRIDAY;', 'MORNING;'),
(5, 'Multivitamins', 'DRUG', false, '2019-08-23', '2019-08-29', '3 pills', 'SATURDAY;SUNDAY;', 'DAY;EVENING;NIGHT;'),
(5, 'Acupuncture', 'PROCEDURE', false, '2019-08-19', '2019-08-26', 'In all body points', 'MONDAY;WEDNESDAY;FRIDAY;SUNDAY;', 'MORNING;NIGHT;');

# ==================================================