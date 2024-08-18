package com.tucandeira.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Servant extends User {
  private Collection<Course> courses;

  public Servant(String name, Email email, Password password) {
    this(UUID.randomUUID(), name, email, password);
  }

  public Servant(UUID uuid, String name, Email email, Password password) {
    super(uuid, name, email, password);

    this.courses = new HashSet<>();
  }

  public Collection<Course> getCourses() {
    return this.courses;
  }

  public void add(Course course) {
    this.courses.add(course);
  }
}
