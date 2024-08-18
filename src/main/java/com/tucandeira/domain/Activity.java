package com.tucandeira.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Activity implements Serializable {
  private UUID uuid;

  private Integer workload;

  private LocalDate start;

  private LocalDate end;

  private boolean curriculumLink;

  private String attached;

  public Complementary() {}

  public Complementary(Integer workload, LocalDate start, LocalDate end, boolean curriculumLink, String attached) {
    this(UUID.randomUUID(), workload, start, end, curriculumLink, attached);
  }

  public Complementary(UUID uuid, Integer workload, LocalDate start, LocalDate end, boolean curriculumLink, String attached) {
    if (workload <= 0) {
      throw new IllegalArgumentException("As atividades devem possuir carga horária maior que zero.");
    }

    this.uuid = uuid;

    this.workload = workload;

    this.start = start;

    this.end = end;

    this.curriculumLink = curriculumLink;

    this.attached = attached;
  }

  public UUID getUUID() {
    return this.uuid;
  }

  public void setUUID(UUID uuid) {
    this.uuid = uuid;
  }

  public void setUUID(String uuid) {
    this.uuid = UUID.fromString(uuid);
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

  public String getAttached() {
    return this.attached;
  }

  public void setAttached(String attached) {
    this.attached = attached;
  }
}
