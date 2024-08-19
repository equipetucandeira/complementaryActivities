package com.tucandeira.repository;

import com.tucandeira.domain.Activity;
import com.tucandeira.db.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.Optional;

public final class ActivityRepository implements Repository<Activity> {
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
