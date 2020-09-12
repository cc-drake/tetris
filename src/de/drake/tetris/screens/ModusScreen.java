package de.drake.tetris.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.drake.tetris.config.Config;
import de.drake.tetris.screens.comp.ComponentFactory;
import de.drake.tetris.screens.comp.LabeledSpinner;
import de.drake.tetris.screens.comp.NumberSpinner;
import de.drake.tetris.screens.comp.TimeSpinner;
import de.drake.tetris.states.ModeState;

public class ModusScreen extends JScrollPane {
	
	private static int optionSize = 16;
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public ModusScreen(final ActionListener listener) {
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			JPanel topPanel = new JPanel();
			topPanel.setBackground(Color.black);
			topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.ipadx = 0;//TODO
			c.ipady = 0;
			c.insets = new Insets(0, 0, 0, 0);
			contentPanel.add(topPanel, BorderLayout.CENTER);
			
				//Links und Rechts Spalten mit Glue einfügen, damit die Einstellungen mittig zentriert dargestellt werden
				c.gridy = 0;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.fill = GridBagConstraints.VERTICAL;
				c.weightx = 1.;
				c.weighty = 0.;
				
				c.gridx = 0;
				topPanel.add(Box.createGlue(), c);
				c.gridx = 5;
				topPanel.add(Box.createGlue(), c);
				
				//In der obersten Zeile Struts einfügen, die eine einheitliche Mindestbreite der Spalten sicherstellen
				c.gridy = 0;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.;
				c.weighty = 1.;
				
				c.gridx = 1;
				topPanel.add(Box.createHorizontalStrut(0), c);//TODO
				c.gridx = 2;
				topPanel.add(Box.createHorizontalStrut(0), c);
				c.gridx = 3;
				topPanel.add(Box.createHorizontalStrut(0), c);
				c.gridx = 4;
				topPanel.add(Box.createHorizontalStrut(0), c);
				
				//In der letzten Zeile Glue einfügen, damit die Einstellungen mittig zentriert dargestellt werden
				c.gridx = 0;
				c.gridwidth = 6;
				c.gridheight = 1;
				c.fill = GridBagConstraints.VERTICAL;
				c.weightx = 0.;
				c.weighty = 1.;
				
				c.gridy = 5;
				topPanel.add(Box.createGlue(), c);
				
				//Beginn des Inhalts
				c.gridwidth = 1;
				c.gridheight = 1;
				c.fill = GridBagConstraints.NONE;
				c.weightx = 0.;
				c.weighty = 0.;
				
				//Erste Inhalts-Spalte - Solitär
				c.gridx = 1;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.solitaer, Color.green, 50), c);
				c.gridy = 2;
				LabeledSpinner timelimit_sol = new LabeledSpinner(
						"Zeitlimit (mm:ss)", Color.white, ModusScreen.optionSize,
						new TimeSpinner());
				topPanel.add(timelimit_sol, c);
				c.gridy = 3;
				LabeledSpinner speedIncreaseRow = new LabeledSpinner(
						"Beschleunigung je Reihe (%)", Color.white, ModusScreen.optionSize,
						new NumberSpinner(Config.speedIncreaseRow, 0., 100., 0.1));
				topPanel.add(speedIncreaseRow, c);
				
				//Zweite Inhalts-Spalte - Combat
				c.gridx = 2;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.combat, Color.red, 50), c);
				c.gridy = 2;
				LabeledSpinner timelimit_com = new LabeledSpinner(
						"Zeitlimit (mm:ss)", Color.white, ModusScreen.optionSize,
						new TimeSpinner());
				topPanel.add(timelimit_com, c);
				c.gridy = 3;
				LabeledSpinner speedIncreaseSec = new LabeledSpinner(
						"Beschleunigung je Sekunde (%)", Color.white, ModusScreen.optionSize,
						new NumberSpinner(Config.speedIncreaseSec, 0., 10., 0.01));
				topPanel.add(speedIncreaseSec, c);
				
				//Dritte Inhalts-Spalte - Race
				c.gridx = 3;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.race, Color.blue, 50), c);
				c.gridy = 2;
				LabeledSpinner timelimit_rac = new LabeledSpinner(
						"Zeitlimit (mm:ss)", Color.white, ModusScreen.optionSize,
						new TimeSpinner());
				topPanel.add(timelimit_rac, c);
				c.gridy = 3;
				LabeledSpinner raceRows = new LabeledSpinner(
						"Reihen", Color.white, ModusScreen.optionSize,
						new NumberSpinner(Config.raceRows, 1, 9999, 1));
				topPanel.add(raceRows, c);
				
				//Vierte Inhalts-Spalte - Cheese
				c.gridx = 4;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.cheese, Color.yellow, 50), c);
				c.gridy = 2;
				LabeledSpinner timelimit_che = new LabeledSpinner(
						"Zeitlimit (mm:ss)", Color.white, ModusScreen.optionSize,
						new TimeSpinner());
				topPanel.add(timelimit_che, c);
				c.gridy = 3;
				LabeledSpinner cheeseRows = new LabeledSpinner(
						"Reihen", Color.white, ModusScreen.optionSize,
						new NumberSpinner(Config.cheeseRows, 1, Config.hoehe - Config.getMaxSteinSize(), 1));
				topPanel.add(cheeseRows, c);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Color.black);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ModeState.back, listener));
				
	}
	
}
