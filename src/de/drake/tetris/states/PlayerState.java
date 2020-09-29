package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.config.PlayerTemplate;
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
	
	/**
	 * Erstellt einen neuen PlayerState.
	 */
	PlayerState() {
		this.screen = new PlayerScreen(this);
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
		if (e.getActionCommand() == PlayerState.back) {
			State.setCurrentState(State.modeState);
			return;
		}
		if (e.getActionCommand() == PlayerState.start) {
			State.setCurrentState(new GameState(PlayerTemplate.playerTemplates));
		}
	}
}