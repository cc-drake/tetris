package de.drake.tetris.controller.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.config.IniInOut;
import de.drake.tetris.view.screens.ConfigScreen;

/**
 * Der ConfigState erlaubt die Einstellung der Spieloptionen
 */
public class ConfigState extends State implements ActionListener {
	
	public final static String RESET = "Default-Einstellungen wiederherstellen";
	public final static String SAVE = "Einstellungen speichern (Tetris.ini)";
	public final static String BACK = "Zurück";
	
	private final ConfigScreen screen = new ConfigScreen(this);
	
	@Override
	public void tick() {
	}
	
	@Override
	public JComponent getScreen() {
		return this.screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.screen.applyConfig())
			return;
		switch (e.getActionCommand()) {
		case ConfigState.BACK:
			StateManager.setCurrentState(new ModeState());
			return;
		case ConfigState.SAVE:
			try {
				IniInOut.saveIni();
			} catch (Exception e1) {
			}
			return;
		case ConfigState.RESET:
			IniInOut.setConfig(null);
			StateManager.setCurrentState(new ConfigState());
			return;
		}
	}
	
}