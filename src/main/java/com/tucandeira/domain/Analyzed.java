package com.tucandeira.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public final class Analyzed extends ComplementaryActivity {
  private LocalDate analyzedAt;

  private boolean approved;

  private String commentary;

  public String getCommentary() {
    return this.commentary;
  }

  public void setCommentary(String commentary) {
    this.commentary = commentary;
  }

  public void setAnalyzedAt(LocalDate analyzedAt) {
    this.analyzedAt = analyzedAt;
  }

  public Long getWaitedDays() {
    return ChronoUnit.DAYS.between(this.analyzedAt, LocalDate.now());
  }

  public boolean isApproved() {
    return false;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }
}
