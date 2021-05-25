package de.drake.tetris.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.model.util.Position;

/**
 * Das Spielfeld von Tetris, in dem der Stein fällt.
 */
public class Spielfeld {

	/**
	 * Eine Liste der Blöcke im Spielfeld, strukturiert nach ihrer Position.
	 */
	private final HashMap<Position, Block> blocks = new HashMap<Position, Block>();
	
	/**
	 * Der Zufallsgenerator, der für die Erzeugung der Cheese-Rows zuständig ist
	 */
	private final Random random;
	
	/**
	 * Speichert die zuletzt ausgegebene Zufallszahl zur Erzeugung von Cheesereihen.
	 */
	private int lastRand = Integer.MAX_VALUE;
	
	/**
	 * Erzeugt ein neues Spielfeld.
	 * @param seed Startwert für den Zufallsgenerator zur Erzeugung von Cheese-Rows
	 */
	Spielfeld(final long seed) {
		this.random = new Random(seed);
		this.generateCheeseRows(GameMode.getCheeseRows());
	}
	
	public void clearRow(final int row) {
		Predicate<Position> filter = new Predicate<Position>() {

			@Override
			public boolean test(final Position position) {
				return position.getY() == row;
			}
			
		};
		
		this.blocks.keySet().removeIf(filter);
	}
	
	public void clearColumn(final int column) {
		Predicate<Position> filter = new Predicate<Position>() {

			@Override
			public boolean test(final Position position) {
				return position.getX() == column;
			}
			
		};
		
		this.blocks.keySet().removeIf(filter);
	}
	
	/**
	 * Leert ein 3x3-Feld im Spielfeld.
	 * 
	 * @param mittelpunkt
	 * 		Der Mittelpunkt des zu entfernenden 3x3-Feldes.
	 * 
	 */
	public void clear3x3(final Position mittelpunkt) {
		Predicate<Position> filter = new Predicate<Position>() {

			@Override
			public boolean test(final Position position) {
				return (Math.abs(position.getX() - mittelpunkt.getX()) <= 1)
						&& (Math.abs(position.getY() - mittelpunkt.getY()) <= 1);
			}
			
		};
		
		this.blocks.keySet().removeIf(filter);
	}
	
	public void removeRow(final int row, final int xMin, final int xMax) {
		Set<Entry<Position, Block>> blocksAbove = new HashSet<Entry<Position, Block>>();
		
		for (Entry<Position, Block> entry : this.blocks.entrySet()) {
			if ((entry.getKey().getY() < row)
					&& (entry.getKey().getX() >= xMin)
					&& (entry.getKey().getX() <= xMax)) {
				blocksAbove.add(entry);
			}
		}
		
		this.move(blocksAbove, 0, 1);
	}
	
	public void generateCheeseRows(final int rows) {
		for (int i = 0; i < rows; i++) {
			this.generateCheeseRow();
		}
	}

	private void generateCheeseRow() {
		
		this.move(this.blocks.entrySet(), 0, -1);
		
		int rand = this.lastRand;
		while (rand == this.lastRand) {
			rand = this.random.nextInt(Config.breite);
		}
		this.lastRand = rand;
		
		for (int x = 0; x < Config.breite; x++) {
			if (x == this.lastRand)
				continue;
			this.blocks.put(new Position(x, Config.hoehe - 1), new Block(Asset.TEXTURE_ORANGE, true));
		}
		
	}
	
	/**
	 * Verschiebt die ausgewählten Felder des Spielfelds in der angegebenen Richtung.
	 * 
	 * @param entries Die zu verschiebenden Blöcke
	 * @param x Die horizontale Verschiebung (positiv = nach rechts, negativ = nach links)
	 * @param y Die vertikale Verschiebung (positiv = nach unten, negativ = nach oben)
	 */
	private void move(final Set<Entry<Position, Block>> entries, final int x, final int y) {
		HashSet<Position> oldKeys = new HashSet<Position>();
		HashMap<Position, Block> newMaps = new HashMap<Position, Block>();
		
		for (Entry <Position, Block> entry : entries) {
			oldKeys.add(entry.getKey());
			Position newKey = new Position(entry.getKey());
			newKey.verschiebe(x, y);
			newMaps.put(newKey, entry.getValue());
		}
		
		this.blocks.keySet().removeAll(oldKeys);
		this.blocks.putAll(newMaps);
	}
	
	public void addBlocks(final HashSet<Position> positions, final BlockTexture texture,
			final boolean isCheese) {
		for (Position position : positions) {
			this.blocks.put(position, new Block(texture, isCheese));
		}
	}
	
	/**
	 * Gibt alle Blöcke zurück, welche sich über den übergebenen Rows befinden,
	 * aber nicht in den übergebenen Rows liegen.
	 * Ist Rows leer, so wird ein leeres HashSet zurückgegeben.
	 * @param rows Die Rows, über denen die Blöcke liegen müssen
	 * @param xMin Blöcke links von xMin werden ignoriert
	 * @param xMax Blöcke rechts von xMax werden ignoriert
	 */
	public HashSet<Block> getBlocksAbove(final HashSet<Integer> rows, final int xMin, final int xMax) {
		HashSet<Block> result = new HashSet<Block>();
		if (rows.isEmpty())
			return result;
		for (Entry<Position, Block> entry : this.blocks.entrySet()) {
			if ((entry.getKey().getY() < Collections.max(rows))
					&& (!rows.contains(entry.getKey().getY()))
					&& (entry.getKey().getX() >= xMin)
					&& (entry.getKey().getX() <= xMax)) {
				result.add(entry.getValue());
			}
		}
		return result;
	}
	
	/**
	 * Gibt die Blöcke des Spielfelds zurück.
	 */
	public HashMap<Position, Block> getBlocks() {
		return this.blocks;
	}
	
	int getRemainingCheeseRows() {
		HashSet<Integer> cheeseRows = new HashSet<Integer>();
		for (Entry <Position, Block> entry : this.blocks.entrySet()) {
			if (entry.getValue().isCheese()) {
				cheeseRows.add(entry.getKey().getY());
			}
		}
		return cheeseRows.size();
	}

	public boolean isBlocked(Position position) {
		if (position.getX() < 0 || position.getX() >= Config.breite
				|| position.getY() >= Config.hoehe)
			return true;
		return this.blocks.containsKey(position);
	}
	
	public boolean rowIsComplete(final int row) {
		int blocks = 0;
		
		for (Entry<Position, Block> entry : this.blocks.entrySet()) {
			if (entry.getKey().getY() == row) {
				blocks++;
			}
		}
		
		return blocks == Config.breite;
	}
	
}