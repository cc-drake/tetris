package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ComponentFactory {
	
	public static JButton createButton(final String text, final String actionCommand, final ActionListener listener) {
		JButton result = new JButton(text);
		result.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		result.setActionCommand(actionCommand);
		result.addActionListener(listener);
		return result;
	}
	
	public static JButton createButton(final String text, final ActionListener listener) {
		return ComponentFactory.createButton(text, text, listener);
	}
	
	public static JLabel createLabel(final String text, final Color color, final Color bgcolor, final int size) {
		JLabel result = new JLabel(text);
		result.setForeground(color);
		result.setFont(new Font(Font.SERIF, Font.BOLD, size));
		result.setHorizontalAlignment(SwingConstants.CENTER);
		if (bgcolor != null) {
			result.setOpaque(true);
			result.setBackground(bgcolor);
		}
		return result;
	}
	
}
