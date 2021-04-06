START TRANSACTION;

INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES (DEFAULT, 1000, 'James', 'Brown', 1000);

UPDATE doctors SET office_id = 1 WHERE doctor_id = 1;

START TRANSACTION;
ROLLBACK;
INSERT INTO offices (office_id, office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate) 
VALUES (DEFAULT, 'GreenAlley', '123 Test street', 'TestCity', 'OH', '43026', '1232563696', '9:00:00', '5:00:00', 50);
COMMIT;

INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) 
VALUES (DEFAULT, 3, 'Tiffany', 'Williams', 2);

SELECT doctor_id, first_name, last_name, doctors.office_id as office_id,
 office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate 
FROM doctors 
JOIN offices ON doctors.office_id = offices.office_id;

SELECT doctor_id, first_name, last_name, doctors.office_id as office_id, office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate
FROM doctors
JOIN offices ON doctors.office_id = offices.office_id
JOIN users ON users.user_id = doctors.user_id 
WHERE username = ?;
SELECT * FROM doctors;
