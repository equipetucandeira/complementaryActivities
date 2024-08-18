package com.tucandeira.repository;

import com.tucandeira.db.Repository;
import com.tucandeira.domain.Course;
import com.tucandeira.domain.Workload;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public final class CourseRepository implements Repository<Course> {
  private Connection connection;

  public CourseRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Course cast(ResultSet resultSet) throws SQLException {
    UUID uuid = UUID.fromString(resultSet.getString("id"));

    String name = resultSet.getString("name");

    Integer workload = resultSet.getInt("workload");

    return new Course(uuid, name, workload);
  }

  @Override
  public boolean save(Course course) {
    try {
      var statement = this.connection.prepareStatement("INSERT INTO Courses (uuid, name, workload) VALUES (?, ?, ?)");

      statement.setString(1, course.getUUID().toString());

      statement.setString(2, course.getName());

      statement.setInt(3, course.getWorkload());

      statement.executeUpdate();

      return true;
    } catch (SQLException exception) {
      return false;
    }
  }

  @Override
  public Collection<Course> list() {
    Collection<Course> courses = new ArrayList<Course>();

    try {
      var statement = this.connection.prepareCall("{call GetCourses()}");

      var resultSet = statement.executeQuery();

      while (resultSet.next()) {
        courses.add(cast(resultSet));
      }
    } catch (SQLException exception) {
      return new ArrayList<Course>();
    }

    return courses;
  }

  @Override
  public Optional<Course> find(UUID uuid) {
    try {
      var statement = this.connection.prepareStatement("SELECT * FROM Courses WHERE uuid = ? AND acive = TRUE");

      statement.setString(1, uuid.toString());

      var resultSet = statement.executeQuery();

      return Optional.of(cast(resultSet));
    } catch (SQLException exception) {
      return Optional.ofNullable(null);
    }
  }

  @Override
  public boolean update(Course course) {
    try {
      var statement = this.connection.prepareStatement("UPDATE Courses SET workload = ? WHERE uuid = ?");

      statement.setInt(1, course.getWorkload());

      statement.setString(2, course.getUUID().toString());

      statement.executeUpdate();

      return true;
    } catch (SQLException exception) {
      return false;
    }
  }

  @Override
  public boolean delete(UUID uuid) {
    try {
      var statement = this.connection.prepareStatement("UPDATE Courses SET active = FALSE WHRE uuid = ?");

      statement.setString(1, uuid.toString());

      statement.executeUpdate();

      return true;
    } catch (SQLException exception) {
      return false;
    }
  }
}
