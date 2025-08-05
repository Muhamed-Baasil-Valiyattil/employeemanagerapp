
CREATE TABLE employee(
id INT PRIMARY KEY,
first_name VARCHAR(25),
last_name VARCHAR(25),
mobile_number VARCHAR(10) UNIQUE,
email VARCHAR(64) UNIQUE,
joining_date DATE,
active_status BOOLEAN
);

INSERT INTO employee VALUES 
(1,'alex','Antony','9090909090','abc@gmail.com','2022-01-01',TRUE),
(2,'Joseph','ural','9191919191','def@gmail.com','2025-02-21',TRUE),
(3,'Malik','Shah','9192939495','jkl@gmail.com','2024-12-11',FALSE),
(4,'Ram','Singh','9090909093','mnop@yahoo.com','2023-12-01',TRUE),
(5,'Amal','Khan','9191919192','arsa@outlook.com','2022-03-21',FALSE),
(6,'Muhamed','lais','9190299292','jdhd@gmail.com','2025-01-25',TRUE);

SELECT * FROM employee;

ALTER TABLE employee 
ADD CONSTRAINT chk_email_format 
CHECK (email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$');

ALTER TABLE employee 
MODIFY COLUMN mobile_number CHAR(10);

DESC employee;

SELECT 
    CONSTRAINT_NAME,
    CONSTRAINT_TYPE,
    TABLE_NAME
FROM 
    INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE 
    TABLE_SCHEMA = 'empdb'
    AND TABLE_NAME = 'employee';