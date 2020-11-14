package de.drake.tetris.config;

import java.util.Vector;

import de.drake.tetris.input.util.InputManager;

public class PlayerConfig {
	
	public static Vector<PlayerConfig> playerConfigs;

	private String name;
	
	private InputManager inputManager;
	
	private double initialSpeed;
	
	public PlayerConfig(final String name) {
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