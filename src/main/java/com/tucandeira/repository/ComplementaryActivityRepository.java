package com.tucandeira.repository;

import com.tucandeira.domain.ComplementaryActivity;

import java.util.Collection;
import java.util.UUID;
import java.util.Optional;

public final class ComplementaryActivityRepository implements Repository<ComplementaryActivity> {
  @Override
  public boolean save(ComplementaryActivity activity);

  @Override
  public Collection<T> list();

  @Override
  public Optional<T> find(UUID uuid);

  @Override
  public boolean update(ComplementaryActivity activity);

  @Override
  public boolean delete(UUID uuid);
}
