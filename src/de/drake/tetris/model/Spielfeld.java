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
	 * Blockiert ein Feld des Spielfelds mit dem angegebenen Typ.
	 * 
	 * @param position
	 * 		Die Position des Feldes, das geblockt werden soll.
	 * @param type
	 * 		Der Typ, mit dem das Feld geblockt werden soll.
	 */
	void block(final Position position, final StoneType type) {
		this.felder.put(position, type);
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
		if (!this.felder.containsKey(position))
			return true;
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