package de.drake.tetris.config;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Vector;

import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.Key;
import de.drake.tetris.util.Action;

public class Player {
	
	public static Vector<Player> players;
	
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

	private String name;
	
	private InputManager inputManager;
	
	private double initialSpeed;
	
	public Player(final String name) {
		this.name = name;
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

	public void setName(final String name) {
		this.name = name;
	}
	
	public void setInputManager(final InputManager manager) {
		this.inputManager = manager;
	}

	public void setInitialSpeed(final double speed) {
		this.initialSpeed = speed;
	}
}