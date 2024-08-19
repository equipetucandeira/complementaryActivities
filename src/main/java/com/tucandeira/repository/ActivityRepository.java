package com.tucandeira.repository;

import com.tucandeira.App;
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

    activity.setName(resultSet.getString("name"));

    activity.setStart(resultSet.getDate("start").toLocalDate());

    activity.setEnd(resultSet.getDate("end").toLocalDate());

    activity.setWorkload(resultSet.getInt("workload"));

    activity.setCurriculumLink(resultSet.getBoolean("curriculum_link"));

    activity.setAttached(resultSet.getString("attached"));

    activity.setStatus(resultSet.getString("state"));

    return activity;
  }

  @Override
  public boolean save(Activity activity) {
    try {
      var statement = this.connection.prepareCall("{call Submit(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

      statement.setString(1, activity.getUUID().toString());
      statement.setString(2, activity.getCategory().getID().toString());
      statement.setString(3, activity.getStudent().getUUID().toString());
      statement.setString(4, activity.getName());
      statement.setInt(5, activity.getWorkload());
      statement.setDate(6, Date.valueOf(activity.getStart()));
      statement.setDate(7, Date.valueOf(activity.getEnd()));
      statement.setBoolean(8, activity.isCurriculumLinked());
      statement.setString(9, activity.getAttached());

      statement.executeUpdate();
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public Collection<Activity> list() {
    try {
      Collection<Activity> activities = new ArrayList<>();

      var statement = this.connection.prepareCall("{call GetSubmissions(?)}");

      statement.setString(1, App.getStudent().getUUID().toString());

      var resultSet = statement.executeQuery();

      while (resultSet.next()) {
        activities.add(cast(resultSet));
      }

      return activities;
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return new ArrayList<>(); 
    }
  }

  public Collection<Activity> listAll() {
    try {
      Collection<Activity> activities = new ArrayList<>();

      var statement = this.connection.prepareCall("{call GetAllSubmissions()}");

      var resultSet = statement.executeQuery();

      while (resultSet.next()) {
        activities.add(cast(resultSet));
      }

      return activities;
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return new ArrayList<>(); 
    }
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
