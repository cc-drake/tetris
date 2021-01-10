package de.drake.tetris.states;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.util.Action;
import de.drake.tetris.screens.GameScreen;

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
	private final ArrayList<Player> spielerliste = new ArrayList<Player>(6);
	
	/**
	 * Speichert den aktuellen Zustand des Spiels.
	 */
	private int state = GameState.PREPARED;
	
	/**
	 * Die seit Spielbeginn vergangene Zeit in Nanosekunden (ohne Pausen).
	 */
	private long laufzeitNano = 0;
	
	/**
	 * Der Zeitpunkt, an dem zuletzt ein "Tick" erfolgt ist
	 */
	private long letzteTickzeit = 0;
	
	/**
	 * Erstellt einen neuen GameState.
	 */
	GameState() {
		this.screen = new GameScreen();
		Random random = new Random();
		long seed = random.nextLong();
		for (PlayerConfig config : PlayerConfig.playerConfigs) {
			Player player = new Player(config, this, seed);
			this.spielerliste.add(player);
			this.screen.addPlayer(this, player);
		}
	}
	
	@Override
	public void tick() {
		
		//Zwischenspeichern, damit sich die Werte zwischendurch nicht ändern
		int currentState = this.state;
		
		switch (currentState) {
		case GameState.RUNNING:
			if (this.letzteTickzeit > 0) {
				this.laufzeitNano += System.nanoTime() - this.letzteTickzeit;
			}
		default:
			this.letzteTickzeit = System.nanoTime();
		}
		
		//Eingaben abfragen und ausführen
		Action action;
		for (Player spieler : this.spielerliste) {
			
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
		
		//Spieler ticken lassen
		for (Player spieler : this.spielerliste) {
			spieler.tick(currentState, this.laufzeitNano);
		}
		
		if (currentState == GameState.RUNNING)
			this.checkWinLose();
		
	}
	
	/**
	 * Reagiert auf das Ausführen der Quit-Aktion eines Spielers.
	 */
	private void toggleQuit() {
		switch (this.state) {
		case GameState.ENDED:
			State.setCurrentState(new StartState());
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
			State.setCurrentState(new StartState());
			break;
		case GameState.ENDED:
			State.setCurrentState(new GameState());
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
	 * Prüft, ob Spieler gewonnen oder verloren haben, und beendet ggfs. das Spiel.
	 */
	private void checkWinLose() {

		//Variablen initialisieren und zugebaute Spieler auf LOSER setzen
		boolean timeout = false;
		if (GameMode.getTimeLimit() > 0 && this.laufzeitNano / 1000000000. >= GameMode.getTimeLimit())
			timeout = true;
		
		int anzahlSpieler = this.spielerliste.size();
		int anzahlAktiveSpieler = 0;
		int maxReihen = 0;
		long maxTime = 0;
		int minRaceReihen = Integer.MAX_VALUE;
		int minCheeseReihen = Integer.MAX_VALUE;
		for (Player player : this.spielerliste) {
			if (player.hasState(Player.UNDEF) && !GameMode.getMode().equals(GameMode.SOLITAER))
				player.setState(Player.LOSER);
			if (player.hasState(Player.ACTIVE))
				anzahlAktiveSpieler++;
			if (player.getFertigeReihen() > maxReihen)
				maxReihen = player.getFertigeReihen();
			if (player.getLaufzeit() > maxTime)
				maxTime = player.getLaufzeit();
			if (GameMode.getRaceRows() > 0 && player.hasState(Player.ACTIVE) && player.getVerbleibendeReihen() < minRaceReihen)
				minRaceReihen = player.getVerbleibendeReihen();
			if (GameMode.getCheeseRows() > 0 && player.hasState(Player.ACTIVE) && player.getCheeseReihen() < minCheeseReihen)
				minCheeseReihen = player.getCheeseReihen();
		}
		
		switch (GameMode.getMode()) {
		
		case GameMode.SOLITAER:
			if (timeout || anzahlAktiveSpieler == 0) {
				for (Player spieler : this.spielerliste) {
					if (spieler.getFertigeReihen() == maxReihen) {
						spieler.setState(Player.WINNER);
					} else {
						spieler.setState(Player.LOSER);
					}
				}
				this.state = GameState.ENDED;
			}
			break;
			
		case GameMode.COMBAT:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)) {
				for (Player spieler : this.spielerliste) {
					if (spieler.hasState(Player.ACTIVE) || (anzahlAktiveSpieler == 0 && spieler.getLaufzeit() == maxTime)) {
						spieler.setState(Player.WINNER);
					}
				}
				this.state = GameState.ENDED;
			}
			break;
		
		case GameMode.RACE:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)
			|| minRaceReihen == 0) {
				for (Player spieler : this.spielerliste) {
					if (spieler.hasState(Player.ACTIVE)
							&& spieler.getVerbleibendeReihen() == minRaceReihen) {
						spieler.setState(Player.WINNER);
					} else {
						spieler.setState(Player.LOSER);
					}
				}
				this.state = GameState.ENDED;
			}
			break;
		
		case GameMode.CHEESE:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)
			|| minCheeseReihen == 0) {
				for (Player player : this.spielerliste) {
					if (player.hasState(Player.ACTIVE)
							&& player.getCheeseReihen() == minCheeseReihen) {
						player.setState(Player.WINNER);
					} else {
						player.setState(Player.LOSER);
					}
				}
				this.state = GameState.ENDED;
			}
			break;
			
		}

	}
	
	public void draufwerfen(final Player werfer, final int rows) {
		for (Player spieler : this.spielerliste) {
			if (spieler == werfer || !spieler.hasState(Player.ACTIVE))
				continue;
			spieler.addRows(rows);
		}
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}
	
	/**
	 * Gibt den aktuellen Zustand des PlayStates zurück.
	 */
	public int getState() {
		return this.state;
	}
}