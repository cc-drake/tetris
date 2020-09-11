package de.drake.tetris.states;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

import de.drake.tetris.config.PlayerTemplate;
import de.drake.tetris.input.gamepad.GamepadMonitor;
import de.drake.tetris.model.Spieler;
import de.drake.tetris.screens.GameScreen;
import de.drake.tetris.util.Action;

/**
 * Der PlayState verwaltet das aktive Tetris-Spiel.
 */
public class GameState extends State {
	
	public final static int PREPARED = 1;
	
	public final static int RUNNING = 2;
	
	public final static int PAUSED = 3;
	
	public final static int QUIT = 4;
	
	public final static int ENDED = 5;
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final GameScreen screen;
	
	/**
	 * Eine Liste der Spieler, die gemeinsam Tetris spielen.
	 */
	private final ArrayList<Spieler> spielerliste = new ArrayList<Spieler>(6);
	
	/**
	 * Speichert den aktuellen Zustand des Spiels.
	 */
	private int state = GameState.PREPARED;
	
	/**
	 * Erstellt einen neuen PlayState.
	 */
	GameState() {
		this.screen = new GameScreen();
		Random random = new Random();
		long seed = random.nextLong();
		Spieler spieler;
		for (PlayerTemplate template : PlayerTemplate.createPlayerTemplates()) {
			spieler = new Spieler(template, this, seed);
			this.spielerliste.add(spieler);
			this.screen.addPlayer(this, spieler);
		}
	}
	
	@Override
	public void tick() {
		
		//Zwischenspeichern, damit sich die Werte zwischendurch nicht �ndern
		int currentState = this.state;
		long currentTime = System.nanoTime();
		
		Action action;
		for (Spieler spieler : this.spielerliste) {
			
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
			if (currentState == GameState.RUNNING) {
				spieler.performMoveAction(action);
			}
			
		}
		
		for (Spieler spieler : this.spielerliste) {
			spieler.performSteinfall(currentState, currentTime);
		}
		
		this.checkVictory();
		
	}
	
	/**
	 * Reagiert auf das Ausf�hren der Quit-Aktion eines Spielers.
	 */
	private void toggleQuit() {
		switch (this.state) {
		case GameState.ENDED:
			this.quitGame();
			break;
		case GameState.QUIT:
			this.state = GameState.RUNNING;
			break;
		default:
			this.state = GameState.QUIT;
		}
	}
	
	/**
	 * Reagiert auf das Ausf�hren der Pause-Aktion eines Spielers.
	 */
	private void togglePause() {
		switch (this.state) {
		case GameState.QUIT:
			this.state = GameState.ENDED;
		case GameState.ENDED:
			this.quitGame();
			break;
		case GameState.PAUSED:
		case GameState.PREPARED:
			this.state = GameState.RUNNING;
			break;
		case GameState.RUNNING:
			this.state = GameState.PAUSED;
			break;
		}
	}
	
	/**
	 * Pr�ft, ob alle bis auf einen Spieler das Spiel verloren haben, und beendet ggfs. das Spiel.
	 */
	private void checkVictory() {
		int anzahlSpieler = this.spielerliste.size();
		int anzahlVerlierer = 0;
		for (Spieler spieler : this.spielerliste) {
			if (spieler.getState() == Spieler.LOSER) {
				anzahlVerlierer++;
			}
		}
		if ((anzahlSpieler > 1 && anzahlVerlierer >= anzahlSpieler - 1)
				|| (anzahlSpieler == 1 && anzahlVerlierer == 1)) {
			this.state = GameState.ENDED;
			for (Spieler spieler : this.spielerliste) {
				if (spieler.getState() == Spieler.ACTIVE)
					spieler.winGame();
			}
		}
	}
	
	private void quitGame() {
		for (GamepadMonitor monitor : GamepadMonitor.gamepadMonitors) {
			monitor.stopThread();
		}
		State.setCurrentState(State.startState);
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}
	
	/**
	 * Gibt den aktuellen Zustand des PlayStates zur�ck.
	 */
	public int getState() {
		return this.state;
	}
}