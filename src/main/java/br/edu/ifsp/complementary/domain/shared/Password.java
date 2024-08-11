package br.edu.ifsp.complementary.domain.shared;

import br.edu.ifsp.complementary.infrastructure.CryptographicHash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Password {
	public static final String pattern = "\\w{8,32}";

	private String hash;

	private Password(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return this.hash;
	}

	public static boolean isValid(String password) {
		return Pattern.compile(Password.pattern).matcher(password).matches();
	}

	public static Password from(String password) throws PasswordException {
		if (!isValid(password)) {
			throw new PasswordException("A senha deve possuir entre 8 e 32 caracteres.");
		}

		return new Password(new CryptographicHash(password).toString());
	}
}
