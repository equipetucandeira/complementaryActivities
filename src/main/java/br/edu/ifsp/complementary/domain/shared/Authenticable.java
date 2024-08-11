package br.edu.ifsp.complementary.domain.shared;

public abstract class Authenticable {
	private Email email;

	private Password password;

	public Email getEmail() {
		return this.email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Password getPassword() {
		return this.password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}
};
