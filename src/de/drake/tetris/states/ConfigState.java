package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.config.IniInOut;
import de.drake.tetris.screens.ConfigScreen;

/**
 * Der ConfigState erlaubt die Einstellung der Spieloptionen
 */
public class ConfigState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final ConfigScreen screen;
	
	public final static String save = "In Tetris.ini speichern";
	
	public final static String reset = "Tetris.ini zurücksetzen";
	
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
			if (this.screen.applyConfig())
				State.setCurrentState(State.startState);
			return;
		}
		if (e.getActionCommand() == ConfigState.save) {
			if (this.screen.applyConfig()) {
				try {
					IniInOut.saveIni();
				} catch (Exception e1) {
				}
			}
			return;
		}
		if (e.getActionCommand() == ConfigState.reset) {
			IniInOut.setConfig(null);
			try {
				IniInOut.saveIni();
			} catch (Exception e2) {
			}
			return;
		}
	}
}