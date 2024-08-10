package br.edu.ifsp.complementary.infrastructure;

import java.io.IOException;
import java.util.Properties;

public final class Configuration {
  private Properties properties;

  public Configuration(String filename) throws IOException {
    this.properties = new Properties();

    var stream = this.getClass().getResourceAsStream(filename);

    this.properties.load(stream);
  }

  public String getProperty(String property) {
    return this.properties.getProperty(property);
  }
}
