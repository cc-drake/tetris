package de.drake.tetris.states;

import javax.swing.JPanel;

public abstract class GameState {
	
	public abstract void tick();
	
	/**
	 * Das Panel, in dem gezeichnet wird.
	 */
	public abstract JPanel getJPanel();
	
}