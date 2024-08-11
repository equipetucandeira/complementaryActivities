package br.edu.ifsp.complementary.domain.shared;

@SuppressWarnings("serial")
public final class EmailException extends RuntimeException {
	public EmailException(String message) {
		super(message);
	}
}
