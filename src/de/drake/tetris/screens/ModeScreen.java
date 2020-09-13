package de.drake.tetris.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import de.drake.tetris.config.Config;
import de.drake.tetris.screens.comp.ComponentFactory;
import de.drake.tetris.screens.comp.ListSpinner;
import de.drake.tetris.screens.comp.OptionTable;
import de.drake.tetris.screens.comp.NumberSpinner;
import de.drake.tetris.screens.comp.TimeSpinner;
import de.drake.tetris.states.ModeState;
import de.drake.tetris.util.GameMode;

public class ModeScreen extends JScrollPane {
	
	private static int optionSize = 15;
	
	private static Color optionColor = Color.white;
	
	private static Color optionBgColor = Color.darkGray;
	
	private final JSpinner timeLimit_sol;
	
	private final JSpinner timeLimit_com;
	
	private final JSpinner timeLimit_race;
	
	private final JSpinner timeLimit_che;
	
	private final JSpinner speedIncreaseRow;
	
	private final JSpinner speedIncreaseSec;
	
	private final JSpinner raceRows;
	
	private final JSpinner cheeseRows;
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public ModeScreen(final ActionListener listener) {
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			JPanel topPanel = new JPanel();
			topPanel.setBackground(Color.black);
			topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 5, 0, 5);
			c.fill = GridBagConstraints.BOTH;
			contentPanel.add(topPanel, BorderLayout.CENTER);
				
				//In der obersten Zeile Struts einfügen, die eine einheitliche Mindestbreite der Spalten sicherstellen
				c.gridy = 0;
				
				c.gridx = 0;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 1;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 2;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 3;
				topPanel.add(Box.createHorizontalStrut(280), c);
				
				//Erste Inhalts-Spalte - Solitär
				c.gridx = 0;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Solitär", Color.green, ModeScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_sol = new OptionTable(
						ModeScreen.optionColor, ModeScreen.optionBgColor, ModeScreen.optionSize);
				topPanel.add(options_sol, c);
				
					this.timeLimit_sol = new TimeSpinner(Config.timeLimit);
					options_sol.addOption("Zeitlimit (mm:ss)", this.timeLimit_sol);
					
					this.speedIncreaseRow = new NumberSpinner(
							Config.speedIncreaseRow, 0., 100., 0.1);
					options_sol.addOption("Beschleunigung je Reihe (%)", this.speedIncreaseRow);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.SOLITAER.toString(), listener), c);
				
				//Zweite Inhalts-Spalte - Combat
				c.gridx = 1;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Combat", Color.red, ModeScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_com = new OptionTable(
						ModeScreen.optionColor, ModeScreen.optionBgColor, ModeScreen.optionSize);
				topPanel.add(options_com, c);
				
					this.timeLimit_com = new TimeSpinner(Config.timeLimit);
					options_com.addOption("Zeitlimit (mm:ss)", this.timeLimit_com);
					
					this.speedIncreaseSec = new NumberSpinner(
							Config.speedIncreaseSec, 0., 10., 0.1);
					options_com.addOption("Beschleunigung je Sekunde (%)", this.speedIncreaseSec);
					
					String[] values = {"Classic", "Evil", "Peaceful"};
					ListSpinner combatType = new ListSpinner(
							values);
					options_com.addOption("Battlemode", combatType);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.COMBAT.toString(), listener), c);
				
				//Dritte Inhalts-Spalte - Race
				c.gridx = 2;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Race", Color.blue, ModeScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_race = new OptionTable(
						ModeScreen.optionColor, ModeScreen.optionBgColor, ModeScreen.optionSize);
				topPanel.add(options_race, c);
				
					this.timeLimit_race = new TimeSpinner(Config.timeLimit);
					options_race.addOption("Zeitlimit (mm:ss)", this.timeLimit_race);
					
					this.raceRows = new NumberSpinner(
							Config.raceRows, 1, 9999, 1);
					options_race.addOption("Reihen", this.raceRows);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.RACE.toString(), listener), c);
				
				//Vierte Inhalts-Spalte - Cheese
				c.gridx = 3;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Cheese", Color.yellow, ModeScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_che = new OptionTable(
						ModeScreen.optionColor, ModeScreen.optionBgColor, ModeScreen.optionSize);
				topPanel.add(options_che, c);
				
					this.timeLimit_che = new TimeSpinner(Config.timeLimit);
					options_che.addOption("Zeitlimit (mm:ss)", this.timeLimit_che);
					
					this.cheeseRows = new NumberSpinner(
							Config.cheeseRows, 1, Config.hoehe - Config.getMaxSteinSize(), 1);
					options_che.addOption("Reihen", this.cheeseRows);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.CHEESE.toString(), listener), c);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Color.black);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ModeState.back, listener));
				
	}
	
	public int getTimeLimit(final GameMode mode) {
		Date value = null;
		if (mode == GameMode.SOLITAER)
			value = (Date) this.timeLimit_sol.getValue();
		if (mode == GameMode.COMBAT)
			value = (Date) this.timeLimit_com.getValue();
		if (mode == GameMode.RACE)
			value = (Date) this.timeLimit_race.getValue();
		if (mode == GameMode.CHEESE)
			value = (Date) this.timeLimit_che.getValue();
		Calendar date = Calendar.getInstance();
		date.setTime(value);
		return date.get(Calendar.MINUTE) * 60 + date.get(Calendar.SECOND);
	}

	public double getSpeedIncreaseRow() {
		return (double) this.speedIncreaseRow.getValue();
	}

	public double getSpeedIncreaseSec() {
		return (double) this.speedIncreaseSec.getValue();
	}

	public int getRaceRows() {
		return (int) (double) this.raceRows.getValue();
	}
	
	public int getCheeseRows() {
		return (int) (double) this.cheeseRows.getValue();
	}
	
}
