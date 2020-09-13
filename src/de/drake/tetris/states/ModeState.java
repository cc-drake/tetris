package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.config.Config;
import de.drake.tetris.screens.ModeScreen;
import de.drake.tetris.util.GameMode;

/**
 * Der ModeState erlaubt die Auswahl des Spielmodus
 */
public class ModeState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final ModeScreen screen;
	
	public final static String back = "Zurück";
	
	/**
	 * Erstellt einen neuen ModeState.
	 */
	ModeState() {
		this.screen = new ModeScreen(this);
	}
	
	@Override
	public void tick() {
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == ModeState.back) {
			State.setCurrentState(State.startState);
		}
		if (e.getActionCommand() == GameMode.SOLITAER.toString()) {
			Config.timeLimit = this.screen.getTimeLimit(GameMode.SOLITAER);
			Config.speedIncreaseRow = this.screen.getSpeedIncreaseRow();
			Config.speedIncreaseSec = 0.;
			Config.raceRows = 0;
			Config.cheeseRows = 0;
			Config.gameMode = GameMode.SOLITAER;
		}
		if (e.getActionCommand() == GameMode.COMBAT.toString()) {
			Config.timeLimit = this.screen.getTimeLimit(GameMode.COMBAT);
			Config.speedIncreaseRow = 0.;
			Config.speedIncreaseSec = this.screen.getSpeedIncreaseSec();
			Config.raceRows = 0;
			Config.cheeseRows = 0;
			Config.gameMode = GameMode.COMBAT;
		}
		if (e.getActionCommand() == GameMode.RACE.toString()) {
			Config.timeLimit = this.screen.getTimeLimit(GameMode.RACE);
			Config.speedIncreaseRow = 0.;
			Config.speedIncreaseSec = 0.;
			Config.raceRows = this.screen.getRaceRows();
			Config.cheeseRows = 0;
			Config.gameMode = GameMode.RACE;
		}
		if (e.getActionCommand() == GameMode.CHEESE.toString()) {
			Config.timeLimit = this.screen.getTimeLimit(GameMode.CHEESE);
			Config.speedIncreaseRow = 0.;
			Config.speedIncreaseSec = 0.;
			Config.raceRows = 0;
			Config.cheeseRows = this.screen.getCheeseRows();
			Config.gameMode = GameMode.CHEESE;
		}
		State.setCurrentState(new GameState());
	}
}