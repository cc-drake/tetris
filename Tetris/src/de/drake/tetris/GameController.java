package de.drake.tetris;

import de.drake.tetris.config.Config;
import de.drake.tetris.gfx.Assets;
import de.drake.tetris.states.GameStateManager;
import de.drake.tetris.view.Display;

/**
 * Der GameController initialisiert das Spiel und l�st regelm��ig die Methoden "tick()" und "render()" aus.
 */
class GameController extends Thread {
	
	private Display display;
	
	/**
	 * Wird beim Start des Threads ausgef�hrt.
	 */
	public void run() {
		Assets.init();
		GameStateManager.setState(GameStateManager.playState);
		this.display = new Display();
		
		long timePerTick = 1000000000L / Config.fps;
		long lastTick = System.nanoTime();
		while(true) {
			if ((System.nanoTime() - lastTick) >= timePerTick) {
				lastTick += timePerTick;
				GameStateManager.getState().tick();
				this.display.render();
			}
		}
		
	}
	
}