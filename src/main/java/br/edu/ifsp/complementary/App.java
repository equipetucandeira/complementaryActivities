package br.edu.ifsp.complementary;

import br.edu.ifsp.complementary.data.Jdbc;
import br.edu.ifsp.complementary.ui.SwingUI;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class App {
	private static AppConfiguration configuration;

	private static Jdbc dataSource;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					configuration = new AppConfiguration("application.properties");

					Class.forName(configuration.getDatabaseDriver());

					dataSource = new Jdbc(
						configuration.getDatabaseURL(),
						configuration.getDatabaseUser(),
						configuration.getDatabasePassword()
					);
				} catch (ClassNotFoundException exception) {
					showErrorMessage("Driver " + configuration.getDatabaseDriver() + " não encontrado.");
				} catch (Exception exception) {
					showErrorMessage(exception.getMessage());
				}

				var frame = SwingUI.getJFrame(
					configuration.getTitle(),
					configuration.getIcon(),
					null,
					new Dimension(
						configuration.getWidth(),
						configuration.getHeight()
					),
					SwingUI.getColor(configuration.getBackgroundColor())
				);

				frame.setVisible(true);
			}
		});
	}

	private static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, configuration.getTitle(), JOptionPane.ERROR_MESSAGE);
	}
}
