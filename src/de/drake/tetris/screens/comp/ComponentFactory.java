package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.drake.tetris.config.Config;

public class ComponentFactory {
	
	public static JButton createButton(final String text, final String actionCommand, final ActionListener listener) {
		JButton result = new JButton(text);
		result.setFont(new Font(Font.SERIF, Font.BOLD, Config.textSize));
		result.setActionCommand(actionCommand);
		result.addActionListener(listener);
		return result;
	}
	
	public static JButton createButton(final String text, final ActionListener listener) {
		return ComponentFactory.createButton(text, text, listener);
	}
	
	public static JCheckBox createCheckbox(final String text, final boolean isSelected) {
		JCheckBox result = new JCheckBox(text, isSelected);
		result.setForeground(Config.textColor);
		result.setBackground(Config.textBgColor);
		result.setFont(new Font(Font.SERIF, Font.PLAIN, Config.textSize));
		return result;
	}
	
	public static JLabel createLabel(final String text, final Color color) {
		JLabel result = new JLabel(text);
		result.setForeground(color);
		result.setFont(new Font(Font.SERIF, Font.BOLD, Config.headerSize));
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setOpaque(true);
		result.setBackground(Config.textBgColor);
		return result;
	}
	
	public static JTextField createJTextField(final String name) {
		JTextField result = new JTextField(name);
		result.setFont(new Font(Font.SERIF, Font.BOLD, Config.textSize));
		return result;
	}
}
