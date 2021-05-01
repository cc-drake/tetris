package de.drake.tetris.controller.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.ToolTipManager;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.view.screens.ModeScreen;

/**
 * Der ModeState erlaubt die Auswahl des Spielmodus
 */
public class ModeState extends State implements ActionListener {
	
	public final static String BACK = "Zurück";
	public final static String CONFIG = "Erweiterte Optionen";
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final ModeScreen screen = new ModeScreen(this);
	
	@Override
	public void tick() {
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolTipManager.sharedInstance().mouseExited(new MouseEvent(this.screen, 0, 0, 0, 0, 0, 0, true));
		this.screen.applyConfig();
		switch (e.getActionCommand()) {
		case ModeState.BACK:
			StateManager.setCurrentState(new StartState());
			return;
		case ModeState.CONFIG:
			StateManager.setCurrentState(new ConfigState());
			return;
		case GameMode.SOLITAER:
		case GameMode.COMBAT:
		case GameMode.RACE:
		case GameMode.CHEESE:
			GameMode.createInstance(e.getActionCommand());
			StateManager.setCurrentState(new PlayerState());
			return;
		}
	}
}