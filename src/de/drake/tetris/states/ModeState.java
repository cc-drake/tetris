package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.ToolTipManager;

import de.drake.tetris.config.GameMode;
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
	
	public final static String config = "Erweiterte Optionen";
	
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
		ToolTipManager.sharedInstance().mouseExited(new MouseEvent(this.screen, 0, 0, 0, 0, 0, 0, true));
		this.screen.applyConfig();
		switch (e.getActionCommand()) {
		case ModeState.back:
			State.setCurrentState(new StartState());
			return;
		case ModeState.config:
			State.setCurrentState(new ConfigState());
			return;
		case GameMode.SOLITAER:
		case GameMode.COMBAT:
		case GameMode.RACE:
		case GameMode.CHEESE:
			GameMode.createInstance(e.getActionCommand());
			State.setCurrentState(new PlayerState());
			return;
		}
	}
}