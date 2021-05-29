package de.drake.tetris;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.IniInOut;
import de.drake.tetris.controller.GameLoop;
import de.drake.tetris.controller.states.StateManager;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.view.Display;
import de.drake.tetris.view.ErrorWindow;

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
		try {
			IniInOut.initConfig();
			Asset.init();
			InputDevice.init();
			StateManager.init(new Display());
			Thread gameLoop = new Thread(new GameLoop());
			gameLoop.start();
		} catch (Throwable e) {
			new ErrorWindow(e);
		}
	}
	
}