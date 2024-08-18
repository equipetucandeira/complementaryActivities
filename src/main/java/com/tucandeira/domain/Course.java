package com.tucandeira.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("serial")
public final class Course implements Serializable {
  private UUID uuid;

  private String name;

  private Integer workload;

  private Collection<Student> students;

  private Collection<Servant> servants;

  public Course(String name, Integer workload) throws IllegalArgumentException {
    this(UUID.randomUUID(), name, workload);
  }

  public Course(UUID uuid, String name, Integer workload) throws IllegalArgumentException {
    if (workload <= 0) {
      throw new IllegalArgumentException("As horas complementares devem ser maiores do que zero.");
    }

    this.uuid = uuid;

    this.name = name;

    this.workload = workload;

    this.students = new HashSet<Student>();

    this.servants = new HashSet<Servant>();
  }

  public UUID getUUID() {
    return this.uuid;
  }

  public String getName() {
    return this.name;
  }

  public Integer getWorkload() {
    return this.workload;
  }

  public Collection<Student> getStudents() {
    return this.students;
  }

  public void add(Servant servant) {
    this.servants.add(servant);
  }

  public void add(Student student) {
    this.students.add(student);

    student.setCourse(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, workload);
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
