package de.drake.tetris.screens;

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
import de.drake.tetris.screens.util.ComponentFactory;
import de.drake.tetris.screens.util.NumberSpinner;
import de.drake.tetris.screens.util.OptionTable;
import de.drake.tetris.states.ModeState;

public class ConfigScreen extends JScrollPane {

	private NumberSpinner stone_small, stone_regular, stone_large, stone_bomb, 
			hoehe, breite, feld_seitenverhaeltnis, keyRepeatDelay, keyRepeatSpeed;
	
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
					table.addOption("H�ufigkeit kleiner Steine (< 4 Teile)", this.stone_small);
					
					this.stone_regular = new NumberSpinner(Config.stone_regular, 0, 100, 1);
					table.addOption("H�ufigkeit normaler Steine (= 4 Teile)", this.stone_regular);
					
					this.stone_large = new NumberSpinner(Config.stone_large, 0, 100, 1);
					table.addOption("H�ufigkeit gro�er Steine (= 5 Teile)", this.stone_large);
					
					this.stone_bomb = new NumberSpinner(Config.stone_bomb, 0, 100, 1);
					table.addOption("H�ufigkeit von Bomben", this.stone_bomb);
					
					this.breite = new NumberSpinner(Config.breite, 10, 100, 1);
					table.addOption("Breite des Spielfelds (Anzahl Felder)", this.breite);
					
					this.hoehe = new NumberSpinner(Config.hoehe, 10, 100, 1);
					table.addOption("H�he des Spielfelds (Anzahl Felder)", this.hoehe);
					
					this.feld_seitenverhaeltnis = new NumberSpinner(Config.feld_seitenverhaeltnis, .1, 9.9, .1);
					table.addOption("Seitenverh�ltnis der Felder (Breite/H�he)", this.feld_seitenverhaeltnis);
					
					this.keyRepeatDelay = new NumberSpinner(Config.keyRepeatDelay, 0, 1000, 10);
					table.addOption("Tastenwiederholung: Verz�gerung (Millisekunden)", this.keyRepeatDelay);
					
					this.keyRepeatSpeed = new NumberSpinner(Config.keyRepeatSpeed, 0, 100, 1);
					table.addOption("Tastenwiederholung: Geschwindigkeit (Felder/Sekunde)", this.keyRepeatSpeed);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Config.COLOR_BACKGROUND);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ModeState.back, listener));
				
	}

	public boolean applyConfig() {
		if ((double) this.stone_small.getValue() == 0.
				&& (double) this.stone_regular.getValue() == 0.
				&& (double) this.stone_large.getValue() == 0.
				&& (double) this.stone_bomb.getValue() == 0.) {
			return false;
		}
		Config.stone_small = ((Double) this.stone_small.getValue()).intValue();
		Config.stone_regular = ((Double) this.stone_regular.getValue()).intValue();
		Config.stone_large = ((Double) this.stone_large.getValue()).intValue();
		Config.stone_bomb = ((Double) this.stone_bomb.getValue()).intValue();
		Config.breite = ((Double) this.breite.getValue()).intValue();
		Config.hoehe = ((Double) this.hoehe.getValue()).intValue();
		Config.feld_seitenverhaeltnis = (double) this.feld_seitenverhaeltnis.getValue();
		Config.keyRepeatDelay = ((Double) this.keyRepeatDelay.getValue()).intValue();
		Config.keyRepeatSpeed = ((Double) this.keyRepeatSpeed.getValue()).intValue();
		return true;
	}
	
}