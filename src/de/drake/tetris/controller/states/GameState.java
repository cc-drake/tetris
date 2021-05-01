package de.drake.tetris.controller.states;

import javax.swing.JComponent;

import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.util.Action;
import de.drake.tetris.model.util.GameStatus;
import de.drake.tetris.view.screens.GameScreen;

/**
 * Der PlayState verwaltet das aktive Tetris-Spiel.
 */
public class GameState extends State {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final GameScreen screen;
	
	private final Game game;
	
	/**
	 * Erstellt einen neuen GameState.
	 */
	GameState() {
		this.game = new Game();
		this.screen = new GameScreen(this.game);
	}
	
	@Override
	public void tick() {
		
		this.game.tick();
		
		//Zwischenspeichern, damit sich die Werte zwischendurch nicht ändern
		GameStatus currentGameStatus = game.getStatus();
		
		//Eingaben abfragen und ausführen
		Action action;
		for (Player spieler : this.game.getPlayers()) {
			
			action = spieler.getInputAction();
			if (action == null)
				continue;
			if (action == Action.QUIT) {
				this.toggleQuit();
				continue;
			}
			if (action == Action.PAUSE) {
				this.togglePause();
				continue;
			}
			if (currentGameStatus == GameStatus.RUNNING) {
				spieler.performMoveAction(action);
			}
			
		}
		
		//Spieler ticken lassen
		for (Player spieler : this.game.getPlayers()) {
			spieler.tick(currentGameStatus);
		}
		
		if (currentGameStatus == GameStatus.RUNNING)
			this.game.checkWinLose();
		
	}
	
	/**
	 * Reagiert auf das Ausführen der Quit-Aktion eines Spielers.
	 */
	private void toggleQuit() {
		switch (this.game.getStatus()) {
		case ENDED:
			StateManager.setCurrentState(new StartState());
			break;
		case QUIT:
			this.game.setStatus(GameStatus.RUNNING);
			break;
		default:
			this.game.setStatus(GameStatus.QUIT);
		}
	}
	
	/**
	 * Reagiert auf das Ausführen der Pause-Aktion eines Spielers.
	 */
	private void togglePause() {
		switch (this.game.getStatus()) {
		case QUIT:
			this.game.setStatus(GameStatus.ENDED);
			StateManager.setCurrentState(new StartState());
			break;
		case ENDED:
			StateManager.setCurrentState(new GameState());
			break;
		case PAUSED:
		case PREPARED:
			this.game.setStatus(GameStatus.RUNNING);
			break;
		case RUNNING:
			this.game.setStatus(GameStatus.PAUSED);
			break;
		}
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}
	
}