package de.drake.tetris.config;

import java.util.ArrayList;

import de.drake.tetris.input.GamepadManager;
import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.KeyboardManager;
import de.drake.tetris.input.gamepad.Gamepad;

public class PlayerTemplate {
	
	public static ArrayList<PlayerTemplate> createPlayerTemplates() {
		ArrayList<PlayerTemplate> result = new ArrayList<PlayerTemplate>();
		result.add(new PlayerTemplate("Player", new KeyboardManager(KeyBinding.createKeyBindingP1()), 2.));
		result.add(new PlayerTemplate("Player GP", new GamepadManager(Gamepad.gamepads.get(0), KeyBinding.createKeyBindingGamePad()), 2.));
//		result.add(new PlayerTemplate("Player 1", new KeyboardManager(KeyBinding.createKeyBindingP1P1()), 2.));
//		result.add(new PlayerTemplate("Player 2", new KeyboardManager(KeyBinding.createKeyBindingP1P2()), 2.));
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