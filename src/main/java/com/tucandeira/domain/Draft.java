package com.tucandeira.domain;

import java.time.LocalDate;

@SuppressWarnings("serial")
public final class Draft extends ComplementaryActivity {
  public Waiting submit() {
    var waiting = new Waiting();

    waiting.setUUID(getUUID());

    waiting.setWorkload(getWorkload());

    waiting.setStart(getStart());

    waiting.setEnd(getEnd());

    waiting.setCurriculumLink(isCurriculumLinked());

    waiting.setSupportingDocument(getSupportingDocument());

    waiting.setSubmmitedAt(LocalDate.now());

    return waiting;
  }
}
