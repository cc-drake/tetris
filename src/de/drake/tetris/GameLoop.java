package de.drake.tetris;

/**
 * Der GameController initialisiert das Spiel und löst regelmäßig die Methoden "tick()" und "render()" aus.
 */
public class GameLoop extends Thread {
	
	/**
	 * Framerate in Aktionen pro Spieler pro Sekunde.
	 */
	public final static int fps = 100;
	
	private final Game game;
	
	GameLoop(final Game game) {
		this.game = game;
	}
	
	/**
	 * Wird beim Start des Threads ausgeführt.
	 */
	public void run() {
		
		long timePerTick = 1000000000L / GameLoop.fps;
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