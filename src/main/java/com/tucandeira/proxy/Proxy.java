package com.tucandeira.proxy;

import com.tucandeira.db.Repository;

import java.util.Collection;

public abstract class Proxy<T> {
  protected Repository<T> repository;

  protected T proxy;

  public abstract boolean save();

  public abstract Collection<T> list();

  public abstract boolean update();

  public abstract boolean delete();
}
