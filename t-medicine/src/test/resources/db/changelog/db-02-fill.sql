--liquibase formatted sql
--changeset milaev:fill-test-table

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
