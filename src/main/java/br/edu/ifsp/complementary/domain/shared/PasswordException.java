package br.edu.ifsp.complementary.domain.shared;

@SuppressWarnings("serial")
public final class PasswordException extends RuntimeException {
	public PasswordException(String message) {
		super(message);
	}
}
