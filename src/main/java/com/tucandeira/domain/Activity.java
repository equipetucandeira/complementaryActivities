package com.tucandeira.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Activity implements Serializable {
  private UUID uuid;

  private String name;

  private String observation;

  private Collection<String> supportingDocumentType;

  private Workload workload;

  public Activity(String name, String observation, Collection<String> supportingDocumentType, Workload workload) {
    this(UUID.randomUUID(), name, observation, supportingDocumentType, workload);
  }

  public Activity(UUID uuid, String name, String observation, Collection<String> supportingDocumentType, Workload workload) {
    this.uuid = uuid;

    this.name = name;

    this.observation = observation;

    this.supportingDocumentType = supportingDocumentType;

    this.workload = workload;
  }

  public UUID getUUID() {
    return this.uuid;
  }

  public String getName() {
    return this.name;
  }

  public String getObservation() {
    return this.observation;
  }

  public Collection<String> getSupportingDocumentType() {
    return this.supportingDocumentType;
  }

  public Workload getWorkload() {
    return this.workload;
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name);
  }

  @Override
  public boolean equals(Object object) {
    return hashCode() == object.hashCode();
  }
}
