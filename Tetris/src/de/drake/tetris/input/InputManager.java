package de.drake.tetris.input;

import de.drake.tetris.data.Action;

public interface InputManager {
	
	public final static int KEYBOARD = 0;
	
	public final static int GAMEPAD_0 = 1;
	
	public final static int GAMEPAD_1 = 2;
	
	public final static int AI = 3;
	
	public abstract Action getNextAction(final int currentState);
	
}
