package de.drake.tetris.input;

import de.drake.tetris.data.Action;

public interface InputManager {
	
	public static int KEYBOARD = 0;
	
	public static int GAMEPAD_0 = 1;
	
	public static int GAMEPAD_1 = 2;
	
	public static int AI = 3;
	
	public abstract Action getNextAction(final int currentState);
	
}
