package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class LabeledSpinner extends JPanel {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private final JSpinner spinner;

	public LabeledSpinner(final String name, final Color color, final int size, final JSpinner spinner) {
		super.setOpaque(false);
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel label = new JLabel(name + ":");
		label.setForeground(color);
		label.setFont(new Font(Font.SERIF, Font.PLAIN, size));
		super.add(label);
		
		this.spinner = spinner;
		spinner.setFont(new Font(Font.SERIF, Font.BOLD, size));
		super.add(spinner);
	}
	
	public Date getValue() {
		return (Date) this.spinner.getValue();
	}
}
