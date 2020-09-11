package de.drake.tetris.input;

import java.awt.Component;

import de.drake.tetris.config.KeyBinding;

public class KeyboardManager extends KeyManager {

	public KeyboardManager(final Component inputSource, final KeyBinding keyBinding) {
		super(keyBinding);
		inputSource.addKeyListener(this);
		inputSource.addFocusListener(this);
	}

}
