package de.drake.tetris.controller;

import de.drake.tetris.config.Config;
import de.drake.tetris.controller.states.StateManager;

/**
 * Der GameLoop löst regelmäßig die Methode "tick()" des aktuellen States aus.
 */
public class GameLoop implements Runnable {
	
	@Override
	public void run() {
		long timePerTick = 1000000000L / Config.FPS;
		long lastTick = System.nanoTime();
		while(true) {
			if ((System.nanoTime() - lastTick) >= timePerTick) {
				lastTick += timePerTick;
				StateManager.tickCurrentState();
			}
		}
	}
	
}