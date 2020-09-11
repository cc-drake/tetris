package de.drake.tetris.input;

import java.awt.Component;

import de.drake.tetris.config.KeyBinding;
import de.drake.tetris.input.gamepad.GamepadMonitor;

public class GamepadManager extends KeyManager {
	
	private GamepadMonitor monitor;

	public GamepadManager(final Component inputSource, final int gamepadNr, KeyBinding keyBinding) {
		super(keyBinding);
		this.monitor = new GamepadMonitor(inputSource, gamepadNr);
		this.monitor.setKeyListener(this);
		inputSource.addFocusListener(this);
		this.monitor.start();
	}
	
}
