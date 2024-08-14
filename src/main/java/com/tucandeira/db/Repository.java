package com.tucandeira.db;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> extends ResultSetTypecasting<T> {
  boolean save(T object);

  Collection<T> list();

  Optional<T> find(UUID uuid);

  boolean update(T object);

  boolean delete(UUID uuid);
}
