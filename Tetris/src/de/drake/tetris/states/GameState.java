package de.drake.tetris.states;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import de.drake.tetris.config.PlayerTemplate;
import de.drake.tetris.model.Spieler;
import de.drake.tetris.util.Action;
import de.drake.tetris.view.PlayerScreen;

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
	private final JPanel screen;
	
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
		
		this.screen = new JPanel();
		this.screen.setBackground(Color.white);
		this.screen.setFocusable(true);
		switch (PlayerTemplate.createPlayerTemplates().size()) {
		case 1:
			this.screen.setLayout(new GridLayout(1, 1, 5, 5));
			break;
		case 2:
			this.screen.setLayout(new GridLayout(1, 2, 5, 5));
			break;
		case 3:
		case 4:
			this.screen.setLayout(new GridLayout(2, 2, 5, 5));
			break;
		case 5:
		case 6:
			this.screen.setLayout(new GridLayout(2, 3, 5, 5));
			break;
		default:
			throw new Error("Mehr als 6 Spieler sind nicht zugelassen");
		}
		
		Random random = new Random();
		long seed = random.nextLong();
		Spieler spieler;
		for (PlayerTemplate template : PlayerTemplate.createPlayerTemplates()) {
			spieler = new Spieler(template, this, seed);
			this.spielerliste.add(spieler);
			this.screen.add(new PlayerScreen(this, spieler));
		}
	}
	
	@Override
	public void tick() {
		
		//Zwischenspeichern, damit sich die Werte zwischendurch nicht ändern
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
	 * Reagiert auf das Ausführen der Quit-Aktion eines Spielers.
	 */
	private void toggleQuit() {
		switch (this.state) {
		case GameState.ENDED:
			State.setCurrentState(State.mainState);
			break;
		case GameState.QUIT:
			this.state = GameState.RUNNING;
			break;
		default:
			this.state = GameState.QUIT;
		}
	}
	
	/**
	 * Reagiert auf das Ausführen der Pause-Aktion eines Spielers.
	 */
	private void togglePause() {
		switch (this.state) {
		case GameState.QUIT:
			this.state = GameState.ENDED;
		case GameState.ENDED:
			State.setCurrentState(State.mainState);
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
	 * Prüft, ob alle bis auf einen Spieler das Spiel verloren haben, und beendet ggfs. das Spiel.
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

	@Override
	public JPanel getScreen() {
		return this.screen;
	}
	
	/**
	 * Gibt den aktuellen Zustand des PlayStates zurück.
	 */
	public int getState() {
		return this.state;
	}
}