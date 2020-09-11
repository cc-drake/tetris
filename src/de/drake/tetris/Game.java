package de.drake.tetris;

import de.drake.tetris.gfx.Assets;
import de.drake.tetris.screens.Display;
import de.drake.tetris.states.State;

/**
 * Der GameController initialisiert das Spiel und l�st regelm��ig die Methoden "tick()" und "render()" aus.
 */
class Game {

	private final Display display;
	
	Game() {
		Assets.init();
		this.display = new Display();
		State.setCurrentState(State.startState);
		GameLoop gameLoop = new GameLoop(this);
		gameLoop.start();
		
	}
	
	void tick() {
		State.getCurrentState().tick();
	}
	
	void render() {
		this.display.render();
	}
	
}