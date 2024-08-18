package com.tucandeira.domain;

import java.util.Collection;
import java.util.Objects;
import java.io.Serializable;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Student implements Serializable {
  private UUID uuid;

  private String name;

  private Email email;

  private Password password;

  private Course course;

  private Collection<ComplementaryActivity> activities;

  public Student() {}

  public Student(String name, Email email, Password password) {
    this(UUID.randomUUID(), name, email, password);
  }

  public Student(UUID uuid, String name, Email email, Password password) {
    this.uuid = uuid;

    this.name = name;

    this.email = email;

    this.password = password;
  }

  public UUID getUUID() {
    return this.uuid;
  }

  public void setUUID(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Email getEmail() {
    return this.email;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  public Password getPassword() {
    return this.password;
  }

  public void setPassword(Password password) {
    this.password = password;
  }

  public Course getCourse() {
    return this.course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, email);
  }

  @Override
  public boolean equals(Object object) {
    return hashCode() == object.hashCode();
  }

  @Override
  public String toString() {
    return this.name;
  }
}
