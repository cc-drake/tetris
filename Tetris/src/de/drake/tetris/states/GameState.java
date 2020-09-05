package de.drake.tetris.states;

import javax.swing.JPanel;

public abstract class GameState {
	
	public abstract void tick();
	
	public abstract JPanel getJPanel();
	
}