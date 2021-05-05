package de.drake.tetris.model;

import java.util.HashSet;
import java.util.Random;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.model.util.Position;
import de.drake.tetris.model.util.PositionHashMap;

/**
 * Das Spielfeld von Tetris, in dem der Stein fällt.
 */
public class Spielfeld {
	
	private final Random random;

	/**
	 * Eine Liste der Blöcke im Spielfeld, strukturiert nach ihrer Position.
	 */
	private final PositionHashMap<Block> blocks = new PositionHashMap<Block>();
	
	private int lastRand = Integer.MAX_VALUE;
	
	/**
	 * Erzeugt ein neues Spielfeld.
	 * @param seed Startwert für den Zufallsgenerator zur Erzeugung von Cheese-Rows
	 */
	Spielfeld(final long seed) {
		this.random = new Random(seed);
		this.generateCheeseRows(GameMode.getCheeseRows());
	}
	
	void generateCheeseRows(final int rows) {
		for (int i = 0; i < rows; i++) {
			this.generateCheeseRow();
		}
		if (rows >= 4) {
			Asset.SOUND_ADDFOUR.play();
		} else if (rows > 0) {
			Asset.SOUND_ADD.play();
		}
	}

	private void generateCheeseRow() {
		
		this.blocks.insertRow(Config.hoehe - 1);
		
		for (int x = 0; x < Config.breite; x++) {
			this.blocks.put(x, Config.hoehe - 1, new Block(Asset.TEXTURE_ORANGE, true));
		}
		
		int rand = this.lastRand;
		while (rand == lastRand) {
			rand = this.random.nextInt(Config.breite);
		}
		this.blocks.remove(rand, Config.hoehe - 1);
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
		int yMin = this.blocks.yMin();
		int yMax = this.blocks.yMax();
		for (int y = yMin; y <= yMax; y++) {
			zeileFertig = true;
			for (int x = 0; x < Config.breite; x++) {
				if (!this.blocks.containsKey(new Position(x, y))) {
					zeileFertig = false;
					break;
				}
			}
			if (zeileFertig) {
				this.blocks.cutRow(y);
				result++;
			}
		}
		if (result >= 4) {
			Asset.SOUND_TETRIS.play();
		} else if (result > 0) {
			Asset.SOUND_ROW.play();
		}
		return result;
	}
	
	public void addBlocks(final HashSet<Position> positions, final BlockTexture texture) {
		for (Position position : positions) {
			this.blocks.put(position, new Block(texture, false));
		}
	}
	
	/**
	 * Entfernt ein 3x3-Feld aus dem Spielfeld und lässt darüberliegende Felder nachrutschen.
	 * 
	 * @param mittelpunkt
	 * 		Der Mittelpunkt des zu entfernenden 3x3-Feldes.
	 * 
	 */
	public void entferne3x3(final Position mittelpunkt) {
		for (int y = mittelpunkt.getY() - 1;
				y <= Math.min(Config.hoehe - 1, mittelpunkt.getY() + 1); y++) {
			for (int x = Math.max(0, mittelpunkt.getX() - 1);
					x <= Math.min(Config.breite - 1, mittelpunkt.getX() + 1); x++) {
				this.blocks.cut(x, y);
			}
		}
	}
	
	public void entferneReihe(final int zeile) {
		this.blocks.cutRow(zeile);
	}
	
	public void entferneSpalte(final int spalte) {
		this.blocks.cutColumn(spalte);
	}
	
	/**
	 * Gibt den Block an der angegebenen Position zurück, sofern vorhanden.
	 */
	public Block getBlock(final int spalte, final int zeile) {
		return this.blocks.get(spalte, zeile);
	}
	
	int getCheeseReihen() {
		int result = 0;
		int yMin = this.blocks.yMin();
		int yMax = this.blocks.yMax();
		for (int zeile = yMin; zeile <= yMax; zeile ++) {
			for (int spalte = 0; spalte < Config.breite; spalte++) {
				if (this.blocks.get(spalte, zeile).isCheese()) {
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
		return this.blocks.containsKey(position);
	}
	
}