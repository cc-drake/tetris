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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.screens.util.ComponentFactory;
import de.drake.tetris.screens.util.ListSpinner;
import de.drake.tetris.screens.util.NumberSpinner;
import de.drake.tetris.screens.util.OptionTable;
import de.drake.tetris.screens.util.TimeSpinner;
import de.drake.tetris.states.ModeState;

public class ModeScreen extends JScrollPane {
	
	private final TimeSpinner timeLimitCombat;
	
	private final TimeSpinner timeLimitRace;
	
	private final TimeSpinner timeLimitCheese;
	
	private final NumberSpinner speedIncreaseRow;
	
	private final NumberSpinner speedIncreaseSec;
	
	private final NumberSpinner raceRows;
	
	private final NumberSpinner cheeseRows;
	
	private final ListSpinner combatType;
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public ModeScreen(final ActionListener listener) {
		ToolTipManager.sharedInstance().setInitialDelay(1);
		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
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
				JLabel sol_label = ComponentFactory.createLabel(GameMode.SOLITAER, Color.green);
				sol_label.setToolTipText("<html>"
						+ "Im Solitär-Modus gibt es keinerlei Zeitdruck - jeder Spieler<br>"
						+ "kann so langsam spielen, wie er mag.<br>"
						+ "Das Spiel endet erst, wenn alle Spieler \"zugebaut\" sind.<br>"
						+ "Gewinner ist der Spieler, der vor seinem Ableben<br>"
						+ "die meisten Reihen eliminiert hat."
						+ "</html>");
				topPanel.add(sol_label, c);
				
				c.gridy = 2;
				OptionTable options_sol = new OptionTable();
				topPanel.add(options_sol, c);
					
					this.speedIncreaseRow = new NumberSpinner(
							Config.speedIncreaseRow, 0., 100., 0.1);
					options_sol.addOption("Beschleunigung je Reihe (%)", this.speedIncreaseRow);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.SOLITAER, listener), c);
				
				//Zweite Inhalts-Spalte - Combat
				c.gridx = 1;
				
				c.gridy = 1;
				JLabel com_label = ComponentFactory.createLabel(GameMode.COMBAT, Color.red);
				com_label.setToolTipText("<html>"
						+ "Im Combat-Modus geht es darum, so lange wie möglich zu überleben.<br>"
						+ "Der letzte verbleibende Spieler gewinnt das Spiel.<br><br>"
						+ "Je nach Battlemode ist es möglich, seine Mitspieler zu sabotieren:<br>"
						+ "- Classic: Bei zwei gleichzeitig eliminierten Reihen erhalten<br>"
						+ "alle Mitspieler eine zusätzliche Reihe. Bei drei Reihen gibt es zwei<br>"
						+ "zusätzliche Reihen, bei vier Reihen sogar vier zusätzliche Reihen.<br>"
						+ "- Badass: Jede eliminierte Reihe wird den Mitspielern als zusätzliche<br>"
						+ "Reihe aufgebürdet.<br>"
						+ "- Peace: Eine Sabotage der Mitspieler ist nicht möglich."
						+ "</html>");
				topPanel.add(com_label, c);
				
				c.gridy = 2;
				OptionTable options_com = new OptionTable();
				topPanel.add(options_com, c);
				
					this.timeLimitCombat = new TimeSpinner(Config.timeLimitCombat);
					options_com.addOption("Zeitlimit (mm:ss)", this.timeLimitCombat);
					
					this.speedIncreaseSec = new NumberSpinner(
							Config.speedIncreaseSec, 0., 100., 0.1);
					options_com.addOption("Beschleunigung je Sekunde (%)", this.speedIncreaseSec);
					
					String[] values = {GameMode.COMBAT_CLASSIC, GameMode.COMBAT_BADASS, GameMode.COMBAT_PEACE};
					this.combatType = new ListSpinner(values, 5, Config.combatType);
					options_com.addOption("Battlemode", combatType);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.COMBAT, listener), c);
				
				//Dritte Inhalts-Spalte - Race
				c.gridx = 2;
				
				c.gridy = 1;
				JLabel race_label = ComponentFactory.createLabel(GameMode.RACE, Color.blue);
				race_label.setToolTipText("<html>"
						+ "Im Race-Modus geht es darum, so schnell wie möglich eine bestimmte<br>"
						+ "Anzahl von Reihen zu eliminieren.<br>"
						+ "Der erste Spieler, der die benötigte Anzahl von Reihen eliminiert hat,<br>"
						+ "gewinnt das Spiel."
						+ "</html>");
				topPanel.add(race_label, c);
				
				c.gridy = 2;
				OptionTable options_race = new OptionTable();
				topPanel.add(options_race, c);
				
					this.timeLimitRace = new TimeSpinner(Config.timeLimitRace);
					options_race.addOption("Zeitlimit (mm:ss)", this.timeLimitRace);
					
					this.raceRows = new NumberSpinner(
							Config.raceRows, 1, 9999, 1);
					options_race.addOption("Reihen", this.raceRows);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.RACE, listener), c);
				
				//Vierte Inhalts-Spalte - Cheese
				c.gridx = 3;
				
				c.gridy = 1;
				JLabel che_label = ComponentFactory.createLabel(GameMode.CHEESE, Color.yellow);
				che_label.setToolTipText("<html>"
						+ "Im Cheese-Modus sind zu Spielbeginn bereits einige vorgefertigte<br>"
						+ "\"Käsereihen\" vorhanden, die so schnell wie möglich eliminiert<br>"
						+ "werden müssen.<br>"
						+ "Der erste Spieler, der sämtliche Käsereihen eliminiert hat,<br>"
						+ "gewinnt das Spiel."
						+ "</html>");
				topPanel.add(che_label, c);
				
				c.gridy = 2;
				OptionTable options_che = new OptionTable();
				topPanel.add(options_che, c);
				
					this.timeLimitCheese = new TimeSpinner(Config.timeLimitCheese);
					options_che.addOption("Zeitlimit (mm:ss)", this.timeLimitCheese);
					
					this.cheeseRows = new NumberSpinner(
							Config.cheeseRows, 1, 100, 1);
					options_che.addOption("Reihen", this.cheeseRows);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"Wählen", GameMode.CHEESE, listener), c);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Config.COLOR_BACKGROUND);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ModeState.config, listener));
				bottomPanel.add(ComponentFactory.createButton(ModeState.back, listener));
				
	}
	
	public void updateConfig() {
		Config.timeLimitCombat = this.calculateSeconds(this.timeLimitCombat.getDateValue());
		Config.timeLimitRace = this.calculateSeconds(this.timeLimitRace.getDateValue());
		Config.timeLimitCheese = this.calculateSeconds(this.timeLimitCheese.getDateValue());
		Config.speedIncreaseRow = this.speedIncreaseRow.getDoubleValue();
		Config.speedIncreaseSec = this.speedIncreaseSec.getDoubleValue();
		Config.combatType = (String) this.combatType.getValue();
		Config.raceRows = this.raceRows.getIntValue();
		Config.cheeseRows = this.cheeseRows.getIntValue();
	}
	
	private int calculateSeconds(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
	}
	
}