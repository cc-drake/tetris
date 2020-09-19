package de.drake.tetris.input.gamepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.drake.tetris.screens.Display;

class GamepadKey {
	
	private final Display display;
	
	private final int keyCode;
	
	private final char keyChar;
	
	private boolean ispressed;
	
	private KeyListener listener;

	GamepadKey(final Display display, final int keyCode, final char keyChar) {
		this.display = display;
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.ispressed = false;
	}
	
	void setListener(final KeyListener listener) {
		this.listener = listener;
	}

	void setPressed(final boolean pressed) {
		if(this.ispressed == pressed)
			return;
		this.ispressed = pressed;
		if (pressed == true) {
			this.listener.keyPressed(
					new KeyEvent(this.display, 0, System.nanoTime(), 0, this.keyCode, this.keyChar));
		} else {
			this.listener.keyReleased(
					new KeyEvent(this.display, 0, System.nanoTime(), 0, this.keyCode, this.keyChar));
		}
	}
}
