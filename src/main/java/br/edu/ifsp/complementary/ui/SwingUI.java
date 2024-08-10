package br.edu.ifsp.complementary.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class SwingUI {
	public static JFrame getJFrame(String title, String icon, LayoutManager layout, Dimension dimension, Color background) {
		var component = new JFrame(title);

		component.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		component.setIconImage(new ImageIcon(icon).getImage());

		component.setLayout(layout);

		component.setSize(dimension);
		
		component.setResizable(false);

		component.setLocationRelativeTo(null);

		component.getContentPane().setBackground(background);

		return component;
	}

	public static JPanel getJPanel(Rectangle bounds, Color background) {
		var component = new JPanel();

		component.setBounds(bounds);

		component.setBackground(background);

		return component;
	}

	public static JLabel getJLabel(String text, Rectangle bounds, Font font, Color foreground) {
		var component = new JLabel();

		component.setText(text);

		component.setBounds(bounds);

		component.setFont(font);

		component.setForeground(foreground);

		component.setHorizontalTextPosition(JLabel.LEFT);

		component.setVerticalTextPosition(JLabel.CENTER);

		return component;
	}

	public static JTextField getJTextField(Rectangle bounds, Font font, Color foreground, Color background) {
		var component = new JTextField();

		component.setBounds(bounds);

		component.setFont(font);

		component.setForeground(foreground);

		component.setBackground(background);

		component.setBorder(BorderFactory.createEmptyBorder());

		return component;
	}

	public static JPasswordField getJPasswordField(Rectangle bounds, Font font, Color foreground, Color background) {
		var component = new JPasswordField();

		component.setBounds(bounds);

		component.setFont(font);

		component.setForeground(foreground);

		component.setBackground(background);

		component.setBorder(BorderFactory.createEmptyBorder());

		return component;
	}

	public static JButton getJButton(String text, Rectangle bounds, Font font, Color foreground, Color background) {
		var component = new JButton();

		component.setText(text);

		component.setBounds(bounds);

		component.setFont(font);

		component.setForeground(foreground);

		component.setBackground(background);

		component.setBorderPainted(false);

		component.setBorder(BorderFactory.createEmptyBorder());

		component.setFocusPainted(false);

		component.setFocusable(false);

		return component;
	}

	public static Color getColor(String hexadecimal) {
		var decode = Color.decode(hexadecimal);

		return new Color(decode.getRed(), decode.getGreen(), decode.getBlue());
	}
}
