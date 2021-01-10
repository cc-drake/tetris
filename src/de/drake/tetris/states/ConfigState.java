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
	
	public final static String reset = "Default-Einstellungen wiederherstellen";
	
	public final static String save = "Einstellungen speichern (Tetris.ini)";
	
	public final static String back = "Zur�ck";
	
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
		if (!this.screen.applyConfig())
			return;
		switch (e.getActionCommand()) {
		case ConfigState.back:
			State.setCurrentState(new ModeState());
			return;
		case ConfigState.save:
			try {
				IniInOut.saveIni();
			} catch (Exception e1) {
			}
			return;
		case ConfigState.reset:
			IniInOut.setConfig(null);
			State.setCurrentState(new ConfigState());
			return;
		}
	}
}