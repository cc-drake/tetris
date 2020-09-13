package de.drake.tetris.config;

import java.util.ArrayList;

import de.drake.tetris.input.InputManager;

public class PlayerTemplate {
	
	public static ArrayList<PlayerTemplate> createPlayerTemplates() {
		ArrayList<PlayerTemplate> result = new ArrayList<PlayerTemplate>();
//		result.add(new PlayerTemplate("Player", InputManager.KEYBOARD, KeyBinding.createKeyBindingP1(), 2.));
		result.add(new PlayerTemplate("Player GP", InputManager.GAMEPAD_0, KeyBinding.createKeyBindingGamePad(), 2.));
		result.add(new PlayerTemplate("Player 1", InputManager.KEYBOARD, KeyBinding.createKeyBindingP1P1(), 2.));
		result.add(new PlayerTemplate("Player 2", InputManager.KEYBOARD, KeyBinding.createKeyBindingP1P2(), 2.));
		return result;
	}
	
	private final String name;
	
	private final int inputManagerType;
	
	private final KeyBinding keyBinding;
	
	private final double speed;
	
	private PlayerTemplate(final String name, final int inputManagerType,
			final KeyBinding keyBinding, final double speed) {
		this.name = name;
		this.inputManagerType = inputManagerType;
		this.keyBinding = keyBinding;
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public int getInputManagerType() {
		return inputManagerType;
	}

	public KeyBinding getKeyBinding() {
		return keyBinding;
	}

	public double getSpeed() {
		return speed;
	}

}