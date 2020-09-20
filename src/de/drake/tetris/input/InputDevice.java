package de.drake.tetris.input;

import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Controller.Type;

public abstract class InputDevice implements FocusListener {
	
	public final static ArrayList<Gamepad> gamepads = new ArrayList<Gamepad>();
	
	public final static Mouse mouse = new Mouse();
	
	public final static Keyboard keyboard = new Keyboard();
	
	private final HashSet<KeyListener> listeners = new HashSet<KeyListener>();
	
	public void addKeyListener(final KeyListener listener) {
		this.listeners.add(listener);
	}
	
	protected void keyPressed(final Key key) {
		for (KeyListener listener : this.listeners) {
			listener.keyPressed(key);
		}
	}
	
	protected void keyReleased(final Key key) {
		for (KeyListener listener : this.listeners) {
			listener.keyReleased(key);
		}
	}
	
	public static void init() {
		for (Controller controller : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
			if (controller.getType().equals(Type.GAMEPAD)) {
				Gamepad gamepad = new Gamepad(controller);
				InputDevice.gamepads.add(gamepad);
			}
		}
	}
	
	public static void removeInputManagers() {
		InputDevice mouse = InputDevice.mouse;
		mouse.listeners.clear();
		
		InputDevice keyboard = InputDevice.keyboard;
		keyboard.listeners.clear();
		
		for (InputDevice gamepad : InputDevice.gamepads) {
			gamepad.listeners.clear();
		}
	}
	
}