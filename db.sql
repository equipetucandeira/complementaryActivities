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
CREATE PROCEDURE ActivateUserCourse(IN user CHAR(36), IN course CHAR(36))
BEGIN
  UPDATE UsersCourses SET active = TRUE WHERE UsersCourses.user = user AND UsersCourses.course = course;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE DeactivateUserCourse(IN user CHAR(36), IN course CHAR(36))
BEGIN
  UPDATE UsersCourses SET active = FALSE WHERE UsersCourses.user = user AND UsersCourses.course = course;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetStudentCourses(IN id CHAR(36))
BEGIN
  SELECT Users.name AS 'student', Courses.name AS 'course' FROM UsersCourses JOIN Users ON UsersCourses.user = Users.id JOIN Courses ON UsersCourses.course = Courses.id WHERE UsersCourses.active = TRUE AND UsersCourses.user = id;
END $$
DELIMITER ;

CREATE TABLE Activities (
  id CHAR(36) PRIMARY KEY,
  name VARCHAR(60) UNIQUE NOT NULL,
  description VARCHAR(80) NOT NULL,
  submission_limit TINYINT NOT NULL,
  workload TINYINT NOT NULL,
  active BOOLEAN DEFAULT TRUE
);

DELIMITER $$
CREATE PROCEDURE GetActivities()
BEGIN
  SELECT id, name, description, submission_limit, workload FROM Activities WHERE active = TRUE;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetActivity(IN id CHAR(36))
BEGIN
  SELECT id, name, description, submission_limit, workload FROM Activities WHERE active = TRUE AND Activities.id = id;
END $$
DELIMITER ;

CREATE TABLE Submissions (
  id CHAR(36) PRIMARY KEY,
  activity CHAR(36) NOT NULL,
  student CHAR(36) NOT NULL,
  workload TINYINT NOT NULL,
  start DATE NOT NULL,
  end DATE NOT NULL,
  attached CHAR(60) NOT NULL,
  active BOOLEAN DEFAULT TRUE,
  created_at DATE DEFAULT CURDATE(),
  submitted_at DATE,
  expires_at DATE,
  analyzed_at DATE,
  approved BOOLEAN,
  commentary VARCHAR(60) DEFAULT 'Sem comentários.',
  state ENUM('DRAFT', 'WAITING', 'EXPIRED', 'ANALYZED') DEFAULT 'DRAFT',
  FOREIGN KEY (activity) REFERENCES Activities (id),
  FOREIGN KEY (student) REFERENCES Users (id)
);

DELIMITER $$
CREATE PROCEDURE CreateDraft(IN id CHAR(36), IN activity CHAR(36), IN student CHAR(36), IN workload TINYINT, IN start DATE, IN end DATE, IN attached CHAR(60))
BEGIN
  INSERT INTO Submissions (id, activity, student, workload, start, end, attached, state) VALUES (id, activity, student, workload, start, end, 'DRAFT');
END
DELIMITIER ;

DELIMITER $$
CREATE PROCEDURE DeleteDraft(IN id CHAR(36))
BEGIN
  UPDATE Submissions SET active = FALSE WHERE Submissions.id = id;
END
DELIMITIER ;

DELIMITER $$
CREATE PROCEDURE Submit(IN id CHAR(36))
BEGIN
  UPDATE Submissions SET state = 'WAITING', submitted_at = CURDATE(), expires_at = DATE_ADD(CURDATE(), INTERVAL 30 DAY) WHERE active = TRUE AND Submissions.id = id;
END
DELIMITIER ;

DELIMITER $$
CREATE PROCEDURE Resubmit(IN id CHAR(36))
BEGIN
  UPDATE Submissions SET state = 'WAITING', expires_at (CURDATE(), INTERVAL 30 DAY) WHERE active = TRUE AND state = 'EXPIRED' AND Submissions.id = id;
END
DELIMITIER ;

DELIMITER $$
CREATE PROCEDURE Approve(IN id CHAR (36))
BEGIN
  UPDATE Submissions SET state = 'ANALYZED', analyzed_at = CURDATE(), approved = TRUE WHERE active = TRUE AND state = 'WAITING' AND Submissions.id = id;
END
DELIMITIER ;

DELIMITER $$
CREATE PROCEDURE Reprove(IN id CHAR(36), IN commentary VARCHAR(60))
BEGIN
END
DELIMITIER ;
  UPDATE Submissions SET state = 'ANALYZED', analyzed_at = CURDATE(), Submissions.commentary = commentary, approved = FALSE WHERE active = TRUE AND state = 'WAITING' AND Submissions.id = id;
DELIMITER $$
CREATE PROCEDURE GetDrafts()
BEGIN
  SELECT id, activity, student, workload, start, end, created_at, attached FROM Submissions WHERE active = TRUE AND state = 'DRAFT';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetDraft(IN id CHAR(36))
BEGIN
  SELECT id, activity, student, workload, start, end, created_at, attached FROM Submissions WHERE active = TRUE AND state = 'DRAFT' AND Submissions.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetWaitings()
BEGIN
  SELECT id, activity, student, workload, start, end, created_at, submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'WAITING';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetWaiting(IN id CHAR(36))
BEGIN
  SELECT id, activity, student, workload, start, end, created_at, submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'WAITING' AND Submissions.id = id;
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
  SELECT id, activity, student, workload, start, end, created_at, submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'EXPIRED';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetExpired(IN id CHAR(36))
BEGIN
  SELECT id, activity, student, workload, start, end, created_at, submitted_at, expires_at, attached FROM Submissions WHERE active = TRUE AND state = 'EXPIRED' AND Submissions.id = id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetAnalyzeds()
BEGIN
  SELECT id, approved, activity, student, workload, start, end, created_at, submitted_at, analyzed_at, attached FROM Submissions WHERE active = TRUE AND state = 'ANALYZED';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetAnalyzed(IN id CHAR(36))
BEGIN
  SELECT id, approved, activity, student, workload, start, end, created_at, submitted_at, analyzed_at, attached FROM Submissions WHERE active = TRUE AND state = 'ANALYZED' AND Submissions.id = id;
END $$
DELIMITER ;