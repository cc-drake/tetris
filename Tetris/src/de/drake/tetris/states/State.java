package de.drake.tetris.states;

import javax.swing.JPanel;

public abstract class State {
	
	public final static State startState = new StartState();
	public final static State modeState = new ModeState();
	
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
	public abstract JPanel getScreen();
	
}