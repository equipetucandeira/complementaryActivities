package com.tucandeira.domain;

import com.tucandeira.domain.Email;
import com.tucandeira.domain.Password;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Student implements Serializable {
  private UUID uuid;

  private String name;

  private Email email;

  private Password password;

  public Student(String name, Email email, Password password) {
    this.uuid = UUID.randomUUID();

    this.name = name;

    this.email = email;

    this.password = password;
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

  public String getName() {
    return this.name;
  }

  public Email getEmail() {
    return this.email;
  }

  public Password getPassword() {
    return this.password;
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, email);
  }

  @Override
  public boolean equals(Object object) {
    return getClass() == object.getClass() || hashCode() == object.hashCode();
  }
}
