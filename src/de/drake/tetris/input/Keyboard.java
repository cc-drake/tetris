package de.drake.tetris.input;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import de.drake.tetris.input.util.Key;

public class Keyboard extends InputDevice implements java.awt.event.KeyListener {
	
	public Keyboard() {
		super(InputDevice.KEYBOARD);
	}

	private final HashMap<Integer, Key> keyCode2key = new HashMap<Integer, Key>();
	
	private Key getKey(final KeyEvent e) {
		if (this.keyCode2key.containsKey(e.getKeyCode()))
			return this.keyCode2key.get(e.getKeyCode());
		Key result = new Key(e.getKeyCode(), KeyEvent.getKeyText(e.getKeyCode()));
		this.keyCode2key.put(e.getKeyCode(), result);
		return result;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != 0)
			super.keyPressed(this.getKey(e));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() != 0)
			super.keyReleased(this.getKey(e));
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		for (Key key : this.keyCode2key.values()) {
			this.keyReleased(key);
		}
	}

	@Override
	public String toString() {
		return "Tastatur";
	}
	
}