BEGIN TRANSACTION;

DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS offices;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE offices (
       office_id serial primary key, 
       office_name varchar(50) NOT NULL,
       address varchar(100) NOT NULL,
       city varchar(50) NOT NULL,
       district varchar(50),
       postal_code varchar(10) NOT NULL,
       phone varchar(10) NOT NULL, 
       open_time time NOT NULL,
       close_time time NOT NULL,
       hourly_rate numeric NOT NULL

);

CREATE TABLE doctors (
        doctor_id serial primary key, 
        user_id int,
        first_name varchar(50) NOT NULL,
        last_name varchar(50) NOT NULL,
        office_id int,
        
        CONSTRAINT fk_office_id FOREIGN KEY (office_id) REFERENCES offices(office_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
    
);

CREATE TABLE patients (
        patient_id serial primary key,
        user_id int,
        first_name varchar(50) NOT NULL,
        last_name varchar(50) NOT NULL, 
        
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE appointments (
        appointment_id serial primary key, 
        doctor_id int,
        patient_id int,
        appointment_date date NOT NULL, 
        appointment_time_start time NOT NULL,
        appointment_time_end time NOT NULL,
        appointment_status varchar(25),
        
        CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
        --CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patients(patient_id)    
);

INSERT INTO users (username,password_hash,role, email) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'admin2@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'testuser1@test.com');

--doctor
INSERT INTO users (username,password_hash,role, email) VALUES ('tiffany','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR', 'testdoctor3@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('jamesbrown','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR', 'testdoctor4@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('lola','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR', 'testdoctor5@test.com');
INSERT INTO users (username, password_hash,role, email) VALUES ('kevin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR', 'testdoctor6@test.com');

--patient 
INSERT INTO users (username,password_hash,role, email) VALUES ('brian','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'testpatient1@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('matt','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'testpatient2@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('jordan','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'testpatient3@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('olga','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'testpatient4@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('christian','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'testpatient5@test.com');

--more doctors
INSERT INTO users (username,password_hash,role, email) VALUES ('ty','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR', 'testdoctor7@test.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('aline','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR', 'testdoctor8@test.com');



INSERT INTO offices (office_id, office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate) 
VALUES (DEFAULT, 'TreeCare', '591 E Livingston Ave', 'Columbus', 'OH', '43215', '6143574454', '09:00:00', '17:00:00', 25);
INSERT INTO offices (office_id, office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate) 
VALUES (DEFAULT, 'ParrottCare', '1495 W 5th Ave', 'Columbus', 'OH', '43212', '6147856948', '09:00:00', '17:00:00', 30);
INSERT INTO offices (office_id, office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate) 
VALUES (DEFAULT, 'AardvarkCare', '3100 Cleveland Ave', 'Columbus', 'OH', '43224', '6142568969', '09:00:00', '18:00:00', 50);

INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES(DEFAULT, 4, 'James', 'Brown', 1);
INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES(DEFAULT, 3, 'Tiffany', 'Blue', 2 );
INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES(DEFAULT, 5, 'Lola', 'Balinsky', 1 );
INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES(DEFAULT, 6, 'Kevin', 'Docs', null );


INSERT INTO patients (patient_id, user_id, first_name, last_name) VALUES (DEFAULT, 7, 'Brian', 'Lauvray');
INSERT INTO patients (patient_id, user_id, first_name, last_name) VALUES (DEFAULT, 8, 'Matt', 'Eland');
INSERT INTO patients (patient_id, user_id, first_name, last_name) VALUES (DEFAULT, 9, 'Jordan', 'Rutland');
INSERT INTO patients (patient_id, user_id, first_name, last_name) VALUES (DEFAULT, 10, 'Olga', 'Melnichenko');
INSERT INTO patients (patient_id, user_id, first_name, last_name) VALUES (DEFAULT, 11, 'Christian', 'Cumberland');

INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES(DEFAULT, 13, 'Aline', 'Giroux', 1 );
INSERT INTO doctors (doctor_id, user_id, first_name, last_name, office_id) VALUES(DEFAULT, 12, 'Ty', 'Kish', 2 );

INSERT INTO patients (patient_id, user_id, first_name, last_name) VALUES (DEFAULT, 2, 'Anthony', 'James');



INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 1, 1, '2021-05-21', '11:00:00', '12:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 2, 1, '2021-05-21', '13:00:00', '14:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 2, 1, '2021-05-21', '10:00:00', '11:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 2, 1, '2021-05-22', '13:00:00', '14:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 2, 1, '2021-05-21', '11:00:00', '12:00:00', 'appointment');

INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 1, 2, '2021-05-21', '11:00:00', '12:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 3, 3, '2021-05-21', '13:00:00', '14:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 3, 4, '2021-05-21', '10:00:00', '11:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 4, 5, '2021-05-22', '13:00:00', '14:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 3, 3, '2021-05-21', '11:00:00', '12:00:00', 'appointment');

INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 1, 2, '2021-05-20', '10:00:00', '11:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 3, 3, '2021-05-20', '09:00:00', '10:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 3, 4, '2021-05-20', '11:00:00', '12:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 4, 5, '2021-05-24', '13:00:00', '14:00:00', 'appointment');
INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT, 3, 3, '2021-05-20', '14:00:00', '15:00:00', 'appointment');


COMMIT TRANSACTION;




