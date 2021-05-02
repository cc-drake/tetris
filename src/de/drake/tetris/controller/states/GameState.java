package de.drake.tetris.controller.states;

import java.util.HashMap;
import java.util.Random;

import javax.swing.JComponent;

import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.input.util.InputHandler;
import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.util.Action;
import de.drake.tetris.model.util.GameStatus;
import de.drake.tetris.model.util.PlayerStatus;
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
	 * Eine Liste der InputHandler, die die Eingaben der jeweiligen Spieler puffern
	 */
	private final HashMap<Player, InputHandler> inputHandlers = new HashMap<Player, InputHandler>(6);
	
	/**
	 * Erstellt einen neuen GameState.
	 */
	GameState() {
		this.game = new Game();
		long seed = new Random().nextLong();
		for (PlayerConfig config : PlayerConfig.playerConfigs) {
			Player player = new Player(config, this.game, seed);
			game.addPlayer(player);
			this.inputHandlers.put(player, config.createInputHandler());
		}
		this.screen = new GameScreen(this.game);
	}
	
	@Override
	public void tick() {
		
		// Zwischenspeichern, damit sich die Werte zwischendurch nicht ändern
		GameStatus currentGameStatus = game.getStatus();
		
		// Eine Eingabe je Spieler ausführen
		Action action;
		for (Player player : this.game.getPlayers()) {
			
			action = this.inputHandlers.get(player).getNextAction();
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
			if (currentGameStatus == GameStatus.RUNNING
					&& player.hasStatus(PlayerStatus.ACTIVE)) {
				player.performMoveAction(action);
			}
			
		}
		
		// Spiel ticken lassen
		this.game.tick();
		
		// GUI aktualisieren
		this.screen.repaint();
		
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