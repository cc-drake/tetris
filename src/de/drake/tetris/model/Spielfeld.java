package de.drake.tetris.model;

import java.util.Random;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.model.util.StoneType;
import de.drake.tetris.model.util.Position;
import de.drake.tetris.model.util.PositionHashMap;

/**
 * Das Spielfeld von Tetris, in dem der Stein fällt.
 */
public class Spielfeld {
	
	private final Random random;
	
	/**
	 * Eine Liste der blockierten Felder des Spielfelds, strukturiert nach ihrer Position.
	 */
	private final PositionHashMap<StoneType> blockierteFelder = new PositionHashMap<StoneType>();
	
	private int lastRand = Integer.MAX_VALUE;
	
	/**
	 * Erzeugt ein neues Spielfeld.
	 * @param seed Startwert für den Zufallsgenerator zur Erzeugung von Cheese-Rows
	 */
	Spielfeld(final long seed) {
		this.random = new Random(seed);
		this.generateCheeseRows(GameMode.getCheeseRows());
	}
	
	void verarbeiteStein(Stone stein) {
		switch (stein.getType()) {
		case BLUE:
		case GREEN:
		case RED:
		case YELLOW:
			for (Position position : stein.getPositionen()) {
				this.blockierteFelder.put(position, stein.getType());
			}
			return;
		case BOMB_SQUARE:
			this.entferne3x3(stein.getMittelpunkt());
			return;
		case BOMB_HORIZONTAL:
			this.blockierteFelder.cutRow(stein.getMittelpunkt().getY());
			return;
		case BOMB_VERTICAL:
			this.blockierteFelder.cutColumn(stein.getMittelpunkt().getX());
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
		
		this.blockierteFelder.insertRow(Config.hoehe - 1);
		
		for (int x = 0; x < Config.breite; x++) {
			this.blockierteFelder.put(x, Config.hoehe - 1, StoneType.CHEESE);
		}
		
		int rand = this.lastRand;
		while (rand == lastRand) {
			rand = this.random.nextInt(Config.breite);
		}
		this.blockierteFelder.remove(rand, Config.hoehe - 1);
		this.lastRand = rand;
		
	}
	
	/**
	 * Entfernt alle fertigen Reihen aus dem Spielfeld.
	 * 
	 * @return
	 * 		Die Anzahl der fertigen Reihen, die entfernt wurden.
	 */
	int entferneFertigeReihen() {
		int result = 0;
		boolean zeileFertig;
		int yMin = this.blockierteFelder.yMin();
		int yMax = this.blockierteFelder.yMax();
		for (int y = yMin; y <= yMax; y++) {
			zeileFertig = true;
			for (int x = 0; x < Config.breite; x++) {
				if (!this.isBlocked(new Position(x, y))) {
					zeileFertig = false;
					break;
				}
			}
			if (zeileFertig) {
				this.blockierteFelder.cutRow(y);
				result++;
			}
		}
		return result;
	}
	
	/**
	 * Entfernt ein 3x3-Feld aus dem Spielfeld und lässt darüberliegende Felder nachrutschen.
	 * 
	 * @param mittelpunkt
	 * 		Der Mittelpunkt des zu entfernenden 3x3-Feldes.
	 * 
	 */
	private void entferne3x3(final Position mittelpunkt) {
		for (int y = mittelpunkt.getY() - 1;
				y <= Math.min(Config.hoehe - 1, mittelpunkt.getY() + 1); y++) {
			for (int x = Math.max(0, mittelpunkt.getX() - 1);
					x <= Math.min(Config.breite - 1, mittelpunkt.getX() + 1); x++) {
				this.blockierteFelder.cut(x, y);
			}
		}
	}
	
	/**
	 * Gibt den Typ des Feldes an der angegebenen Position zurück.
	 */
	public StoneType getStoneType(final int spalte, final int zeile) {
		return this.blockierteFelder.get(spalte, zeile);
	}
	
	int getCheeseReihen() {
		int result = 0;
		int yMin = this.blockierteFelder.yMin();
		int yMax = this.blockierteFelder.yMax();
		for (int zeile = yMin; zeile <= yMax; zeile ++) {
			for (int spalte = 0; spalte < Config.breite; spalte++) {
				if (this.blockierteFelder.get(spalte, zeile) == StoneType.CHEESE) {
					result++;
					break;
				}
			}
		}
		return result;
	}

	public boolean isBlocked(Position position) {
		if (position.getX() < 0 || position.getX() >= Config.breite || position.getY() >= Config.hoehe)
			return true;
		return this.blockierteFelder.containsKey(position);
	}
	
}