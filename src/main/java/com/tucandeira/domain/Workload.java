package com.tucandeira.domain;

public record Workload(Integer perActivity, Integer max) {
  public Workload {
    if (perActivity <= 0 || max <= 0) {
      throw new IllegalArgumentException("As horas complementares devem ser maiores do que zero.");
    }
  }
}
