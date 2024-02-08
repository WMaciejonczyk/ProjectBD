DROP DATABASE IF EXISTS medical_equipment;

CREATE DATABASE medical_equipment;

USE medical_equipment;

CREATE TABLE info(
eq_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
eq_name VARCHAR(255) NOT NULL,
eq_type ENUM('ECG', 'EMG', 'RTG', 'MRI', 'OTHER'),
production_date DATE NOT NULL,
last_service_date DATE NOT NULL,
eq_status ENUM('FREE', 'OCCUPIED') NOT NULL,
service_validity_period SMALLINT NOT NULL
);

CREATE TABLE service_archives(
service_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
service_date DATE NOT NULL,
equipment_id INT NOT NULL,
technician VARCHAR(255) NOT NULL,
description TEXT NOT NULL,
FOREIGN KEY (equipment_id) REFERENCES info(eq_id)
);

CREATE TABLE reservations(
reservation_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
start_date DATE NOT NULL,
end_date DATE NOT NULL,
equipment_id INT NOT NULL,
reserver VARCHAR(255) NOT NULL,
FOREIGN KEY (equipment_id) REFERENCES info(eq_id)
);

CREATE TABLE users(
user_login VARCHAR(30) PRIMARY KEY NOT NULL,
user_password VARCHAR(30) NOT NULL,
department ENUM('DOCTOR', 'TECHNICIAN', 'ADMIN') NOT NULL
);

INSERT INTO users(user_login, user_password, department)
 VALUES('project_admin', '1234', 'ADMIN');

CREATE VIEW technician_view AS
SELECT info.eq_name, info.eq_type, DATE_ADD(info.last_service_date, INTERVAL info.service_validity_period DAY) AS next_service_date
FROM info;

INSERT INTO reservations(start_date, end_date, equipment_id, reserver)
VALUES('2024-02-02', '2024-02-10', 1, 'John Doe');

INSERT INTO reservations(start_date, end_date, equipment_id, reserver)
VALUES('2024-02-15', '2024-02-20', 2, 'Jane Doe');

CREATE VIEW doctor_view AS
SELECT eq_id, eq_name, eq_type
FROM info;
