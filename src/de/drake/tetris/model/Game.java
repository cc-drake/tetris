package de.drake.tetris.model;

import java.util.ArrayList;
import java.util.Random;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.model.util.GameStatus;

public class Game {
	
	/**
	 * Speichert den aktuellen Zustand des Spiels.
	 */
	private GameStatus status = GameStatus.PREPARED;
	
	/**
	 * Der Zeitpunkt, an dem zuletzt ein "Tick" erfolgt ist
	 */
	private long letzteTickzeit = 0;
	
	/**
	 * Die seit Spielbeginn vergangene Zeit in Nanosekunden (ohne Pausen).
	 */
	private long laufzeitNano = 0;
	
	/**
	 * Eine Liste der Player, die am Spiel teilnehmen.
	 */
	private final ArrayList<Player> players = new ArrayList<Player>(6);
	
	public Game() {
		Random random = new Random();
		long seed = random.nextLong();
		for (PlayerConfig config : PlayerConfig.playerConfigs) {
			this.players.add(new Player(config, this, seed));
		}
	}
	
	public void tick() {
		switch (this.status) {
		case RUNNING:
			if (this.letzteTickzeit > 0) {
				this.laufzeitNano += System.nanoTime() - this.letzteTickzeit;
			}
		default:
			this.letzteTickzeit = System.nanoTime();
		}
	}
	
	public void draufwerfen(final Player werfer, final int rows) {
		for (Player player : this.players) {
			if (player == werfer || !player.hasState(Player.ACTIVE))
				continue;
			player.addRows(rows);
		}
	}
	
	/**
	 * Prüft, ob Spieler gewonnen oder verloren haben, und beendet ggfs. das Spiel.
	 * @param laufzeitNano 
	 */
	public void checkWinLose() {

		//Variablen initialisieren und zugebaute Spieler auf LOSER setzen
		boolean timeout = false;
		if (GameMode.getTimeLimit() > 0 && this.laufzeitNano / 1000000000. >= GameMode.getTimeLimit())
			timeout = true;
		
		int anzahlSpieler = this.players.size();
		int anzahlAktiveSpieler = 0;
		int maxReihen = 0;
		long maxTime = 0;
		int minRaceReihen = Integer.MAX_VALUE;
		int minCheeseReihen = Integer.MAX_VALUE;
		for (Player player : this.players) {
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
				for (Player spieler : this.players) {
					if (spieler.getFertigeReihen() == maxReihen) {
						spieler.setState(Player.WINNER);
					} else {
						spieler.setState(Player.LOSER);
					}
				}
				this.status = GameStatus.ENDED;
			}
			break;
			
		case GameMode.COMBAT:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)) {
				for (Player spieler : this.players) {
					if (spieler.hasState(Player.ACTIVE) || (anzahlAktiveSpieler == 0 && spieler.getLaufzeit() == maxTime)) {
						spieler.setState(Player.WINNER);
					}
				}
				this.status = GameStatus.ENDED;
			}
			break;
		
		case GameMode.RACE:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)
			|| minRaceReihen == 0) {
				for (Player spieler : this.players) {
					if (spieler.hasState(Player.ACTIVE)
							&& spieler.getVerbleibendeReihen() == minRaceReihen) {
						spieler.setState(Player.WINNER);
					} else {
						spieler.setState(Player.LOSER);
					}
				}
				this.status = GameStatus.ENDED;
			}
			break;
		
		case GameMode.CHEESE:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)
			|| minCheeseReihen == 0) {
				for (Player player : this.players) {
					if (player.hasState(Player.ACTIVE)
							&& player.getCheeseReihen() == minCheeseReihen) {
						player.setState(Player.WINNER);
					} else {
						player.setState(Player.LOSER);
					}
				}
				this.status = GameStatus.ENDED;
			}
			break;
			
		}

	}
	
	/**
	 * Gibt den aktuellen Status des GameStates zurück.
	 */
	public GameStatus getStatus() {
		return this.status;
	}
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	public long getLaufzeit() {
		return this.laufzeitNano;
	}
	
	public void setStatus(final GameStatus status) {
		this.status = status;
	}
	
}