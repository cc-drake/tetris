package de.drake.tetris.states;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import de.drake.tetris.config.PlayerTemplate;
import de.drake.tetris.input.KeyboardManager;
import de.drake.tetris.model.Spieler;
import de.drake.tetris.view.PlayerGUI;

/**
 * Der PlayState verwaltet das aktive Tetris-Spiel.
 */
public class PlayState extends GameState {
	
	public final static int PREPARED = 1;
	
	public final static int RUNNING = 2;
	
	public final static int PAUSED = 3;
	
	public final static int ENDED = 4;
	
	/**
	 * Das Panel, in dem das Spielfeld gezeichnet wird.
	 */
	private JPanel playPanel;
	
	/**
	 * Eine Liste der Spieler, die gemeinsam Tetris spielen.
	 */
	private ArrayList<Spieler> spielerliste = new ArrayList<Spieler>(6);
	
	/**
	 * Speichert den aktuellen Zustand des Spiels.
	 */
	private int currentState = PlayState.PREPARED;
	
	/**
	 * Erstellt einen neuen PlayState.
	 */
	PlayState() {
		
		this.playPanel = new JPanel();
		
		Random random = new Random();
		long seed = random.nextLong();
		for (PlayerTemplate template : PlayerTemplate.createPlayerTemplates()) {
			this.spielerliste.add(new Spieler(template, this, seed));
		}
		
		this.initPlayPanel();
	}
	
	private void initPlayPanel() {
		this.playPanel.setBackground(Color.white);
		this.playPanel.setFocusable(true);
		switch (this.spielerliste.size()) {
		case 1:
			this.playPanel.setLayout(new GridLayout(1, 1, 5, 5));
			break;
		case 2:
			this.playPanel.setLayout(new GridLayout(1, 2, 5, 5));
			break;
		case 3:
		case 4:
			this.playPanel.setLayout(new GridLayout(2, 2, 5, 5));
			break;
		case 5:
		case 6:
			this.playPanel.setLayout(new GridLayout(2, 3, 5, 5));
			break;
		default:
			throw new Error("Mehr als 6 Spieler sind nicht zugelassen");
		}
		for (Spieler spieler : this.spielerliste) {
			this.playPanel.add(new PlayerGUI(spieler));
		}
	}
	
	public void addKeyboardManager(final KeyboardManager manager) {
		this.playPanel.addKeyListener(manager);
		this.playPanel.addFocusListener(manager);
	}
	
	/**
	 * Reagiert auf das Ausführen der Pause-Aktion eines Spielers.
	 */
	public void togglePause() {
		switch (this.currentState) {
		case PlayState.PREPARED:
			this.currentState = PlayState.RUNNING;
			break;
		case PlayState.RUNNING:
			this.currentState = PlayState.PAUSED;
			break;
		case PlayState.PAUSED:
			this.currentState = PlayState.RUNNING;
			break;
		}
	}
	
	@Override
	public void tick() {
		
		//Zwischenspeichern, damit sich die Werte zwischendurch nicht ändern
		int currentState = this.currentState;
		long currentTime = System.nanoTime();
		
		for (Spieler spieler : this.spielerliste) {
			if (spieler.getCurrentState() == Spieler.ACTIVE) {
				spieler.performNextInputAction(currentState);
				spieler.performSteinfall(currentState, currentTime);
			}
		}
		
		this.checkVictory();
		
	}
	
	/**
	 * Prüft, ob alle bis auf einen Spieler das Spiel verloren haben, und beendet ggfs. das Spiel.
	 */
	private void checkVictory() {
		int anzahlSpieler = this.spielerliste.size();
		int anzahlVerlierer = 0;
		for (Spieler spieler : this.spielerliste) {
			if (spieler.getCurrentState() == Spieler.LOSER) {
				anzahlVerlierer++;
			}
		}
		if ((anzahlSpieler > 1 && anzahlVerlierer >= anzahlSpieler - 1)
				|| (anzahlSpieler == 1 && anzahlVerlierer == 1)) {
			this.currentState = PlayState.ENDED;
			for (Spieler spieler : this.spielerliste) {
				if (spieler.getCurrentState() == Spieler.ACTIVE)
					spieler.winGame();
			}
		}
	}

	@Override
	public JPanel getJPanel() {
		return this.playPanel;
	}
	
	/**
	 * Gibt den aktuellen Zustand des PlayStates zurück.
	 */
	public int getCurrentState() {
		return this.currentState;
	}
}