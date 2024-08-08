CREATE DATABASE Complementary_Activities;

USE Complementary_Activities;


DELIMITER //
CREATE PROCEDURE CreateCategory(IN name VARCHAR(255), IN maximumLoad TINYINT)
BEGIN
  INSERT INTO Category (name, maximumLoad) VALUES (name, maximumLoad)
END
DELIMITER ;


DELIMITER //
CREATE PROCEDURE CreateEvaluator(IN name VARCHAR(255), IN email VARCHAR(255))
BEGIN
	INSERT INTO Evaluator (name, email) VALUES (name, email)
END
DELIMITER;

DELIMITER //

CREATE PROCEDURE CreateActivity(IN category BIGINT, IN name VARCHAR(255), IN maximumLoad TINYINT, IN observation VARCHAR(255), IN documentType VARCHAR(255), IN active BOOLEAN)
BEGIN
	INSERT INTO Activity(
		category,
        name,
        maximumLoad,
        observation,
        documentType,
        active,
        createdAt,
        updatedAt
    )
    VALUES (
		category,
        name,
        maximumLoad,
        observation,
        documentType,
        active,
        createdAt,
        updatedAt
    );
END //

DELIMITER ;


DELIMITER //
CREATE PROCEDURE CreateRegistration(IN course BIGINT, IN active BOOLEAN)
BEGIN
	INSERT INTO Registration(course, active) VALUES (course, active);
END //

DELIMITER ;


DELIMITER //
CREATE PROCEDURE CreateEvaluatorCourse(IN submitted BIGINT, IN evaluator BIGINT)
BEGIN
	INSERT INTO activity_evaluator(
		submitted,
        evaluator
    )VALUES(
		submitted,
        evaluator
    );
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE CreateActivityCourse(IN course BIGINT, IN activity BIGINT)
BEGIN
	INSERT INTO CourseActivity(
		course,
        activity
    )VALUES(
		course,
        activity
    );
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE CreateStudentRegistration(IN student BIGINT, IN registration BIGINT)
BEGIN
    INSERT INTO student_registration (student, registration)
    VALUES (student, registration);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE CreateStudent(IN name VARCHAR(255), IN email VARCHAR(255))
BEGIN
    INSERT INTO Student (name, email)
    VALUES (name, email);
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE ReadActiveCategories()
BEGIN
  SELECT id, name, maximumLoad FROM Category WHERE active = TRUE SORT BY name
END 
DELIMITER ;


DELIMITER //
CREATE PROCEDURE ReadStudentRegistration()
BEGIN
	SELECT
		sr.student,
        sr.registration,
        s.name,
        r.course,
        c.name
	FROM
		student_registration sr
	JOIN 
		Registration r ON sr.registration = r.id
	JOIN
		Course c ON r.course = c.id;
END //
DELIMITER ;
		

DELIMITER //
CREATE PROCEDURE ReadSubmittedDetailsFull()
BEGIN
	SELECT 
		s.id,
        s.activity,
        a.name AS activityName,
        s.status, 
        s.registration,
        r.course,
        r.active,
        s.submittedAt, 
        s.expiresAt, 
        s.attempts,
        s.attached,
        s.workload,
        s.observation,
        s.comentary,
        s.curriculumLink,
        s.updateAt
	FROM 
		Submitted s
	JOIN
		Activity a on s.activity = a.id
	JOIN
		Registration r ON s.registration = r.id;
END //
DELIMITER;

DELIMITER //

CREATE PROCEDURE ReadSubmittedDetailsSimple()
BEGIN
	SELECT
		s.id,
        s.activity,
        s.registration,
        s.status,
        s.attached,
        s.workload,
        s.submittedAt
	FROM
		Submitted s;
END //

DELIMITER;

DELIMITER //

CREATE PROCEDURE ReadCourseDetailsSimple()
BEGIN
	SELECT
		id,
        name,
        additionalHours,
        active
	FROM
		Course;
END //

DELIMITER;

