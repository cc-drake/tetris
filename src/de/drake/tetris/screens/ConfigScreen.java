package de.drake.tetris.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.drake.tetris.config.Config;
import de.drake.tetris.screens.util.ComponentFactory;
import de.drake.tetris.screens.util.OptionTable;
import de.drake.tetris.states.ModeState;

public class ConfigScreen extends JScrollPane {
	
	private final JCheckBox stone_small, stone_regular, stone_large, stone_bomb;
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public ConfigScreen(final ActionListener listener) {
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			JPanel topPanel = new JPanel();
			topPanel.setBackground(Config.bgColor);
			topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 5, 0, 5);
			c.fill = GridBagConstraints.BOTH;
			contentPanel.add(topPanel, BorderLayout.CENTER);
				
				c.gridx = 0;
				
				c.gridy = 0;
				JLabel settings = ComponentFactory.createLabel("Einstellungen", Color.green);
				topPanel.add(settings, c);
				
				c.gridy = 1;
				OptionTable table = new OptionTable();
				topPanel.add(table, c);
					
					JPanel stones = new JPanel();
					stones.setBackground(Config.textBgColor);
					stones.setLayout(new BoxLayout(stones, BoxLayout.PAGE_AXIS));
					
						this.stone_small = ComponentFactory.createCheckbox(
								"Kleine Steine (< 4 Teile)", false);
						stones.add(this.stone_small);
						
						this.stone_regular = ComponentFactory.createCheckbox(
								"Normale Steine (= 4 Teile)", true);
						stones.add(this.stone_regular);
						
						this.stone_large = ComponentFactory.createCheckbox(
								"Große Steine (= 5 Teile)", false);
						stones.add(this.stone_large);
						
						this.stone_bomb = ComponentFactory.createCheckbox(
								"Bomben", false);
						stones.add(this.stone_bomb);
					
					table.addOption("Steinauswahl", stones);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Config.bgColor);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ModeState.back, listener));
				
	}
	
}