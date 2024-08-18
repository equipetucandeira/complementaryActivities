package com.tucandeira.domain;

import java.util.Objects;
import java.io.Serializable;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Category implements Serializable {
  private UUID id;
  private String name;
  private String description;
  private Integer submissionLimit;
  private Integer workload;

  public Category(String name, String description, Integer submissionLimit, Integer workload) {
    this(UUID.randomUUID(), name, description, submissionLimit, workload);
  }

  public Category(UUID id, String name, String description, Integer submissionLimit, Integer workload) {
    if (submissionLimit <= 0) {
      throw new IllegalArgumentException("O limite de envios desta categoria deve ser maior do que zero.");
    }
    
    if (workload <= 0) {
      throw new IllegalArgumentException("A carga horária desta categoria deve ser maior do que zero.");
    }

    this.id = id;

    this.name = name;

    this.description = description;

    this.submissionLimit = submissionLimit;

    this.workload = workload;
  }

  public UUID getID() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getSubmissionLimit() {
    return this.submissionLimit;
  }

  public Integer getWorkload() {
    return this.workload;
  }

  @Override
  public String toString() {
    return this.name + ": " + this.description;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

  @Override
  public boolean equals(Object object) {
    return hashCode() == object.hashCode();
  }
}
