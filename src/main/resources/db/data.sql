-- ROLES
INSERT INTO roles(name) VALUES ('ADMIN');
INSERT INTO roles(name) VALUES ('TEACHER');
INSERT INTO roles(name) VALUES ('STUDENT');
INSERT INTO roles(name) VALUES ('MONITORING');

-- USERS
INSERT INTO users(username,password,email,first_name,last_name,role_id)
VALUES ('admin1','admin123','admin@school.com','System','Admin',1);

INSERT INTO users(username,password,email,first_name,last_name,role_id)
VALUES ('teacher1','teach123','teacher@school.com','John','Smith',2);

INSERT INTO users(username,password,email,first_name,last_name,role_id)
VALUES ('student1','stud123','student@school.com','Emma','Brown',3);

INSERT INTO users(username,password,email,first_name,last_name,role_id)
VALUES ('monitoring','monitoring','monitoring@school.com','Monitoring','Monitoring',4);

-- SUBJECTS
INSERT INTO subjects(name,description) VALUES ('მათემატიკა','მათემატიკა');
INSERT INTO subjects(name,description) VALUES ('ქართული ენა','ქართული ენა');
INSERT INTO subjects(name,description) VALUES ('ინგლისური ენა','ინგლისური ენა');
INSERT INTO subjects(name,description) VALUES ('რუსული ენა','რუსული ენა');
INSERT INTO subjects(name,description) VALUES ('ისტორია','ისტორია');
INSERT INTO subjects(name,description) VALUES ('გეოგრაფია','გეოგრაფია');
INSERT INTO subjects(name,description) VALUES ('ფიზიკა','ფიზიკა');
INSERT INTO subjects(name,description) VALUES ('ქიმია','ქიმია');
INSERT INTO subjects(name,description) VALUES ('ბიოლოგია','ბიოლოგია');

-- GROUPS
INSERT INTO student_groups(name,grade_level,academic_year)
VALUES ('Group A',10,'2025-2026');

-- STUDENT
INSERT INTO students(user_id,birth_date,address,parent_name,parent_contact,admission_date)
VALUES (3,'2008-05-12','City Center','Michael Brown','555123456','2025-09-01');

-- TEACHER
INSERT INTO teachers(user_id,hire_date,salary,qualification)
VALUES (2,'2024-01-10',2500.00,'MSc Mathematics');

-- RELATIONS
INSERT INTO group_students(group_id,student_id) VALUES (1,1);
INSERT INTO teacher_subjects(teacher_id,subject_id) VALUES (1,1);

-- CLASS
INSERT INTO classes(teacher_id,subject_id,group_id,class_date,start_time,end_time,topic,room_number)
VALUES (1,1,1,'2026-04-20', '14:00:00','15:00:00','Algebra Basics','101');

-- TIMETABLE
INSERT INTO timetables(group_id,subject_id,teacher_id,day_of_week,start_time,end_time,room_number)
VALUES (1,1,1,'MONDAY','09:00:00','10:00:00','101');

-- ASSIGNMENT
INSERT INTO assignments(teacher_id,group_id,subject_id,title,description,due_date,max_score)
VALUES (1,1,1,'Homework 1','Solve algebra exercises','2026-04-25',100);

-- EXAM
INSERT INTO exams(subject_id,group_id,title,exam_date,max_score)
VALUES (1,1,'Shualeduri 1','2026-05-10',100);

-- GRADE
INSERT INTO grades(student_id,assignment_id,grade_value,remarks)
VALUES (1,1,92,'Excellent');

-- FEE
INSERT INTO fees(student_id,amount,due_date,status)
VALUES (1,1200.00,'2026-05-01','PENDING');

-- PAYMENT
INSERT INTO payments(fee_id,payment_date,amount_paid,method)
VALUES (1,'2026-04-22',600.00,'BANK_TRANSFER');

-- SENSOR
INSERT INTO sensor
(id, name, type, location, unit, min_threshold, max_threshold)
VALUES
    (1,'Temperature Sensor','TEMPERATURE','Main Building','°C',18,28),
    (2,'Humidity Sensor','HUMIDITY','Main Building','%',30,60),
    (3,'Air Quality Sensor','AIR_QUALITY','Main Building','AQI',0,100),
    (4,'CO2 Sensor','CO2','Main Building','ppm',0,1000),
    (5,'Pressure Sensor','PRESSURE','Main Building','hPa',980,1050),
    (6,'Light Sensor','LIGHT','Main Building','lux',200,800),
    (7,'Noise Sensor','NOISE','Main Building','dB',0,70);

-- SENSOR_READING
INSERT INTO sensor_reading(sensor_id,reading_value,reading_time)
VALUES
    (1,31.2,CURRENT_TIMESTAMP),
    (2,42.0,CURRENT_TIMESTAMP),
    (3,120.0,CURRENT_TIMESTAMP),
    (4,1450.0,CURRENT_TIMESTAMP),
    (5,1012.0,CURRENT_TIMESTAMP),
    (6,350.0,CURRENT_TIMESTAMP),
    (7,82.0,CURRENT_TIMESTAMP);