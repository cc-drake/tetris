package de.drake.tetris;

import de.drake.tetris.config.Config;

/**
 * Der GameController initialisiert das Spiel und löst regelmäßig die Methoden "tick()" und "render()" aus.
 */
class GameLoop extends Thread {
	
	private final Game game;
	
	GameLoop(final Game game) {
		this.game = game;
	}
	
	/**
	 * Wird beim Start des Threads ausgeführt.
	 */
	public void run() {
		
		long timePerTick = 1000000000L / Config.fps;
		long lastTick = System.nanoTime();
		while(true) {
			if ((System.nanoTime() - lastTick) >= timePerTick) {
				lastTick += timePerTick;
				game.tick();
				game.render();
			}
		}
		
	}
	
}