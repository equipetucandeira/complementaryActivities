package br.edu.ifsp.complementary;

import br.edu.ifsp.complementary.infrastructure.Configuration;
import br.edu.ifsp.complementary.infrastructure.ConfigurationException;

public final class AppConfiguration {
	private Configuration configuration;

	public AppConfiguration(String filename) throws ConfigurationException {
		this.configuration = new Configuration(filename);
	}

	public String getTitle() {
		return this.configuration.getProperty("app.window.title");
	}

	public String getIcon() {
		return this.configuration.getProperty("app.window.icon");
	}

	public int getWidth() {
		return Integer.valueOf(this.configuration.getProperty("app.window.width"));
	}

	public int getHeight() {
		return Integer.valueOf(this.configuration.getProperty("app.window.height"));
	}

	public String getDatabaseDriver() {
		return this.configuration.getProperty("db.driver");
	}

	public String getDatabaseURL() {
		return this.configuration.getProperty("db.url");
	}

	public String getDatabaseUser() {
		return this.configuration.getProperty("db.user");
	}

	public String getDatabasePassword() {
		return this.configuration.getProperty("db.password");
	}

	public String getBackgroundColor() {
		return this.configuration.getProperty("app.theme.background");
	}

	public String getFontFamily() {
		return this.configuration.getProperty("app.window.text.font-family");
	}

	public int getFontSize() {
		return Integer.valueOf(this.configuration.getProperty("app.window.text.font-size"));
	}

	public String getFontForegroundColor() {
		return this.configuration.getProperty("app.window.text.color");
	}
}
