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

CREATE TABLE articles (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  imgpath VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  text LONGTEXT NOT NULL,
  PRIMARY KEY (id)
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

INSERT INTO articles (imgpath, title, text) VALUES
('res/img/image01.jpeg', 'Psychiatric symptoms may indicate the beginning of Alzheimer’s disease', '<div class="texcon">
                    Researchers at UC San Francisco (UCSF) and the University of São Paulo have shown that the earliest
                    stages of Alzheimer’s brain pathology are tightly linked to neuropsychiatric symptoms such as
                    anxiety, depression and sleep disturbances.
                </div>
                <div class="texcon">
                    The finding strongly suggests that rather than such symptoms causing Alzheimer’s to develop, they
                    are in fact the earliest warning signs that the disease is already in progress.
                </div>
                <div class="texcon">
                    Study author Lea Grinberg says the discovery that the biological basis for these symptoms is the
                    early Alzheimer’s pathology itself was quite surprising: “It suggests these people with
                    neuropsychiatric symptoms are not at risk of developing Alzheimer’s disease – they already have it.”
                </div>
                <div class="texcon">
                    The authors say the findings could lead to earlier diagnosis of Alzheimer’s and to biomarkers that
                    could be used to develop drugs that would slow disease progression. They could also help researchers
                    better understand the biological basis of psychiatric symptoms in older individuals.
                </div>'),
('res/img/image02.jpeg', 'Identifying Biomarkers for Guillain–Barré Syndrome', '<div class="texcon">
                    Guillain-Barre syndrome is a rare autoimmune condition in which the immune system attacks peripheral
                    nerves and damages the myelin insulation. Symptoms rapidly progress from pain to muscle weakness and
                    even paralysis. Although Guillain-Barre usually affects the hands, feet, and limbs, it can be
                    life-threatening when nerves controlling breathing are affected.
                </div>
                <div class="texcon">
                    Guillain-Barre syndrome has been clinically sub-classified into several variants1, but specific
                    biomarkers for each of the subtypes have not been well defined. Furthermore, there are several other
                    conditions that can cause similar symptoms, such as inflammatory demyelination disorders of the
                    central nervous system (IDDs).
                </div>
                <div class="texcon">
                    Consequently, Guillain-Barre syndrome can be difficult to diagnose, especially in younger patients2.
                    This can be seriously detrimental to patient outcomes since early effective treatment is critical to
                    preventing rapid progression to a severe clinical course.
                </div>
                <div class="texcon">
                    Greater understanding of the underlying pathology of Guillain-Barre syndrome is thus needed to
                    facilitate its accurate diagnosis and improve patient prognoses3.
                </div>'),
('res/img/image03.jpeg', 'What is the Role of Vascular Endothelial Growth Factor?', '<div class="texcon">
                    Formation of new blood vessels can be divided into two processes – vasculogenesis and angiogenesis.
                    Vasculogenesis represents the formation of new blood vessels from hemangioblasts during embryonic
                    development, while angiogenesis is the formation of new capillary branches from established blood
                    vessels.
                </div>
                <div class="texcon">
                    Vascular Endothelial Growth Factor, abbreviated as VEGF, represents a protein signaling molecule
                    which plays a crucial role in both vasculogenesis and angiogenesis.

                    There are five different types of VEGF - VEGF-A, VEGF-B, VEGF-C, VEGF-D, and placental growth factor
                    (PlGF).
                </div>
                <div class="texcon">
                    VEGF-A, sometimes referred to as VEGF, was discovered in the 1980s by various groups of researchers.
                    VEGF-A is a single gene, but it is one of the VEGF proteins which exists in sub-types; this occurs
                    due to the way its messenger RNA (mRNA) is processed, and in the case of VEGF-A this gives rise to
                    nine sub-types.
                </div>
                <div class="texcon">
                    This protein is a “mitogen”, necessary for the survival of vascular endothelial cells. VEGF-A,
                    expressed in many organs and tissues, also allows plasma and proteins contained within the plasma to
                    pass through the endothelial layer of blood vessels without damaging the vessel. Mice lacking VEGF-A
                    do not survive, and exhibit abnormal vasculature; therefore, it is likely that VEGF-A is essential
                    for the formation of blood vessels at early stages of embryonic development.
                </div>');

# ==================================================