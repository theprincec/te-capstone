START TRANSACTION;

INSERT INTO appointments (appointment_id, doctor_id, appointment_date, appointment_time_start, appointment_time_end, appointment_status) VALUES (DEFAULT,?, ?, ?, ?, 'personal');
