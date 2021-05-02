package de.drake.tetris.controller.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.view.screens.PlayerScreen;

/**
 * Der ModeState erlaubt die Auswahl des Spielmodus
 */
public class PlayerState extends State implements ActionListener {
	
	public final static String BACK = "Zurück";
	public final static String START = "Spiel starten";
	public final static String ADD_PLAYER = "Spieler hinzufügen";
	public final static String REMOVE_PLAYER = "Spieler entfernen";
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final PlayerScreen screen = new PlayerScreen(this);
	
	@Override
	public void tick() {
		this.screen.tick();
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InputDevice.removeInputManagers();
		switch (e.getActionCommand()) {
		case PlayerState.BACK:
			this.screen.applyPlayerConfigs();
			StateManager.setCurrentState(new ModeState());
			return;
		case PlayerState.START:
			this.screen.applyPlayerConfigs();
			if (PlayerConfig.playerConfigs.size() > 0)
				StateManager.setCurrentState(new GameState());
			return;
		case PlayerState.ADD_PLAYER:
			this.screen.addPlayer();
			return;
		case PlayerState.REMOVE_PLAYER:
			this.screen.removeSelectedPlayer();
			return;
		}
	}
}