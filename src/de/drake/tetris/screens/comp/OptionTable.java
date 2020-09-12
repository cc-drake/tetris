package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class OptionTable extends JPanel {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private final Color color;
	
	private final int size;
	
	private int options = 0;

	public OptionTable(final Color color, final int size) {
		
		this.color = color;
		this.size = size;
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());

	}
	
	public void addOption(final String name, final JSpinner spinner) {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 3, 5, 3);
		c.gridy = this.options;
		
		JLabel label = new JLabel(name + ":");
		label.setForeground(this.color);
		label.setFont(new Font(Font.SERIF, Font.PLAIN, this.size));
		c.gridx = 0;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(label, c);
		
		spinner.setFont(new Font(Font.SERIF, Font.BOLD, this.size));
		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(spinner, c);
		
		this.options++;
	}

}
