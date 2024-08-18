package com.tucandeira.proxy;

import com.tucandeira.db.Repository;
import com.tucandeira.domain.ComplementaryActivity;

import java.util.Collection;

public final class ComplementaryActivityProxy extends Proxy<ComplementaryActivity> {
  public ComplementaryActivityProxy(Repository<ComplementaryActivity> repository) {
    this.repository = repository;

    this.proxy = new ComplementaryActivity();
  }

  @Override
  public boolean save() {
    return this.repository.save(this.proxy);
  }

  @Override
  public Collection<ComplementaryActivity> list() {
    return this.repository.list();
  }

  @Override
  public boolean update() {
    return this.repository.update(this.proxy);
  }

  @Override
  public boolean delete() {
    return this.repository.delete(this.proxy.getUUID());
  }
}
