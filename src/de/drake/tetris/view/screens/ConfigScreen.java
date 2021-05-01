package de.drake.tetris.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.drake.tetris.config.Config;
import de.drake.tetris.controller.states.ConfigState;
import de.drake.tetris.view.screens.util.ComponentFactory;
import de.drake.tetris.view.screens.util.NumberSpinner;
import de.drake.tetris.view.screens.util.OptionTable;

public class ConfigScreen extends JScrollPane {

	private NumberSpinner stone_small, stone_regular, stone_large, stone_bomb, 
			hoehe, breite, initialSpeed, keyRepeatDelay, keyRepeatSpeed;
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public ConfigScreen(final ActionListener listener) {
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			JPanel topPanel = new JPanel();
			topPanel.setBackground(Config.COLOR_BACKGROUND);
			topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 5, 0, 5);
			c.fill = GridBagConstraints.BOTH;
			contentPanel.add(topPanel, BorderLayout.CENTER);
				
				c.gridx = 0;
				
				c.gridy = 0;
				JLabel settings = ComponentFactory.createLabel("Optionen", Color.green);
				topPanel.add(settings, c);
				
				c.gridy = 1;
				OptionTable table = new OptionTable();
				topPanel.add(table, c);
					
					this.stone_small = new NumberSpinner(Config.stone_small, 0, 100, 1);
					table.addOption("Häufigkeit kleiner Steine (< 4 Teile)", this.stone_small);
					
					this.stone_regular = new NumberSpinner(Config.stone_regular, 0, 100, 1);
					table.addOption("Häufigkeit normaler Steine (= 4 Teile)", this.stone_regular);
					
					this.stone_large = new NumberSpinner(Config.stone_large, 0, 100, 1);
					table.addOption("Häufigkeit großer Steine (= 5 Teile)", this.stone_large);
					
					this.stone_bomb = new NumberSpinner(Config.stone_bomb, 0, 100, 1);
					table.addOption("Häufigkeit von Bomben", this.stone_bomb);
					
					this.breite = new NumberSpinner(Config.breite, 1, 100, 1);
					table.addOption("Breite des Spielfelds (Anzahl Felder)", this.breite);
					
					this.hoehe = new NumberSpinner(Config.hoehe, 1, 100, 1);
					table.addOption("Höhe des Spielfelds (Anzahl Felder)", this.hoehe);
					
					this.initialSpeed = new NumberSpinner(Config.initialSpeed, 0., Config.FPS, .1);
					table.addOption("Initiale Fallgeschwindigkeit (Felder/Sekunde)", this.initialSpeed);
					
					this.keyRepeatDelay = new NumberSpinner(Config.keyRepeatDelay, 0, 1000, 10);
					table.addOption("Tastenwiederholung: Verzögerung (Millisekunden)", this.keyRepeatDelay);
					
					this.keyRepeatSpeed = new NumberSpinner(Config.keyRepeatSpeed, 0, 100, 1);
					table.addOption("Tastenwiederholung: Geschwindigkeit (Felder/Sekunde)", this.keyRepeatSpeed);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Config.COLOR_BACKGROUND);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ConfigState.RESET, listener));
				
				bottomPanel.add(ComponentFactory.createButton(ConfigState.SAVE, listener));
				
				bottomPanel.add(ComponentFactory.createButton(ConfigState.BACK, listener));
				
	}

	public boolean applyConfig() {
		if (this.stone_small.getIntValue() == 0
				&& this.stone_regular.getIntValue() == 0
				&& this.stone_large.getIntValue() == 0
				&& this.stone_bomb.getIntValue() == 0) {
			return false;
		}
		Config.stone_small = this.stone_small.getIntValue();
		Config.stone_regular = this.stone_regular.getIntValue();
		Config.stone_large = this.stone_large.getIntValue();
		Config.stone_bomb = this.stone_bomb.getIntValue();
		Config.breite = this.breite.getIntValue();
		Config.hoehe = this.hoehe.getIntValue();
		Config.initialSpeed = this.initialSpeed.getDoubleValue();
		Config.keyRepeatDelay = this.keyRepeatDelay.getIntValue();
		Config.keyRepeatSpeed = this.keyRepeatSpeed.getIntValue();
		return true;
	}
	
}