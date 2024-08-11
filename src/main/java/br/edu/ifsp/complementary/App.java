package br.edu.ifsp.complementary;

import br.edu.ifsp.complementary.infrastructure.Configuration;
import br.edu.ifsp.complementary.infrastructure.ConfigurationException;
import br.edu.ifsp.complementary.ui.SwingUI;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class App {
	private static String title;

	private static Dimension dimensions;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					var configuration = new Configuration("application.properties");

					App.title = configuration.getProperty("app.window.title");

					App.dimensions = new Dimension(
						Integer.valueOf(configuration.getProperty("app.window.width")),
						Integer.valueOf(configuration.getProperty("app.window.height"))
					);

					try {
						Class.forName("db.driver");
					} catch (ClassNotFoundException exception) {
						showErrorMessage("Driver " + configuration.getProperty("db.driver") + " não encontrado.");

						return;
					}

					var frame = SwingUI.getJFrame(
						App.title,
						configuration.getProperty("app.window.icon"),
						null,
						App.dimensions,
						SwingUI.getColor(configuration.getProperty("app.theme.primary"))
					);

					frame.setVisible(true);
				} catch (ConfigurationException exception) {
					showErrorMessage(exception.getMessage());
				}
			}
		});
	}

	private static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, App.title, JOptionPane.ERROR_MESSAGE);
	}
}
