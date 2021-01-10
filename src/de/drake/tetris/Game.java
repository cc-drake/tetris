package de.drake.tetris;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.IniInOut;
import de.drake.tetris.gfx.Assets;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.screens.Display;
import de.drake.tetris.states.StartState;
import de.drake.tetris.states.State;

/**
 * Der GameController initialisiert das Spiel und l�st regelm��ig die Methoden "tick()" und "render()" aus.
 */
class Game implements Runnable {

	private final Display display;
	
	Game() {
		IniInOut.initConfig();
		Assets.init();
		InputDevice.init();
		this.display = new Display();
		State.setCurrentState(new StartState());
		Thread gameLoop = new Thread(this);
		gameLoop.start();
	}

	@Override
	public void run() {
		long timePerTick = 1000000000L / Config.FPS;
		long lastTick = System.nanoTime();
		while(true) {
			if ((System.nanoTime() - lastTick) >= timePerTick) {
				lastTick += timePerTick;
				State.getCurrentState().tick();
				this.display.render();
			}
		}
	}
	
}