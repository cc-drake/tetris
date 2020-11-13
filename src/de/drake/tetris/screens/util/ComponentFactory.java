package de.drake.tetris.screens.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.drake.tetris.config.Config;

public class ComponentFactory {
	
	public static JButton createButton(final String text, final String actionCommand, final ActionListener listener) {
		JButton result = new JButton(text);
		result.setFont(new Font(Font.SERIF, Font.BOLD, Config.SIZE_TEXT));
		result.setActionCommand(actionCommand);
		result.addActionListener(listener);
		return result;
	}
	
	public static JButton createButton(final String text, final ActionListener listener) {
		return ComponentFactory.createButton(text, text, listener);
	}
	
	public static JLabel createLabel(final String text, final Color color) {
		JLabel result = new JLabel(text);
		result.setForeground(color);
		result.setFont(new Font(Font.SERIF, Font.BOLD, Config.SIZE_HEADER));
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setOpaque(true);
		result.setBackground(Config.COLOR_TEXTBACKGROUND);
		return result;
	}
	
	public static JTextField createJTextField(final String name) {
		JTextField result = new JTextField(name);
		result.setFont(new Font(Font.SERIF, Font.BOLD, Config.SIZE_TEXT));
		return result;
	}
}
