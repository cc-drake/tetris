package de.drake.tetris.config;

import java.util.Vector;

import de.drake.tetris.input.util.InputManager;
import de.drake.tetris.input.util.Key;

public class PlayerConfig {
	
	public static Vector<PlayerConfig> playerConfigs;
	
	public static double initialSpeed = 2.;
	
	public static Key keyboard_left,
			keyboard_right,
			keyboard_down,
			keyboard_drop,
			keyboard_dreh_uzs,
			keyboard_dreh_euzs,
			keyboard_pause,
			keyboard_quit;
	
	public static Key mouse_left,
			mouse_right,
			mouse_down,
			mouse_drop,
			mouse_dreh_uzs,
			mouse_dreh_euzs,
			mouse_pause,
			mouse_quit;
	
	public static Key gamepad_left,
			gamepad_right,
			gamepad_down,
			gamepad_drop,
			gamepad_dreh_uzs,
			gamepad_dreh_euzs,
			gamepad_pause,
			gamepad_quit;

	private String name;
	
	private InputManager inputManager;
	
	private double speed;
	
	public PlayerConfig(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public double getSpeed() {
		return speed;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public void setInputManager(final InputManager manager) {
		this.inputManager = manager;
	}

	public void setSpeed(final double speed) {
		this.speed = speed;
	}
}