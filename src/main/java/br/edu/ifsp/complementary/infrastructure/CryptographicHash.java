package br.edu.ifsp.complementary.infrastructure;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class CryptographicHash {
  private String hash;

  public CryptographicHash(String str) {
    var salt = new byte[16];

    new SecureRandom().nextBytes(salt);

    var iterations = 65536;

    var size = 128;

    try {
      this.hash = Base64.getEncoder()
      .encodeToString(
        SecretKeyFactory
        .getInstance("PBKDF2WithHmacSHA1")
        .generateSecret(
          new PBEKeySpec(
            str.toCharArray(),
            salt,
            iterations,
            size
          )
        )
        .getEncoded()
      );
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return this.hash;
  }
}
