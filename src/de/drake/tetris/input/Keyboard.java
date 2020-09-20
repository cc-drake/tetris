package de.drake.tetris.input;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Keyboard extends InputDevice implements java.awt.event.KeyListener {
	
	private final HashMap<Integer, Key> keyCode2key = new HashMap<Integer, Key>();
	
	private Key getKey(final KeyEvent e) {
		if (this.keyCode2key.containsKey(e.getKeyCode()))
			return this.keyCode2key.get(e.getKeyCode());
		Key result = new Key(e.getKeyCode(), e.getKeyCode() + " (" + e.getKeyChar() + ")");
		this.keyCode2key.put(e.getKeyCode(), result);
		return result;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(this.getKey(e));
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
	
}