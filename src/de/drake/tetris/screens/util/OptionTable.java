package de.drake.tetris.screens.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.drake.tetris.config.Config;

public class OptionTable extends JPanel {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private int options = 0;

	private Component glue;

	public OptionTable() {
		this.setBackground(Config.textBgColor);
		this.setLayout(new GridBagLayout());
	}
	
	public void addOption(final String name, final JComponent component) {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 3, 5, 3);
		c.gridy = this.options;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		if (this.glue != null)
			this.remove(this.glue);
		
		JLabel label = new JLabel(name + ":");
		label.setForeground(Config.textColor);
		label.setFont(new Font(Font.SERIF, Font.PLAIN, Config.textSize));
		c.gridx = 0;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(label, c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(component, c);
		
		c.insets = new Insets(0, 0, 15, 0);
		c.gridx = 0;
		c.gridy = this.options + 1;
		c.weighty = 1;
		this.glue = Box.createVerticalGlue();
		this.add(this.glue, c);
		
		this.options++;
		
	}

}
