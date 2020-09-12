package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
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

	private Component glue;

	public OptionTable(final Color color, final Color bgcolor, final int size) {
		
		this.color = color;
		this.size = size;
		if (bgcolor != null) {
			this.setBackground(bgcolor);
		} else {
			this.setOpaque(false);
		}
		this.setLayout(new GridBagLayout());

	}
	
	public void addOption(final String name, final JSpinner spinner) {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 3, 5, 3);
		c.gridy = this.options;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		if (this.glue != null)
			this.remove(this.glue);
		
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
		
		c.insets = new Insets(0, 0, 15, 0);
		c.gridx = 0;
		c.gridy = this.options + 1;
		c.weighty = 1;
		this.glue = Box.createVerticalGlue();
		this.add(this.glue, c);
		
		this.options++;
		
	}

}
