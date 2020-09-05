package de.drake.tetris;

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
		GameController gameController = new GameController();
		gameController.start();
	}
	
}