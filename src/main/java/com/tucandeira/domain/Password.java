package com.tucandeira.domain;

import java.util.Base64;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Password {
  private static final String pattern = "$.{8,}$";

  private String password;

  public Password(String password) throws ShortPasswordException {
    this.password = password;
  }

  public static Password from(String password) throws ShortPasswordException {
    if (!Pattern.compile(pattern).matcher(password).matches()) {
      throw new ShortPasswordException("A senha deve possuir ao menos 8 caracteres.");
    }

    return new Password(password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.password);
  }

  @Override
  public boolean equals(Object object) {
    return hashCode() == object.hashCode();
  }

  @Override
  public String toString() {
    return Base64.getEncoder().encodeToString(this.password.getBytes());
  }
}
