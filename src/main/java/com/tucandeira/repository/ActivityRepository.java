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
    var complementaryActivity = new Activity();

    complementaryActivity.setUUID(resultSet.getString("id"));

    complementaryActivity.setStart(resultSet.getDate("start").toLocalDate());

    complementaryActivity.setEnd(resultSet.getDate("end").toLocalDate());

    complementaryActivity.setCurriculumLink(resultSet.getBoolean("curriculum_link"));

    complementaryActivity.setAttached(resultSet.getString("attached"));

    return complementaryActivity;
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
