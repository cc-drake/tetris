package de.drake.tetris.controller.states;

import javax.swing.JComponent;

public abstract class State {
	
	/**
	 * Wird in regelmäßigen Abständen durch den GameLoop getriggert.
	 */
	public abstract void tick();
	
	/**
	 * Gibt den zu diesem State gehörigen Screen zurück.
	 */
	public abstract JComponent getScreen();
	
}