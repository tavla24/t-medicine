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
(3, 'Wear black glasses', 'PROCEDURE', false, '2019-09-12', '2019-09-18', '10 minutes', 'MONDAY;TUESDAY;FRIDAY;', 'MORNING;EVENING;'),
(4, 'Exercises for eyes', 'PROCEDURE', false, '2019-09-13', '2019-09-25', '3 time for 5 mimutes', 'MONDAY;FRIDAY;', 'MORNING;'),
(5, 'Multivitamins', 'DRUG', false, '2019-09-15', '2019-09-29', '3 pills', 'SATURDAY;SUNDAY;', 'DAY;EVENING;NIGHT;'),
(5, 'Acupuncture', 'PROCEDURE', false, '2019-09-20', '2019-09-26', 'In all body points', 'MONDAY;WEDNESDAY;FRIDAY;SUNDAY;', 'MORNING;NIGHT;');

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