package com.tucandeira.domain;

@SuppressWarnings("serial")
public final class ShortPasswordException extends IllegalArgumentException {
  public ShortPasswordException(String message) {
    super(message);
  }
}
