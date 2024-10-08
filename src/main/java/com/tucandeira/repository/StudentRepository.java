package com.tucandeira.repository;

import com.tucandeira.db.Repository;
import com.tucandeira.domain.Course;
import com.tucandeira.domain.Email;
import com.tucandeira.domain.Password;
import com.tucandeira.domain.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public final class StudentRepository implements Repository<Student> {
  private Connection connection;

  public StudentRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Student cast(ResultSet resultSet) throws SQLException {
    var uuid = UUID.fromString(resultSet.getString("id"));

    var name = resultSet.getString("name");

    var email = new Email(resultSet.getString("email"));

    var password = new Password(resultSet.getString("password"));

    var course = new Course(
      UUID.fromString(resultSet.getString("course_id")),
      resultSet.getString("course"),
      resultSet.getInt("workload")
    );

    var student = new Student(uuid, name, email, password);

    student.setCourse(course);

    return student;
  }

  @Override
  public boolean save(Student student) {
    try {
      var statement = this.connection.prepareCall("INSERT INTO Students (uuid, name, email, password) VALUES (?, ?, ?, ?)");

      statement.setString(1, student.getUUID().toString());

      statement.setString(2, student.getName());
      
      statement.setString(3, student.getEmail().address());

      statement.setString(4, student.getPassword().toString());

      statement.executeUpdate();

      return true;
    } catch (SQLException exception) {
      return false;
    }
  }

  @Override
  public Collection<Student> list() {
    Collection<Student> students = new ArrayList<>();

    try {
     var statement = this.connection.prepareCall("{call GetStudents()}");

     var resultSet = statement.executeQuery();

     while (resultSet.next()) {
      students.add(cast(resultSet));
     }
    } catch (SQLException exception) {
      return new ArrayList<Student>();
    }

    return students;
  }

  @Override
  public Optional<Student> find(UUID uuid) {
    try {
      var statement = this.connection.prepareStatement("SELECT * FROM Students WHERE uuid = ? AND acive = TRUE");

      statement.setString(1, uuid.toString());

      var resultSet = statement.executeQuery();

      return Optional.of(cast(resultSet));
    } catch (SQLException exception) {
      return Optional.ofNullable(null);
    }
  }

  @Override
  public boolean update(Student student) {
    try {
      var statement = this.connection.prepareStatement("UPDATE Students SET name = ? WHERE uuid = ?");

      statement.setString(1, student.getName());

      statement.setString(2, student.getUUID().toString());

      statement.executeUpdate();

      return true;
    } catch (SQLException exception) {
      return false;
    }
  }

  @Override
  public boolean delete(UUID uuid) {
    try {
      var statement = this.connection.prepareStatement("UPDATE Students SET active = FALSE WHERE uuid = ?");

      statement.setString(1, uuid.toString());

      statement.executeUpdate();

      return true;
    } catch (SQLException exception) {
      return false;
    }
  }
}
