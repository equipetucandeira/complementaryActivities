package br.edu.ifsp.complementary;

import br.edu.ifsp.complementary.infrastructure.Configuration;
import br.edu.ifsp.complementary.infrastructure.ConfigurationException;
import br.edu.ifsp.complementary.ui.SwingUI;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					var configuration = new Configuration("application.properties");

					var frame = SwingUI.getJFrame(
						configuration.getProperty("app.window.title"),
						configuration.getProperty("app.window.icon"),
						null,
						new Dimension(
							Integer.valueOf(configuration.getProperty("app.window.width")),
							Integer.valueOf(configuration.getProperty("app.window.height"))
						),
						SwingUI.getColor(configuration.getProperty("app.theme.primary"))
					);

					frame.setVisible(true);
				} catch (ConfigurationException exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "App", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
