package br.edu.ifsp.complementary.domain.shared;

import java.util.Objects;

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

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		var casting = (Authenticable) object;

		return getEmail().equals(casting.getEmail()) && getPassword().equals(casting.getPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), getPassword());
	}
};
