package de.drake.tetris.input.gamepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GamepadKey {
	
	private int keyCode;
	
	private char keyChar;
	
	private boolean ispressed;
	
	private KeyListener listener;

	GamepadKey(final int keyCode, final char keyChar) {
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.ispressed = false;
	}
	
	void setListener(final KeyListener listener) {
		this.listener = listener;
	}

	void setPressed(boolean pressed) {
		if(this.ispressed == pressed)
			return;
		this.ispressed = pressed;
		if (pressed = true) {
			this.listener.keyPressed(
					new KeyEvent(null, 0, System.nanoTime(), 0, this.keyCode, this.keyChar));
		} else {
			this.listener.keyReleased(
					new KeyEvent(null, 0, System.nanoTime(), 0, this.keyCode, this.keyChar));
		}
	}
}
