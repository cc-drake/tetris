package de.drake.tetris.controller;

import de.drake.tetris.controller.states.StateManager;
import de.drake.tetris.log.Logger;

/**
 * Der GameLoop löst regelmäßig die Methode "tick()" des aktuellen States aus.
 */
public class GameLoop implements Runnable {
	
	@Override
	public void run() {
		while(true) {
			StateManager.tickCurrentState();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				Logger.write("Fehler im GameLoop: sleep nicht möglich");
				Logger.writeThrowable(e);
			}
		}
	}
	
}