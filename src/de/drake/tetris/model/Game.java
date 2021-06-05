package de.drake.tetris.model;

import java.util.ArrayList;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.log.Logger;
import de.drake.tetris.model.util.GameStatus;
import de.drake.tetris.model.util.PlayerStatus;

public class Game {
	
	/**
	 * Speichert den aktuellen Zustand des Spiels.
	 */
	private GameStatus status;
	
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
	
	public void addPlayer(final Player player) {
		this.players.add(player);
		this.setStatus(GameStatus.PREPARED);
	}
	
	public void tick() {

		long neueTickzeit = System.nanoTime();
		
		// Ist das Spiel pausiert, muss nur die letzte Tickzeit aktualisiert werden
		if (this.status != GameStatus.RUNNING) {
			this.letzteTickzeit = neueTickzeit;
			return;
		}
			
		// Laufzeit aktualisieren
		if (this.letzteTickzeit > 0) {
			this.laufzeitNano += neueTickzeit - this.letzteTickzeit;
			this.letzteTickzeit = neueTickzeit;
		}
		
		// Spieler ticken lassen
		for (Player player : this.players) {
			player.tick();
		}
		
		// Auf Sieg oder Niederlage prüfen
		this.checkWinLose();
		
	}
	
	/**
	 * Prüft, ob Spieler gewonnen oder verloren haben, und beendet ggfs. das Spiel.
	 * @param laufzeitNano 
	 */
	private void checkWinLose() {
		
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
			if (player.hasStatus(PlayerStatus.STUCK) && !GameMode.is(GameMode.SOLITAER))
				player.setStatus(PlayerStatus.LOSER);
			if (!player.isDead())
				anzahlAktiveSpieler++;
			if (player.getClearedRows() > maxReihen)
				maxReihen = player.getClearedRows();
			if (player.getLaufzeit() > maxTime)
				maxTime = player.getLaufzeit();
			if (GameMode.is(GameMode.RACE) && !player.isDead() && player.getRemainingRaceRows() < minRaceReihen)
				minRaceReihen = player.getRemainingRaceRows();
			if (GameMode.is(GameMode.CHEESE) && !player.isDead() && player.getRemainingCheeseRows() < minCheeseReihen)
				minCheeseReihen = player.getRemainingCheeseRows();
		}
		
		switch (GameMode.getMode()) {
		
		case GameMode.SOLITAER:
			if (timeout || anzahlAktiveSpieler == 0) {
				for (Player spieler : this.players) {
					if (spieler.getClearedRows() == maxReihen) {
						spieler.setStatus(PlayerStatus.WINNER);
					} else {
						spieler.setStatus(PlayerStatus.LOSER);
					}
				}
				this.setStatus(GameStatus.ENDED);
			}
			break;
			
		case GameMode.COMBAT:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)) {
				for (Player spieler : this.players) {
					if (!spieler.isDead() || (anzahlAktiveSpieler == 0 && spieler.getLaufzeit() == maxTime)) {
						spieler.setStatus(PlayerStatus.WINNER);
					}
				}
				this.setStatus(GameStatus.ENDED);
			}
			break;
		
		case GameMode.RACE:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)
			|| minRaceReihen == 0) {
				for (Player spieler : this.players) {
					if (!spieler.isDead() && spieler.getRemainingRaceRows() == minRaceReihen) {
						spieler.setStatus(PlayerStatus.WINNER);
					} else {
						spieler.setStatus(PlayerStatus.LOSER);
					}
				}
				this.setStatus(GameStatus.ENDED);
			}
			break;
		
		case GameMode.CHEESE:
			if (timeout || anzahlAktiveSpieler <= Math.min(1, anzahlSpieler - 1)
			|| minCheeseReihen == 0) {
				for (Player player : this.players) {
					if (!player.isDead() && player.getRemainingCheeseRows() == minCheeseReihen) {
						player.setStatus(PlayerStatus.WINNER);
					} else {
						player.setStatus(PlayerStatus.LOSER);
					}
				}
				this.setStatus(GameStatus.ENDED);
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
	
	long getLaufzeitNano() {
		return this.laufzeitNano;
	}
	
	public void setStatus(final GameStatus status) {
		this.status = status;
		Logger.write("Spielzustand geändert auf " + status);
	}
	
}