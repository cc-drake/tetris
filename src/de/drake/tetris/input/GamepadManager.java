package de.drake.tetris.input;

import javax.swing.JComponent;

import de.drake.tetris.config.KeyBinding;
import de.drake.tetris.input.gamepad.Gamepad;

public class GamepadManager extends InputManager {

	public GamepadManager(final Gamepad gamepad, KeyBinding keyBinding) {
		super(keyBinding);
		gamepad.setKeyListener(this);
	}
	
	@Override
	public void setScreen(final JComponent screen) {
		screen.addFocusListener(this);
	}
	
}