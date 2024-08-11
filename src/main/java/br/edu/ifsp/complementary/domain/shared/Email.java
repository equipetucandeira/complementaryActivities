package br.edu.ifsp.complementary.domain.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Email {
	public static final String pattern = "^(.+)@(\\S+){3,320}$";

	private String address;

	private Email(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.address;
	}

	public static boolean isValid(String address) {
		return Pattern.compile(Email.pattern).matcher(address).matches();
	}

	public static Email from(String address) throws EmailException {
		if (!isValid(address)) {
			throw new EmailException("E-mail inválido.");
		}

		return new Email(address);
	}
}