DELIMITER //
CREATE PROCEDURE ReadActivityDetailsSimple()
BEGIN
	SELECT
		a.id,
        a.category,
        c.name,
        a.name,
        a.maximumLoad,
        a.observation,
        a.documentType
	FROM
		Activity a
        
	JOIN Category c on a.category = c.id;

END //

DELIMITER;

DELIMITER //
CREATE PROCEDURE ReadStudent()
BEGIN
	SELECT id, name, email FROM Student;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE ReadEvaluator()
BEGIN
	SELECT id, name, email FROM Evaluator;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE ReadActivityEvaluatorDetails()
BEGIN
    SELECT 
        ae.submitted,
        ae.evaluator,
        s.activity,
        s.registration,
        s.status,
        s.attached,
        s.workload,
        s.observation,
        s.commentary,
        s.curriculumLink,
        s.attempts,
        s.submitedAt,
        s.expiresAt,
        s.updatedAt,
        e.name AS evaluatorName 
    FROM 
        activity_evaluator ae
    JOIN 
        Submitted s ON ae.submitted = s.id
    JOIN 
        Evaluator e ON ae.evaluator = e.id;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE ReadCourseActivityDetails()
BEGIN
	SELECT
		ca.course,
        c.name AS courseName,
        ca.activity,
        a.name AS activityName,
        a.maximumLoad,
        a.observation,
        a.documentType,
        a.active,
        a.createdAt AS activityCreatedAt,
        a.updatedAt AS activityUpdatedAt
    FROM 
        CourseActivity ca
    JOIN 
        Course c ON ca.course = c.id
    JOIN 
        Activity a ON ca.activity = a.id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE UpdateActivity(IN id BIGINT, IN category BIGINT, IN name VARCHAR(255), IN maximumLoad TINYINT, IN observation VARCHAR(255), IN documentType VARCHAR(255), IN active BOOLEAN)
BEGIN
    UPDATE Activity
    SET 
        category = category,
        name = name,
        maximumLoad = maximumLoad,
        observation = observation,
        documentType = documentType,
        active = active,
        updatedAt = CURRENT_TIMESTAMP
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateCourse(IN id BIGINT, IN name VARCHAR(255), IN additionalHours TINYINT, IN active BOOLEAN)
BEGIN
    UPDATE Course
    SET 
        name = name,
        additionalHours = additionalHours,
        active = active,
        updatedAt = CURRENT_TIMESTAMP
    WHERE id = id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE UpdateEvaluator(IN id BIGINT, IN name VARCHAR(255), IN email VARCHAR(255))
BEGIN
    UPDATE Evaluator
    SET 
        name = name,
        email = email
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE UpdateRegistration(IN id BIGINT, IN course BIGINT, IN active BOOLEAN)
BEGIN
    UPDATE Registration
    SET 
        course = course,
        active = active
    WHERE id = id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE UpdateStudent(IN id BIGINT, IN name VARCHAR(255), IN email VARCHAR(255))
BEGIN
    UPDATE Student
    SET 
        name = name,
        email = email
    WHERE id = id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE UpdateSubmitted(IN id BIGINT, IN activity BIGINT, IN registration BIGINT, IN status ENUM('DRAFT', 'WAITING', 'EXPIRED', 'APPROVED', 'REPROVED'),
    IN attached VARCHAR(255),
    IN workload TINYINT,
    IN observation VARCHAR(255),
    IN commentary VARCHAR(255),
    IN curriculumLink BOOLEAN,
    IN attempts TINYINT,
    IN expiresAt TIMESTAMP
)
BEGIN
    UPDATE Submitted
    SET 
        activity = activity,
        registration = registration,
        status = status,
        attached = attached,
        workload = workload,
        observation = observation,
        commentary = commentary,
        curriculumLink = curriculumLink,
        attempts = attempts,
        expiresAt = expiresAt,
        updatedAt = CURRENT_TIMESTAMP
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteActivity(IN id BIGINT)
BEGIN
    DELETE FROM Activity
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteCourse(IN id BIGINT)
BEGIN
    DELETE FROM Course
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteEvaluator(IN id BIGINT)
BEGIN
    DELETE FROM Evaluator
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteRegistration(IN id BIGINT)
BEGIN
    DELETE FROM Registration
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteStudent(IN id BIGINT)
BEGIN
    DELETE FROM Student
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteSubmitted(IN id BIGINT)
BEGIN
    DELETE FROM Submitted
    WHERE id = id;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteCourseActivity(IN course BIGINT, IN activity BIGINT)
