package com.tucandeira.domain;

import java.util.regex.Pattern;

public record Email(String address) {
  private static final String pattern = "^(.+)@(\\S+){3,320}$";

  public Email {
    if (!Pattern.compile(pattern).matcher(address).matches()) {
      throw new IllegalArgumentException("Endereço de e-mail inválido.");
    }
  }
}
