package com.tucandeira.domain;

@SuppressWarnings("serial")
public final class Student extends User {
  private Course course;

  public Course getCourse() {
    return this.course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
