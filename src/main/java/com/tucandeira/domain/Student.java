package com.tucandeira.domain;

import java.util.Collection;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Student extends User {
  private Course course;

  private Collection<ComplementaryActivity> activities;

  public Student() {}

  public Student(UUID uuid, String name, Email email, Password password) {
    super(uuid, name, email, password);
  }

  public Course getCourse() {
    return this.course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
