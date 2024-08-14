package com.tucandeira.domain;

import java.util.Base64;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Password {
  private final String pattern = "\\w{8,}";

  private String hash;

  public Password(String password) {
    if (!Pattern.compile(pattern).matcher(password).matches()) {
      throw new ShortPasswordException("A senha deve possuir ao menos 8 caracteres.");
    }

    this.hash = Base64.getEncoder().encodeToString(password.getBytes());
  }

  @Override
  public int hashCode() {
    return Objects.hash(hash);
  }

  @Override
  public boolean equals(Object object) {
    return hashCode() == object.hashCode();
  }

  @Override
  public String toString() {
    return this.hash;
  }
}
