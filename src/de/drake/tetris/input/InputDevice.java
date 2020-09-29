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
	
	public final static ArrayList<InputDevice> allInputdevices = new ArrayList<InputDevice>();
	
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
		InputDevice.allInputdevices.add(InputDevice.keyboard);
		InputDevice.allInputdevices.add(InputDevice.mouse);
		int lfdNr = 0;
		for (Controller controller : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
			if (controller.getType().equals(Type.GAMEPAD)) {
				Gamepad gamepad = new Gamepad(controller, lfdNr);
				InputDevice.gamepads.add(gamepad);
				InputDevice.allInputdevices.add(gamepad);
				lfdNr++;
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
	
	public abstract String toString();
	
}