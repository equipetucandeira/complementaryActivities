package com.tucandeira.repository;

import com.tucandeira.domain.ComplementaryActivity;
import com.tucandeira.domain.Draft;
import com.tucandeira.db.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.Optional;

public final class ComplementaryActivityRepository implements Repository<ComplementaryActivity> {
  @Override
  public ComplementaryActivity cast(ResultSet resultSet) throws Exception {
    return new Draft();
  }

  @Override
  public boolean save(ComplementaryActivity activity) {
    return true;
  }

  @Override
  public Collection<ComplementaryActivity> list() {
    return new ArrayList<>();
  }

  @Override
  public Optional<ComplementaryActivity> find(UUID uuid) {
    return null;
  }

  @Override
  public boolean update(ComplementaryActivity activity) {
    return true;
  }

  @Override
  public boolean delete(UUID uuid) {
    return true;
  }
}
