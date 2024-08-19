package com.tucandeira.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Activity implements Serializable {
  private UUID uuid;

  private String name;

  private Integer workload;

  private LocalDate start;

  private LocalDate end;

  private boolean curriculumLink;

  private String attached;

  private Category category;

  private String status;

  private Student student;

  private boolean approved;

  public Activity() {}

  public Activity(String name, Integer workload, LocalDate start, LocalDate end, boolean curriculumLink, String attached, Category category) {
    this(UUID.randomUUID(), name, workload, start, end, curriculumLink, attached, category);
  }

  public Activity(UUID uuid, String name, Integer workload, LocalDate start, LocalDate end, boolean curriculumLink, String attached, Category category) {
    if (workload <= 0) {
      throw new IllegalArgumentException("As atividades devem possuir carga horária maior que zero.");
    }

    this.uuid = uuid;

    this.name = name;

    this.workload = workload;

    this.start = start;

    this.end = end;

    this.curriculumLink = curriculumLink;

    this.attached = attached;

    this.category = category;
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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
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

  public void setStudent(Student student) {
    this.student = student;

    student.addActivity(this);
  }

  public Student getStudent() {
    return this.student;
  }

  public void setAttached(String attached) {
    this.attached = attached;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public boolean isApproved() {
    return this.approved;
  }
}
