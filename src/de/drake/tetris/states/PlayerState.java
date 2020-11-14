package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.screens.PlayerScreen;

/**
 * Der ModeState erlaubt die Auswahl des Spielmodus
 */
public class PlayerState extends State implements ActionListener {

	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final PlayerScreen screen;
	
	public final static String back = "Zurück";
	
	public final static String start = "Spiel starten";
	
	public final static String addPlayer = "Spieler hinzufügen";
	
	public final static String removePlayer = "Spieler entfernen";
	
	/**
	 * Erstellt einen neuen PlayerState.
	 */
	PlayerState() {
		this.screen = new PlayerScreen(this);
		this.screen.addPlayer();
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
		InputDevice.removeInputManagers();
		if (e.getActionCommand() == PlayerState.back) {
			State.setCurrentState(State.modeState);
			return;
		}
		if (e.getActionCommand() == PlayerState.start) {
			this.screen.initializePlayers();
			if (PlayerConfig.playerConfigs.size() > 0)
			State.setCurrentState(new GameState());
			return;
		}
		if (e.getActionCommand() == PlayerState.addPlayer) {
			this.screen.addPlayer();
			return;
		}
		if (e.getActionCommand() == PlayerState.removePlayer) {
			this.screen.removePlayer();
			return;
		}
	}
}