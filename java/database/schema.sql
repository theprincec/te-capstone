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
        appointment_time time NOT NULL,
        
        CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
        CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patients(patient_id)    
);



INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;
