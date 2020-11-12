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
import javax.swing.JSpinner;
import javax.swing.ToolTipManager;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.screens.comp.ComponentFactory;
import de.drake.tetris.screens.comp.ListSpinner;
import de.drake.tetris.screens.comp.OptionTable;
import de.drake.tetris.screens.comp.NumberSpinner;
import de.drake.tetris.screens.comp.TimeSpinner;
import de.drake.tetris.states.ModeState;

public class ModeScreen extends JScrollPane {
	
	private final JSpinner timeLimit_com;
	
	private final JSpinner timeLimit_race;
	
	private final JSpinner timeLimit_che;
	
	private final JSpinner speedIncreaseRow;
	
	private final JSpinner speedIncreaseSec;
	
	private final JSpinner raceRows;
	
	private final JSpinner cheeseRows;
	
	private final JSpinner combatType;
	
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
			topPanel.setBackground(Config.bgColor);
			topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 5, 0, 5);
			c.fill = GridBagConstraints.BOTH;
			contentPanel.add(topPanel, BorderLayout.CENTER);
				
				//In der obersten Zeile Struts einf�gen, die eine einheitliche Mindestbreite der Spalten sicherstellen
				c.gridy = 0;
				
				c.gridx = 0;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 1;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 2;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 3;
				topPanel.add(Box.createHorizontalStrut(280), c);
				
				//Erste Inhalts-Spalte - Solit�r
				c.gridx = 0;
				
				c.gridy = 1;
				JLabel sol_label = ComponentFactory.createLabel(GameMode.SOLITAER, Color.green);
				sol_label.setToolTipText("<html>"
						+ "Im Solit�r-Modus gibt es keinerlei Zeitdruck - jeder Spieler<br>"
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
							GameMode.speedIncreaseRow, 0., 100., 0.1);
					options_sol.addOption("Beschleunigung je Reihe (%)", this.speedIncreaseRow);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"W�hlen", GameMode.SOLITAER, listener), c);
				
				//Zweite Inhalts-Spalte - Combat
				c.gridx = 1;
				
				c.gridy = 1;
				JLabel com_label = ComponentFactory.createLabel(GameMode.COMBAT, Color.red);
				com_label.setToolTipText("<html>"
						+ "Im Combat-Modus geht es darum, so lange wie m�glich zu �berleben.<br>"
						+ "Der letzte verbleibende Spieler gewinnt das Spiel.<br><br>"
						+ "Je nach Battlemode ist es m�glich, seine Mitspieler zu sabotieren:<br>"
						+ "- Classic: Bei zwei gleichzeitig eliminierten Reihen erhalten<br>"
						+ "alle Mitspieler eine zus�tzliche Reihe. Bei drei Reihen gibt es zwei<br>"
						+ "zus�tzliche Reihen, bei vier Reihen sogar vier zus�tzliche Reihen.<br>"
						+ "- Badass: Jede eliminierte Reihe wird den Mitspielern als zus�tzliche<br>"
						+ "Reihe aufgeb�rdet.<br>"
						+ "- Peace: Eine Sabotage der Mitspieler ist nicht m�glich."
						+ "</html>");
				topPanel.add(com_label, c);
				
				c.gridy = 2;
				OptionTable options_com = new OptionTable();
				topPanel.add(options_com, c);
				
					this.timeLimit_com = new TimeSpinner(GameMode.timeLimit);
					options_com.addOption("Zeitlimit (mm:ss)", this.timeLimit_com);
					
					this.speedIncreaseSec = new NumberSpinner(
							GameMode.speedIncreaseSec, 0., 10., 0.1);
					options_com.addOption("Beschleunigung je Sekunde (%)", this.speedIncreaseSec);
					
					String[] values = {GameMode.COMBAT_CLASSIC, GameMode.COMBAT_BADASS, GameMode.COMBAT_PEACE};
					this.combatType = new ListSpinner(values, 5);
					options_com.addOption("Battlemode", combatType);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"W�hlen", GameMode.COMBAT, listener), c);
				
				//Dritte Inhalts-Spalte - Race
				c.gridx = 2;
				
				c.gridy = 1;
				JLabel race_label = ComponentFactory.createLabel(GameMode.RACE, Color.blue);
				race_label.setToolTipText("<html>"
						+ "Im Race-Modus geht es darum, so schnell wie m�glich eine bestimmte<br>"
						+ "Anzahl von Reihen zu eliminieren.<br>"
						+ "Der erste Spieler, der die ben�tigte Anzahl von Reihen eliminiert hat,<br>"
						+ "gewinnt das Spiel."
						+ "</html>");
				topPanel.add(race_label, c);
				
				c.gridy = 2;
				OptionTable options_race = new OptionTable();
				topPanel.add(options_race, c);
				
					this.timeLimit_race = new TimeSpinner(GameMode.timeLimit);
					options_race.addOption("Zeitlimit (mm:ss)", this.timeLimit_race);
					
					this.raceRows = new NumberSpinner(
							GameMode.raceRows, 1, 9999, 1);
					options_race.addOption("Reihen", this.raceRows);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"W�hlen", GameMode.RACE, listener), c);
				
				//Vierte Inhalts-Spalte - Cheese
				c.gridx = 3;
				
				c.gridy = 1;
				JLabel che_label = ComponentFactory.createLabel(GameMode.CHEESE, Color.yellow);
				che_label.setToolTipText("<html>"
						+ "Im Cheese-Modus sind zu Spielbeginn bereits einige vorgefertigte<br>"
						+ "\"K�sereihen\" vorhanden, die so schnell wie m�glich eliminiert<br>"
						+ "werden m�ssen.<br>"
						+ "Der erste Spieler, der s�mtliche K�sereihen eliminiert hat,<br>"
						+ "gewinnt das Spiel."
						+ "</html>");
				topPanel.add(che_label, c);
				
				c.gridy = 2;
				OptionTable options_che = new OptionTable();
				topPanel.add(options_che, c);
				
					this.timeLimit_che = new TimeSpinner(GameMode.timeLimit);
					options_che.addOption("Zeitlimit (mm:ss)", this.timeLimit_che);
					
					this.cheeseRows = new NumberSpinner(
							GameMode.cheeseRows, 1, Config.hoehe - Config.getMaxSteinSize(), 1);
					options_che.addOption("Reihen", this.cheeseRows);
					
				c.gridy = 3;
				topPanel.add(ComponentFactory.createButton(
						"W�hlen", GameMode.CHEESE, listener), c);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Config.bgColor);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ModeState.back, listener));
				
	}
	
	public int getTimeLimit(final String gameMode) {
		Date value = null;
		if (gameMode == GameMode.SOLITAER)
			return 0;
		if (gameMode == GameMode.COMBAT)
			value = (Date) this.timeLimit_com.getValue();
		if (gameMode == GameMode.RACE)
			value = (Date) this.timeLimit_race.getValue();
		if (gameMode == GameMode.CHEESE)
			value = (Date) this.timeLimit_che.getValue();
		Calendar date = Calendar.getInstance();
		date.setTime(value);
		return date.get(Calendar.MINUTE) * 60 + date.get(Calendar.SECOND);
	}
	
	public String getCombatType() {
		return (String) this.combatType.getValue();
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