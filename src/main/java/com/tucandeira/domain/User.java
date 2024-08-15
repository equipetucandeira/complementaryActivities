package com.tucandeira.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("serial")
public abstract class User implements Serializable {
  private UUID uuid;

  private String name;

  private Email email;

  private Password password;

  public User() {}

  public User(String name, Email email, Password password) {
    this(UUID.randomUUID(), name, email, password);
  }

  public User(UUID uuid, String name, Email email, Password password) {
    this.uuid = uuid;

    this.name = name;

    this.email = email;

    this.password = password;
  }

  public final UUID getUUID() {
    return this.uuid;
  }

  public final void setUUID(UUID uuid) {
    this.uuid = uuid;
  }

  public final String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public final Email getEmail() {
    return this.email;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  public final Password getPassword() {
    return this.password;
  }

  public void setPassword(Password password) {
    this.password = password;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(uuid, name, email);
  }

  @Override
  public final boolean equals(Object object) {
    return hashCode() == object.hashCode();
  }
}
