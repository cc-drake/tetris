package de.drake.tetris.input;

import de.drake.tetris.data.Action;

public interface InputManager {
	
	public static int KEYBOARD = 1;
	
	public static int AI = 2;
	
	public abstract Action getNextAction(final int currentState);
	
}
