package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.config.PlayerTemplate;
import de.drake.tetris.screens.ModeScreen;

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
			GameMode.timeLimit = 0;
			GameMode.speedIncreaseRow = this.screen.getSpeedIncreaseRow();
			GameMode.speedIncreaseSec = 0.;
			GameMode.raceRows = 0;
			GameMode.cheeseRows = 0;
			GameMode.gameMode = GameMode.SOLITAER;
			GameMode.combatType = GameMode.COMBAT_PEACE;
		}
		if (e.getActionCommand() == GameMode.COMBAT.toString()) {
			GameMode.timeLimit = this.screen.getTimeLimit(GameMode.COMBAT);
			GameMode.speedIncreaseRow = 0.;
			GameMode.speedIncreaseSec = this.screen.getSpeedIncreaseSec();
			GameMode.raceRows = 0;
			GameMode.cheeseRows = 0;
			GameMode.gameMode = GameMode.COMBAT;
			GameMode.combatType = this.screen.getCombatType();
		}
		if (e.getActionCommand() == GameMode.RACE.toString()) {
			GameMode.timeLimit = this.screen.getTimeLimit(GameMode.RACE);
			GameMode.speedIncreaseRow = 0.;
			GameMode.speedIncreaseSec = 0.;
			GameMode.raceRows = this.screen.getRaceRows();
			GameMode.cheeseRows = 0;
			GameMode.gameMode = GameMode.RACE;
			GameMode.combatType = GameMode.COMBAT_PEACE;
		}
		if (e.getActionCommand() == GameMode.CHEESE.toString()) {
			GameMode.timeLimit = this.screen.getTimeLimit(GameMode.CHEESE);
			GameMode.speedIncreaseRow = 0.;
			GameMode.speedIncreaseSec = 0.;
			GameMode.raceRows = 0;
			GameMode.cheeseRows = this.screen.getCheeseRows();
			GameMode.gameMode = GameMode.CHEESE;
			GameMode.combatType = GameMode.COMBAT_PEACE;
		}
		State.setCurrentState(new GameState(PlayerTemplate.playerTemplates));
	}
}