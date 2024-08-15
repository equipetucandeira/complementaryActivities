package com.tucandeira.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public final class Waiting extends ComplementaryActivity {
  private LocalDate submmitedAt;

  private LocalDate expiresAt;

  public void setSubmmitedAt(LocalDate submittedAt) {
    this.submmitedAt = submittedAt;

    this.expiresAt = submmitedAt.plusDays(30);
  }

  public Long getRemainingDays() {
    return ChronoUnit.DAYS.between(LocalDate.now(), this.expiresAt);
  }

  public Analyzed approve() {
    var analyzed = new Analyzed();

    analyzed.setUUID(getUUID());

    analyzed.setWorkload(getWorkload());

    analyzed.setStart(getStart());

    analyzed.setEnd(getEnd());

    analyzed.setCurriculumLink(isCurriculumLinked());

    analyzed.setSupportingDocument(getSupportingDocument());

    analyzed.setAnalyzedAt(LocalDate.now());

    analyzed.setApproved(true);

    return analyzed;
  }

  public Analyzed reprove(String commentary) {
    var analyzed = new Analyzed();

    analyzed.setUUID(getUUID());

    analyzed.setWorkload(getWorkload());

    analyzed.setStart(getStart());

    analyzed.setEnd(getEnd());

    analyzed.setCurriculumLink(isCurriculumLinked());

    analyzed.setSupportingDocument(getSupportingDocument());

    analyzed.setAnalyzedAt(LocalDate.now());

    analyzed.setApproved(false);

    analyzed.setCommentary(commentary);

    return analyzed;
  }
}
