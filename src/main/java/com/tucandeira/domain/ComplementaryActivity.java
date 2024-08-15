package com.tucandeira.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("serial")
public abstract class ComplementaryActivity implements Serializable {
  private UUID uuid;

  private Integer workload;

  private LocalDate start;

  private LocalDate end;

  private boolean curriculumLink;

  private String supportingDocument;

  public ComplementaryActivity() {}

  public ComplementaryActivity(Integer workload, LocalDate start, LocalDate end, boolean curriculumLink, String supportingDocument) {
    this(UUID.randomUUID(), workload, start, end, curriculumLink, supportingDocument);
  }

  public ComplementaryActivity(UUID uuid, Integer workload, LocalDate start, LocalDate end, boolean curriculumLink, String supportingDocument) {
    if (workload <= 0) {
      throw new IllegalArgumentException("As atividades devem possuir carga horária maior que zero.");
    }

    this.uuid = uuid;

    this.workload = workload;

    this.start = start;

    this.end = end;

    this.curriculumLink = curriculumLink;

    this.supportingDocument = supportingDocument;
  }

  public UUID getUUID() {
    return this.uuid;
  }

  public void setUUID(UUID uuid) {
    this.uuid = uuid;
  }

  public Integer getWorkload() {
    return this.workload;
  }

  public void setWorkload(Integer workload) {
    this.workload = workload;
  }

  public LocalDate getStart() {
    return this.start;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public LocalDate getEnd() {
    return this.end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }

  public boolean isCurriculumLinked() {
    return this.curriculumLink;
  }

  public void setCurriculumLink(boolean curriculumLink) {
    this.curriculumLink = curriculumLink;
  }

  public String getSupportingDocument() {
    return this.supportingDocument;
  }

  public void setSupportingDocument(String supportingDocument) {
    this.supportingDocument = supportingDocument;
  }
}
