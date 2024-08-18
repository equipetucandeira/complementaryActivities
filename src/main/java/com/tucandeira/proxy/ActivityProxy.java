package com.tucandeira.proxy;

import com.tucandeira.db.Repository;
import com.tucandeira.domain.Activity;

import java.util.Collection;

public final class ActivityProxy extends Proxy<Activity> {
  public ActivityProxy(Repository<Activity> repository) {
    this.repository = repository;

    this.proxy = new Activity();
  }

  @Override
  public boolean save() {
    return this.repository.save(this.proxy);
  }

  @Override
  public Collection<Activity> list() {
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
