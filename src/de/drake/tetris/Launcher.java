package de.drake.tetris;

import java.time.LocalDateTime;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.IniInOut;
import de.drake.tetris.controller.GameLoop;
import de.drake.tetris.controller.states.StateManager;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.log.Logger;
import de.drake.tetris.view.Display;

/**
 * Startet das Tetris-Programm.
 */
class Launcher {
	
	/**
	 * Startet das Tetris-Programm.
	 * 
	 * @param args
	 * 		wird ignoriert
	 */
	public static void main(String[] args) {
		Logger.init();
		Logger.write("Tetris gestartet um " + LocalDateTime.now());
		try {
			IniInOut.initConfig();
			Asset.init();
			InputDevice.init();
			StateManager.init(new Display());
			Thread gameLoop = new Thread(new GameLoop());
			gameLoop.setName("GameLoop");
			gameLoop.start();
		} catch (Throwable e) {
			Logger.write("Fehler bei der Spielinitialisierung:");
			Logger.writeThrowable(e);
			System.exit(-1);
		}
	}
	
}