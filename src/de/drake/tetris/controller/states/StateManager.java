package de.drake.tetris.controller.states;

import de.drake.tetris.view.Display;

public class StateManager {
	
	private static State currentState;
	
	private static Display display;
	
	public static void init(final Display display) {
		StateManager.display = display;
		StateManager.setCurrentState(new StartState());
	}
	
	public static void setCurrentState(final State state) {
		StateManager.currentState = state;
		StateManager.display.setScreen(state.getScreen());
	}
	
	public static void tickCurrentState() {
		StateManager.currentState.tick();
	}
	
}