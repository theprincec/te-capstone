START TRANSACTION;

INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES (DEFAULT, 1000, 'James', 'Brown', 1000);


SELECT * FROM doctors;
