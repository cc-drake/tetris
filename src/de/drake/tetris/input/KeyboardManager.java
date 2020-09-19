package de.drake.tetris.input;

import javax.swing.JComponent;

import de.drake.tetris.config.KeyBinding;

public class KeyboardManager extends InputManager {

	public KeyboardManager(final KeyBinding keyBinding) {
		super(keyBinding);
	}

	@Override
	public void setScreen(final JComponent screen) {
		screen.addKeyListener(this);
		screen.addFocusListener(this);
	}

}