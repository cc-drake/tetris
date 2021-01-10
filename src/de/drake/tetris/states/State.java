package de.drake.tetris.states;

import javax.swing.JComponent;

public abstract class State {
	
	private static State currentState;
	
	public static void setCurrentState(final State state) {
		State.currentState = state;
	}
	
	public static State getCurrentState() {
		return State.currentState;
	}
	
	public abstract void tick();
	
	/**
	 * Gibt den Screen zurück, der im Display gezeichnet werden soll.
	 */
	public abstract JComponent getScreen();
	
}