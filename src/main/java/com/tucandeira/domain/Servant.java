package com.tucandeira.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Servant implements Serializable {
  private UUID uuid;

  private String name;

  private Email email;

  private Password password;

  private Collection<Course> courses;

  public Servant() {}

  public Servant(String name, Email email, Password password) {
    this(UUID.randomUUID(), name, email, password);
  }

  public Servant(UUID uuid, String name, Email email, Password password) {
    this.uuid = uuid;

    this.name = name;

    this.email = email;

    this.password = password;

    this.courses = new HashSet<>();
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

  public Collection<Course> getCourses() {
    return this.courses;
  }

  public void add(Course course) {
    this.courses.add(course);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, email);
  }

  @Override
  public boolean equals(Object object) {
    return hashCode() == object.hashCode();
  }
}
