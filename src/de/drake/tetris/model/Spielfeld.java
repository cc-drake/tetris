package de.drake.tetris.model;

import java.util.HashMap;
import java.util.Random;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.model.util.StoneType;
import de.drake.tetris.model.util.Position;

/**
 * Das Spielfeld von Tetris, in dem der Stein fällt.
 */
public class Spielfeld {
	
	private final Random random;
	
	/**
	 * Die Anzahl zusätzlicher, nicht sichtbarer Zeilen oberhalb des Spielfelds.
	 * Diese werden benötigt, wenn ein in der obersten Zeile befindlicher Stein gedreht wird
	 * und oben aus dem Spielfeld ragt.
	 */
	private final int zusatzzeilen = Config.getMaxSteinSize();
	
	/**
	 * Eine Liste der Felder des Spielfelds, strukturiert nach ihrer Position.
	 */
	private final HashMap<Position, StoneType> felder = new HashMap<Position, StoneType>();
	
	private int lastRand = Integer.MAX_VALUE;
	
	/**
	 * Erzeugt ein neues Spielfeld.
	 * @param seed Startwert für den Zufallsgenerator zur Erzeugung von Cheese-Rows
	 */
	Spielfeld(final long seed) {
		this.random = new Random(seed);
		for (int x = 0; x < Config.breite; x++) {
			for (int y = -this.zusatzzeilen; y < Config.hoehe; y++) {
				this.felder.put(new Position(x, y), StoneType.CLEAR);
			}
		}
		
		if (GameMode.gameMode == GameMode.CHEESE)
			this.generateCheeseRows(GameMode.cheeseRows);
	}
	
	void verarbeiteStein(Stone stein) {
		switch (stein.getType()) {
		case BLUE:
		case GREEN:
		case RED:
		case YELLOW:
			for (Position position : stein.getPositionen()) {
				this.felder.put(position, stein.getType());
			}
			return;
		case BOMB_SQUARE:
			this.entferne3x3(stein.getMittelpunkt());
			return;
		case BOMB_HORIZONTAL:
			this.entferneReihe(stein.getMittelpunkt().getY());
			return;
		case BOMB_VERTICAL:
			this.entferneSpalte(stein.getMittelpunkt().getX());
			return;
		default:
			throw new Error("Ungültiger Steintyp");
		}
	}
	
	void generateCheeseRows(final int rows) {
		for (int i = 0; i < rows; i++) {
			this.generateCheeseRow();
		}
	}

	private void generateCheeseRow() {
		for (int y = -this.zusatzzeilen; y < Config.hoehe - 1; y++) {
			for (int x = 0; x < Config.breite; x++) {
				this.felder.put(new Position(x, y), this.felder.get(new Position(x, y + 1)));
			}
		}
		
		for (int spalte = 0; spalte < Config.breite; spalte++) {
			this.felder.put(new Position(spalte, Config.hoehe - 1), StoneType.CHEESE);
		}
		
		int rand = this.lastRand;
		while (rand == lastRand) {
			rand = this.random.nextInt(Config.breite);
		}
		this.felder.put(new Position(rand, Config.hoehe - 1), StoneType.CLEAR);
		this.lastRand = rand;
		
	}
	
	/**
	 * Entfernt alle fertigen Reihen aus dem Spielfeld.
	 * 
	 * @return
	 * 		Die Anzahl der fertigen Reihen, die entfernt wurden.
	 */
	int entferneFertigeReihen() {
		int fertigeReihen = 0;
		boolean zeileFertig;
		for (int y = -this.zusatzzeilen; y < Config.hoehe; y++) {
			zeileFertig = true;
			for (int x = 0; x < Config.breite; x++) {
				if (!this.isBlocked(new Position(x, y))) {
					zeileFertig = false;
					break;
				}
			}
			if (zeileFertig) {
				this.entferneReihe(y);
				fertigeReihen++;
			}
		}
		return fertigeReihen;
	}

	/**
	 * Entfernt eine Reihe aus dem Spielfeld und lässt die darüberliegenden Felder nachrutschen.
	 * 
	 * @param zeile
	 * 		Der Index der Zeile, die entfernt werden soll.
	 * 
	 */
	private void entferneReihe(final int zeile) {
		for (int y = zeile; y >= -this.zusatzzeilen; y--) {
			for (int x = 0; x < Config.breite; x++) {
				if (y == -this.zusatzzeilen) {
					this.felder.put(new Position(x, y), StoneType.CLEAR);
				} else {
					this.felder.put(new Position(x, y), this.felder.get(new Position(x, y - 1)));
				}
			}
		}
	}
	
	/**
	 * Entfernt eine Spalte aus dem Spielfeld.
	 * 
	 * @param spalte
	 * 		Der Index der Spalte, die entfernt werden soll.
	 * 
	 */
	private void entferneSpalte(final int spalte) {
		for (int zeile = -this.zusatzzeilen; zeile < Config.hoehe; zeile++) {
			this.felder.put(new Position(spalte, zeile), StoneType.CLEAR);
		}
	}
	
	/**
	 * Entfernt ein 3x3-Feld aus dem Spielfeld und lässt darüberliegende Felder nachrutschen.
	 * 
	 * @param mittelpunkt
	 * 		Der Mittelpunkt des zu entfernenden 3x3-Feldes.
	 * 
	 */
	private void entferne3x3(final Position mittelpunkt) {
		int entfernteZeilen = mittelpunkt.getY() == Config.hoehe - 1 ? 2 : 3;
		for (int y = Math.min(Config.hoehe - 1, mittelpunkt.getY() + 1);
				y >= -this.zusatzzeilen; y--) {
			for (int x = Math.max(0, mittelpunkt.getX() - 1);
					x <= Math.min(Config.breite - 1, mittelpunkt.getX() + 1); x++) {
				if (y <= entfernteZeilen - this.zusatzzeilen - 1) {
					this.felder.put(new Position(x, y), StoneType.CLEAR);
				} else {
					this.felder.put(new Position(x, y),
							this.felder.get(new Position(x, y - entfernteZeilen)));
				}
			}
		}
	}
	
	/**
	 * Gibt den Typ des Feldes an der angegebenen Position zurück.
	 */
	public StoneType getStoneType(final Position position) {
		return this.felder.get(position);
	}
	
	/**
	 * Gibt an, ob das Spielfeld an der angegebenen Position blockiert ist.
	 * Für Positionen außerhalb des Spielfelds wird stets true zurückgegeben.
	 */
	public boolean isBlocked(final Position position) {
		return (this.felder.get(position) != StoneType.CLEAR);
	}

	int getCheeseReihen() {
		int result = 0;
		for (int zeile = 0; zeile < Config.hoehe; zeile++) {
			for (int spalte = 0; spalte < Config.breite; spalte++) {
				if (this.felder.get(new Position(spalte, zeile)) == StoneType.CHEESE) {
					result++;
					break;
				}
			}
		}
		return result;
	}
	
}