BEGIN
	DELETE FROM CourseActivity WHERE course = course AND activity = activity;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteCourseActivityEvaluator(IN course BIGINT, IN evaluator BIGINT)
BEGIN
    DELETE FROM CourseActivityEvaluator WHERE course = course AND evaluator = evaluator;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteStudentRegistration(IN student BIGINT, IN registration BIGINT)
BEGIN
    DELETE FROM student_registration WHERE student = student AND registration = registration;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteActivityEvaluator(IN submitted BIGINT, IN evaluator BIGINT)
BEGIN
    DELETE FROM activity_evaluator WHERE submitted = submitted AND evaluator = evaluator;
END //

DELIMITER ;

CREATE TABLE Category (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL,
  maximumLoad TINYINT NOT NULL,
  active BOOLEAN DEFAULT TRUE,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Activity (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  category BIGINT NOT NULL,
  name VARCHAR(255) UNIQUE NOT NULL,
  maximumLoad TINYINT NOT NULL,
  observation VARCHAR(255),
  documentType VARCHAR(255),
  active BOOLEAN DEFAULT TRUE,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category) REFERENCES Category (id)
);

CREATE TABLE Course (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL,
  additionalHours TINYINT NOT NULL,
  active BOOLEAN DEFAULT TRUE,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE CourseActivity (
  course BIGINT,
  activity BIGINT,
  FOREIGN KEY(course) REFERENCES Category (id),
  FOREIGN KEY(activity) REFERENCES Activity (id),
  PRIMARY KEY(course, activity)
);

CREATE TABLE Evaluator (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE CourseActivityEvaluator (
  course BIGINT,
  evaluator BIGINT,
  FOREIGN KEY (course) REFERENCES Course (id),
  FOREIGN KEY (evaluator) REFERENCES Evaluator (id),
  PRIMARY KEY (course, evaluator)
);

CREATE TABLE Registration (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  course BIGINT NOT NULL,
  active BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (course) REFERENCES Course (id)
);

CREATE TABLE Student (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);

CREATE TABLE student_registration (
  student BIGINT,
  registration BIGINT,
  FOREIGN KEY (student) REFERENCES Course (id),
  FOREIGN KEY (registration) REFERENCES Registration (id),
  PRIMARY KEY (student, registration)
);

CREATE TABLE Submitted (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  activity BIGINT,
  registration BIGINT,
  status ENUM('DRAFT', 'WAITING', 'EXPIRED', 'APPROVED', 'REPROVED') DEFAULT 'DRAFT',
  attached VARCHAR(255) NOT NULL,
  workload TINYINT NOT NULL,
  observation VARCHAR(255) DEFAULT 'Sem observações',
  commentary VARCHAR(255) NOT NULL,
  curriculumLink BOOLEAN NOT NULL,
  attempts TINYINT DEFAULT 0,
  submitedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  expiresAt TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (activity) REFERENCES Activity (id),
  FOREIGN KEY (registration) REFERENCES Registration (id)
);

DELIMITER //

CREATE TRIGGER set_expiresAt
BEFORE INSERT ON Activity
FOR EACH ROW
BEGIN
  SET NEW.expiresAt = DATE_ADD(NEW.submittedAt, INTERVAL 30 DAY);
END //

DELIMITER ;


CREATE TABLE activity_evaluator (
  submitted BIGINT,
  evaluator BIGINT,
  FOREIGN KEY (submitted) REFERENCES Submitted (id),
  FOREIGN KEY (evaluator) REFERENCES Evaluator (id),
  PRIMARY KEY (submitted, evaluator)
);
