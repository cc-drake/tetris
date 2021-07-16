package de.drake.tetris.controller;

import de.drake.tetris.controller.states.StateManager;
import de.drake.tetris.log.Logger;

/**
 * Der GameLoop l�st regelm��ig die Methode "tick()" des aktuellen States aus.
 */
public class GameLoop implements Runnable {
	
	@Override
	public void run() {
		while(true) {
			StateManager.tickCurrentState();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				Logger.write("Fehler im GameLoop: sleep nicht m�glich");
				Logger.writeThrowable(e);
			}
		}
	}
	
}