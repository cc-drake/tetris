package de.drake.tetris.config;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import de.drake.tetris.input.InputDevice;
import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.Key;
import de.drake.tetris.util.Action;

public class PlayerTemplate {
	
	public final static ArrayList<PlayerTemplate> playerTemplates = createPlayerTemplates();
	
	private static ArrayList<PlayerTemplate> createPlayerTemplates() {
		InputDevice.removeInputManagers();
		ArrayList<PlayerTemplate> result = new ArrayList<PlayerTemplate>();
		result.add(new PlayerTemplate("Keyboard", new InputManager(InputDevice.keyboard, createKeyboard()), 2.));
		result.add(new PlayerTemplate("Maus", new InputManager(InputDevice.mouse, createMouse()), 2.));
		if (InputDevice.gamepads.size() > 0)
			result.add(new PlayerTemplate("Gamepad", new InputManager(InputDevice.gamepads.get(0), createGamepad()), 2.));
		return result;
	}
	
	private static HashMap<Key, Action> createKeyboard() {
		HashMap<Key, Action> result = new HashMap<Key, Action>();
		result.put(new Key(KeyEvent.VK_ESCAPE, "Escape"), Action.QUIT);
		result.put(new Key(KeyEvent.VK_ENTER, "Enter"), Action.PAUSE);
		result.put(new Key(KeyEvent.VK_A, "A"), Action.LINKS);
		result.put(new Key(KeyEvent.VK_D, "D"), Action.RECHTS);
		result.put(new Key(KeyEvent.VK_S, "S"), Action.RUNTER);
		result.put(new Key(KeyEvent.VK_SPACE, "Space"), Action.GANZ_RUNTER);
		result.put(new Key(KeyEvent.VK_NUMPAD5, "NUM-4"), Action.DREHUNG_UHRZEIGERSINN);
		result.put(new Key(KeyEvent.VK_NUMPAD4, "NUM-4"), Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		return result;
	}
	
	private static HashMap<Key, Action> createMouse() {
		HashMap<Key, Action> result = new HashMap<Key, Action>();
		result.put(new Key(MouseEvent.BUTTON1, "LMB"), Action.LINKS);
		result.put(new Key(MouseEvent.BUTTON3, "RMB"), Action.RECHTS);
		result.put(new Key(MouseEvent.BUTTON2, "MMB"), Action.GANZ_RUNTER);
		result.put(new Key(-1, "Scroll-D"), Action.DREHUNG_UHRZEIGERSINN);
		result.put(new Key(0, "Scroll-U"), Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		return result;
	}
	
	private static HashMap<Key, Action> createGamepad() {
		HashMap<Key, Action> result = new HashMap<Key, Action>();
		result.put(new Key(KeyEvent.VK_6, "6"), Action.QUIT);
		result.put(new Key(KeyEvent.VK_7, "7"), Action.PAUSE);
		result.put(new Key(KeyEvent.VK_LEFT, "Links"), Action.LINKS);
		result.put(new Key(KeyEvent.VK_RIGHT, "Rechts"), Action.RECHTS);
		result.put(new Key(KeyEvent.VK_DOWN, "Unten"), Action.RUNTER);
		result.put(new Key(KeyEvent.VK_4, "4"), Action.GANZ_RUNTER);
		result.put(new Key(KeyEvent.VK_5, "5"), Action.GANZ_RUNTER);
		result.put(new Key(KeyEvent.VK_1, "0"), Action.DREHUNG_UHRZEIGERSINN);
		result.put(new Key(KeyEvent.VK_0, "1"), Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		return result;
	}

	private final String name;
	
	private final InputManager inputManager;
	
	private final double initialSpeed;
	
	private PlayerTemplate(final String name, final InputManager inputManager,
			final double initialSpeed) {
		this.name = name;
		this.inputManager = inputManager;
		this.initialSpeed = initialSpeed;
	}
	
	public String getName() {
		return name;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public double getInitialSpeed() {
		return initialSpeed;
	}
	
}