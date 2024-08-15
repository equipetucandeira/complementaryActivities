package com.tucandeira.proxy;

import com.tucandeira.db.Repository;
import com.tucandeira.domain.Email;
import com.tucandeira.domain.Course;
import com.tucandeira.domain.Password;
import com.tucandeira.domain.Student;

import java.util.Collection;
import java.util.UUID;

public final class StudentProxy extends Proxy<Student> {
  public StudentProxy(Repository<Student> repository) {
    this.repository = repository;

    this.proxy = new Student();
  }

  @Override
  public boolean save() {
    return this.repository.save(this.proxy);
  }

  @Override
  public Collection<Student> list() {
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

  public void setUUID(UUID uuid) {
    this.proxy.setUUID(uuid);
  }

  public void setName(String name) {
    this.proxy.setName(name);
  }
  
  public void setEmail(Email email) {
    this.proxy.setEmail(email);
  }

  public void setPassword(Password password) {
    this.proxy.setPassword(password);
  }

  public void setCourse(Course course) {
    this.proxy.setCourse(course);
  }
}
