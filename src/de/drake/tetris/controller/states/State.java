package de.drake.tetris.controller.states;

import javax.swing.JComponent;

public abstract class State {
	
	/**
	 * Wird in regelm��igen Abst�nden durch den GameLoop getriggert.
	 */
	public abstract void tick();
	
	/**
	 * Gibt den zu diesem State geh�rigen Screen zur�ck.
	 */
	public abstract JComponent getScreen();
	
}