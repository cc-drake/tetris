package de.drake.tetris.config;

import java.util.HashMap;
import java.util.Vector;

import de.drake.tetris.model.util.Action;
import de.drake.tetris.view.input.InputDevice;
import de.drake.tetris.view.input.util.InputManager;
import de.drake.tetris.view.input.util.Key;

public class PlayerConfig {
	
	public static Vector<PlayerConfig> playerConfigs;

	private String name;
	
	private double initialSpeed;
	
	private InputDevice inputDevice;
	
	private Key left, right, down, drop, dreh_uzs, dreh_euzs, pause, quit;
	
	public PlayerConfig(final String name, final InputDevice inputDevice) {
		this.name = name;
		this.initialSpeed = Config.initialSpeed;
		this.inputDevice = inputDevice;
		switch (inputDevice.getType()) {
		case InputDevice.KEYBOARD:
			this.left = Config.keyboard_left;
			this.right = Config.keyboard_right;
			this.down = Config.keyboard_down;
			this.drop = Config.keyboard_drop;
			this.dreh_uzs = Config.keyboard_dreh_uzs;
			this.dreh_euzs = Config.keyboard_dreh_euzs;
			this.pause = Config.keyboard_pause;
			this.quit = Config.keyboard_quit;
			break;
		case InputDevice.MOUSE:
			this.left = Config.mouse_left;
			this.right = Config.mouse_right;
			this.down = Config.mouse_down;
			this.drop = Config.mouse_drop;
			this.dreh_uzs = Config.mouse_dreh_uzs;
			this.dreh_euzs = Config.mouse_dreh_euzs;
			this.pause = Config.mouse_pause;
			this.quit = Config.mouse_quit;
			break;
		case InputDevice.GAMEPAD:
			this.left = Config.gamepad_left;
			this.right = Config.gamepad_right;
			this.down = Config.gamepad_down;
			this.drop = Config.gamepad_drop;
			this.dreh_uzs = Config.gamepad_dreh_uzs;
			this.dreh_euzs = Config.gamepad_dreh_euzs;
			this.pause = Config.gamepad_pause;
			this.quit = Config.gamepad_quit;
			break;
		}
	}

	public String getName() {
		return name;
	}

	public double getInitialSpeed() {
		return initialSpeed;
	}

	public InputDevice getInputDevice() {
		return inputDevice;
	}

	public Key getLeft() {
		return this.left;
	}

	public Key getRight() {
		return this.right;
	}

	public Key getDown() {
		return this.down;
	}

	public Key getDrop() {
		return this.drop;
	}

	public Key getDreh_uzs() {
		return this.dreh_uzs;
	}

	public Key getDreh_euzs() {
		return this.dreh_euzs;
	}

	public Key getPause() {
		return this.pause;
	}

	public Key getQuit() {
		return this.quit;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setInitialSpeed(final double initialSpeed) {
		this.initialSpeed = initialSpeed;
	}

	public void setInputDevice(final InputDevice inputDevice) {
		this.inputDevice = inputDevice;
	}

	public void setLeft(final Key left) {
		this.left = left;
	}

	public void setRight(final Key right) {
		this.right = right;
	}

	public void setDown(final Key down) {
		this.down = down;
	}

	public void setDrop(final Key drop) {
		this.drop = drop;
	}

	public void setDreh_uzs(final Key dreh_uzs) {
		this.dreh_uzs = dreh_uzs;
	}

	public void setDreh_euzs(final Key dreh_euzs) {
		this.dreh_euzs = dreh_euzs;
	}

	public void setPause(final Key pause) {
		this.pause = pause;
	}

	public void setQuit(final Key quit) {
		this.quit = quit;
	}
	
	public InputManager getInputManager() {
		HashMap<Key, Action> tastenbelegung = new HashMap<Key, Action>();
		tastenbelegung.put(this.left, Action.LINKS);
		tastenbelegung.put(this.right, Action.RECHTS);
		tastenbelegung.put(this.down, Action.RUNTER);
		tastenbelegung.put(this.drop, Action.GANZ_RUNTER);
		tastenbelegung.put(this.dreh_uzs, Action.DREHUNG_UHRZEIGERSINN);
		tastenbelegung.put(this.dreh_euzs, Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		tastenbelegung.put(this.pause, Action.PAUSE);
		tastenbelegung.put(this.quit, Action.QUIT);
		return new InputManager((InputDevice) this.inputDevice, tastenbelegung);
	}
	
}