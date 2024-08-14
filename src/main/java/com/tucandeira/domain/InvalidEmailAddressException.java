package com.tucandeira.domain;

@SuppressWarnings("serial")
public final class InvalidEmailAddressException extends IllegalArgumentException {
  public InvalidEmailAddressException(String message) {
    super(message);
  }
}
