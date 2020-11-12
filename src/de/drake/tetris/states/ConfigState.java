package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.screens.ConfigScreen;

/**
 * Der ConfigState erlaubt die Einstellung der Spieloptionen
 */
public class ConfigState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final ConfigScreen screen;
	
	public final static String back = "Zurück";
	
	/**
	 * Erstellt einen neuen ConfigState.
	 */
	ConfigState() {
		this.screen = new ConfigScreen(this);
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
		if (e.getActionCommand() == ConfigState.back) {
			State.setCurrentState(State.startState);
			return;
		}
	}
}