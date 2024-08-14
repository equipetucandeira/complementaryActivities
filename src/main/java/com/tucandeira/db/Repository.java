package com.tucandeira.db;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {
  T save(T object);

  Collection<T> list();

  Optional<T> find(UUID uuid);

  T update(T object);

  boolean delete(T object);
}
