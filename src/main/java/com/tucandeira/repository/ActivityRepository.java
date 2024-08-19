package com.tucandeira.repository;

import com.tucandeira.domain.Activity;
import com.tucandeira.db.Repository;

import java.sql.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.Optional;

public final class ActivityRepository implements Repository<Activity> {
  private Connection connection;

  public ActivityRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Activity cast(ResultSet resultSet) throws Exception {
    var activity = new Activity();

    activity.setUUID(resultSet.getString("id"));

    activity.setStart(resultSet.getDate("start").toLocalDate());

    activity.setEnd(resultSet.getDate("end").toLocalDate());

    activity.setCurriculumLink(resultSet.getBoolean("curriculum_link"));

    activity.setAttached(resultSet.getString("attached"));

    return activity;
  }

  @Override
  public boolean save(Activity activity) {
    try {
      var statement = this.connection.prepareCall("{call Submit(?, ?, ?, ?, ?, ?, ?)}");

      statement.setString(1, activity.getUUID().toString());
      statement.setString(2, activity.getCategory().getID().toString());
      statement.setString(3, activity.getStudent().getUUID().toString());
      statement.setInt(4, activity.getWorkload());
      statement.setDate(5, Date.valueOf(activity.getStart()));
      statement.setDate(6, Date.valueOf(activity.getEnd()));
      statement.setString(7, activity.getAttached());

      statement.executeUpdate();
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public Collection<Activity> list() {
    return new ArrayList<>();
  }

  @Override
  public Optional<Activity> find(UUID uuid) {
    return null;
  }

  @Override
  public boolean update(Activity activity) {
    return true;
  }

  @Override
  public boolean delete(UUID uuid) {
    return true;
  }
}
