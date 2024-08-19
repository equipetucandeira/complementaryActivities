CREATE TABLE Courses (
  id CHAR(36) PRIMARY KEY,
  name VARCHAR(60) UNIQUE NOT NULL,
  workload TINYINT NOT NULL,
  active BOOLEAN DEFAULT TRUE
);

DELIMITER $$
CREATE PROCEDURE GetCourses()
BEGIN
  SELECT id, name, workload FROM Courses WHERE active = TRUE ORDER BY name;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetCourse(IN id CHAR(36))
BEGIN
  SELECT id, name, workload FROM Courses WHERE active = TRUE AND Courses.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE CreateCourse(IN id CHAR(36), IN name VARCHAR(60), IN workload TINYINT)
BEGIN
  INSERT INTO  Courses (id, name, workload) VALUES (id, name, workload);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE ActivateCourse(IN id CHAR(36))
BEGIN
  UPDATE Courses SET active = TRUE WHERE Courses.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE DeactivateCourse(IN id CHAR(36))
BEGIN
  UPDATE Courses SET active = FALSE WHERE Courses.id = id;
END $$
DELIMITER ;

CREATE TABLE Users (
  id CHAR(36) PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password BINARY(60) NOT NULL,
  role ENUM('STUDENT', 'SERVANT') NOT NULL,
  active BOOLEAN DEFAULT TRUE
);
 
DELIMITER $$
CREATE PROCEDURE GetStudents()
BEGIN
  SELECT Users.id, Users.name, Users.email, Users.password, Courses.id AS 'course_id', Courses.name AS 'course', Courses.workload FROM UsersCourses JOIN Users ON UsersCourses.user = Users.id JOIN Courses ON UsersCourses.course = Courses.id WHERE UsersCourses.active = TRUE AND Users.role = 'STUDENT';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE CreateStudent(IN id CHAR(36), IN name VARCHAR(60), IN email VARCHAR(255), IN password BINARY(60))
BEGIN
  INSERT INTO Users (id, name, email, password, role) VALUES (id, name, email, password, 'STUDENT');
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetServants()
BEGIN
  SELECT id, name, email, password FROM Users WHERE active = TRUE AND role = 'SERVANT';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE CreateServant(IN id CHAR(36), IN name VARCHAR(60), IN email VARCHAR(255), IN password BINARY(60))
BEGIN
  INSERT INTO Users (id, name, email, password, role) VALUES (id, name, email, password, 'SERVANT');
END $$
DELIMITER ;

CREATE TABLE UsersCourses (
  user CHAR(36),
  course CHAR(36),
  active BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (user) REFERENCES Users (id),
  FOREIGN KEY (course) REFERENCES Courses (id),
  PRIMARY KEY (user, course)
);

DELIMITER $$
CREATE PROCEDURE AddUserCourse(IN user CHAR(36), IN course CHAR(36))
BEGIN
  INSERT INTO UsersCourses (user, course) VALUES (user, course);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetStudentCourses(IN id CHAR(36))
BEGIN
  SELECT Users.name AS 'student', Courses.name AS 'course' FROM UsersCourses JOIN Users ON UsersCourses.user = Users.id JOIN Courses ON UsersCourses.course = Courses.id WHERE UsersCourses.active = TRUE AND UsersCourses.user = id;
END $$
DELIMITER ;

CREATE TABLE Categories (
  id CHAR(36) PRIMARY KEY,
  name VARCHAR(60) UNIQUE NOT NULL,
  description TEXT NOT NULL,
  submission_limit TINYINT NOT NULL,
  workload TINYINT NOT NULL,
  active BOOLEAN DEFAULT TRUE
);

DELIMITER $$
CREATE PROCEDURE GetCategories()
BEGIN
  SELECT id, name, description, submission_limit, workload FROM Categories WHERE active = TRUE;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetCategory(IN id CHAR(36))
BEGIN
  SELECT id, name, description, submission_limit, workload FROM Categories WHERE active = TRUE AND Categories.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SetCategory(IN id CHAR(36), IN name VARCHAR(60), IN description TEXT, IN submission_limit TINYINT, IN workload TINYINT)
BEGIN
  INSERT INTO Categories (id, name, description, submission_limit, workload) VALUES (id, name, description, submission_limit, workload);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE UpdateCategory(IN id CHAR(36), IN name VARCHAR(60), IN description TEXT, IN submission_limit TINYINT, IN workload TINYINT)
