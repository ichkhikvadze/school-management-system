DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS fees;
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS exams;
DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS timetables;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS teacher_subjects;
DROP TABLE IF EXISTS group_students;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS student_groups;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- =========================
-- ROLES
-- =========================
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       role_id BIGINT NOT NULL,
                       active BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- =========================
-- SUBJECTS
-- =========================
CREATE TABLE subjects (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL UNIQUE,
                          description VARCHAR(255)
);

-- =========================
-- STUDENT GROUPS
-- =========================
CREATE TABLE student_groups (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(100) NOT NULL UNIQUE,
                                grade_level INT NOT NULL,
                                academic_year VARCHAR(20) NOT NULL
);

-- =========================
-- STUDENTS
-- =========================
CREATE TABLE students (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL UNIQUE,
                          enrollment_number VARCHAR(50) NOT NULL UNIQUE,
                          birth_date DATE,
                          address VARCHAR(255),
                          parent_name VARCHAR(150),
                          parent_contact VARCHAR(50),
                          admission_date DATE,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =========================
-- TEACHERS
-- =========================
CREATE TABLE teachers (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL UNIQUE,
                          hire_date DATE,
                          salary DECIMAL(10,2),
                          qualification VARCHAR(150),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =========================
-- GROUP-STUDENT RELATION
-- =========================
CREATE TABLE group_students (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                group_id BIGINT NOT NULL,
                                student_id BIGINT NOT NULL,
                                FOREIGN KEY (group_id) REFERENCES student_groups(id),
                                FOREIGN KEY (student_id) REFERENCES students(id),
                                UNIQUE(group_id, student_id)
);

-- =========================
-- TEACHER-SUBJECT RELATION
-- =========================
CREATE TABLE teacher_subjects (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  teacher_id BIGINT NOT NULL,
                                  subject_id BIGINT NOT NULL,
                                  FOREIGN KEY (teacher_id) REFERENCES teachers(id),
                                  FOREIGN KEY (subject_id) REFERENCES subjects(id),
                                  UNIQUE(teacher_id, subject_id)
);

-- =========================
-- CLASSES
-- =========================
CREATE TABLE classes (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         teacher_id BIGINT NOT NULL,
                         subject_id BIGINT NOT NULL,
                         group_id BIGINT NOT NULL,
                         class_date DATE NOT NULL,
                         topic VARCHAR(255),
                         room_number VARCHAR(20),
                         FOREIGN KEY (teacher_id) REFERENCES teachers(id),
                         FOREIGN KEY (subject_id) REFERENCES subjects(id),
                         FOREIGN KEY (group_id) REFERENCES student_groups(id)
);

-- =========================
-- TIMETABLES
-- =========================
CREATE TABLE timetables (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            group_id BIGINT NOT NULL,
                            subject_id BIGINT NOT NULL,
                            teacher_id BIGINT NOT NULL,
                            day_of_week VARCHAR(20) NOT NULL,
                            start_time TIME NOT NULL,
                            end_time TIME NOT NULL,
                            room_number VARCHAR(20),
                            FOREIGN KEY (group_id) REFERENCES student_groups(id),
                            FOREIGN KEY (subject_id) REFERENCES subjects(id),
                            FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

-- =========================
-- ASSIGNMENTS
-- =========================
CREATE TABLE assignments (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             class_id BIGINT NOT NULL,
                             title VARCHAR(150) NOT NULL,
                             description VARCHAR(500),
                             due_date DATE,
                             max_score INT DEFAULT 100,
                             FOREIGN KEY (class_id) REFERENCES classes(id)
);

-- =========================
-- EXAMS
-- =========================
CREATE TABLE exams (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       subject_id BIGINT NOT NULL,
                       group_id BIGINT NOT NULL,
                       exam_date DATE NOT NULL,
                       max_score INT DEFAULT 100,
                       FOREIGN KEY (subject_id) REFERENCES subjects(id),
                       FOREIGN KEY (group_id) REFERENCES student_groups(id)
);

-- =========================
-- GRADES
-- =========================
CREATE TABLE grades (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        student_id BIGINT NOT NULL,
                        assignment_id BIGINT,
                        exam_id BIGINT,
                        class_id BIGINT,
                        grade_value DECIMAL(5,2) NOT NULL,
                        remarks VARCHAR(255),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (student_id) REFERENCES students(id),
                        FOREIGN KEY (assignment_id) REFERENCES assignments(id),
                        FOREIGN KEY (exam_id) REFERENCES exams(id),
                        FOREIGN KEY (class_id) REFERENCES classes(id)
);

-- =========================
-- FEES
-- =========================
CREATE TABLE fees (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      student_id BIGINT NOT NULL,
                      amount DECIMAL(10,2) NOT NULL,
                      due_date DATE NOT NULL,
                      status VARCHAR(50) DEFAULT 'PENDING',
                      FOREIGN KEY (student_id) REFERENCES students(id)
);

-- =========================
-- PAYMENTS
-- =========================
CREATE TABLE payments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          fee_id BIGINT NOT NULL,
                          payment_date DATE NOT NULL,
                          amount_paid DECIMAL(10,2) NOT NULL,
                          method VARCHAR(50),
                          FOREIGN KEY (fee_id) REFERENCES fees(id)
);