BEGIN
  UPDATE Categories SET Categories.name = name, Categories.description = description, Categories.submission_limit = submission_limit, Categories.workload = workload WHERE Categories.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE DeleteCategory(IN id CHAR(36))
BEGIN
  UPDATE Categories SET active = FALSE WHERE Categories.id = id;
END $$
DELIMITER ;

CREATE TABLE CoursesCategories (
  course CHAR(36) NOT NULL,
  category CHAR(36) NOT NULL,
  FOREIGN KEY (course) REFERENCES Courses (id),
  FOREIGN KEY (category) REFERENCES Categories (id),
  PRIMARY KEY(course, category)
);

CREATE TABLE Submissions (
  id CHAR(36) PRIMARY KEY,
  category CHAR(36) NOT NULL,
  student CHAR(36) NOT NULL,
  servant CHAR(36),
  name VARCHAR(255) NOT NULL,
  workload TINYINT NOT NULL,
  start DATE NOT NULL,
  end DATE NOT NULL,
  attached TEXT NOT NULL,
  active BOOLEAN DEFAULT TRUE,
  submitted_at DATE,
  expires_at DATE,
  analyzed_at DATE,
  approved BOOLEAN,
  curriculum_link BOOLEAN,
  state ENUM('WAITING', 'EXPIRED', 'ANALYZED') DEFAULT 'WAITING',
  commentary VARCHAR(60) DEFAULT 'Sem comentários.',
  FOREIGN KEY (category) REFERENCES Categories (id),
  FOREIGN KEY (student) REFERENCES Users (id),
  FOREIGN KEY (servant) REFERENCES Users (id)
);

DELIMITER $$
CREATE PROCEDURE GetSubmissions(IN student CHAR(36))
BEGIN
  SELECT * FROM Submissions WHERE active = TRUE AND Submissions.student = student;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetAllSubmissions()
BEGIN
  SELECT * FROM Submissions WHERE active = TRUE;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE Submit(IN id CHAR(36), IN category CHAR(36), IN student CHAR(36), IN name VARCHAR(255), IN workload TINYINT, IN start DATE, IN end DATE, IN curriculumn_link BOOLEAN, IN attached TEXT)
BEGIN
  INSERT INTO Submissions (id, category, student, name, workload, curriculum_link, attached, start, end, submitted_at, expires_at) VALUES (id, category, student, name, workload, curriculum_link, attached, start, end, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY));
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE Resubmit(IN id CHAR(36))
BEGIN
  UPDATE Submissions SET state = 'WAITING', expires_at = DATE_ADD(CURDATE(), INTERVAL 30 DAY) WHERE active = TRUE AND state = 'EXPIRED' AND Submissions.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE Approve(IN id CHAR (36))
BEGIN
  UPDATE Submissions SET state = 'ANALYZED', analyzed_at = CURDATE(), approved = TRUE WHERE active = TRUE AND state = 'WAITING' AND Submissions.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE Reprove(IN id CHAR(36), IN commentary VARCHAR(60))
BEGIN
  UPDATE Submissions SET state = 'ANALYZED', analyzed_at = CURDATE(), Submissions.commentary = commentary, approved = FALSE WHERE active = TRUE AND state = 'WAITING' AND Submissions.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetWaitings()
BEGIN
  SELECT id, category, student, workload, start, end,  submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'WAITING';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetWaiting(IN id CHAR(36))
BEGIN
  SELECT id, category, student, workload, start, end,   submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'WAITING' AND Submissions.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE EVENT CheckForExpiredSubmissions ON SCHEDULE EVERY 1 DAY DO
BEGIN
  UPDATE Submissions SET state = 'EXPIRED' WHERE expires_at = CURDATE() AND state != 'ANALYZED';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetExpireds()
BEGIN
  SELECT id, category, student, workload, start, end,   submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'EXPIRED';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetExpired(IN id CHAR(36))
BEGIN
  SELECT id, category, student, workload, start, end,   submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'EXPIRED' AND Submissions.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetAnalyzeds()
BEGIN
  SELECT id, approved, category, student, workload, start, end,   submitted_at, analyzed_at, attached FROM Submissions WHERE active = TRUE AND state = 'ANALYZED';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetAnalyzed(IN id CHAR(36))
BEGIN
  SELECT id, approved, category, student, workload, start, end,   submitted_at, analyzed_at, attached FROM Submissions WHERE active = TRUE AND state = 'ANALYZED' AND Submissions.id = id;
END $$
DELIMITER ;