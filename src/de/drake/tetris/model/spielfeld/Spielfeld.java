package de.drake.tetris.model.spielfeld;

import java.util.HashSet;
import java.util.Random;
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
	 * Eine Liste der Blöcke im Spielfeld.
	 */
	private final HashSet<Block> blocks = new HashSet<Block>();
	
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
	public Spielfeld(final long seed) {
		this.random = new Random(seed);
		for (int y = Config.hoehe - GameMode.getCheeseRows(); y < Config.hoehe ; y++) {
			this.generateCheeseRow(y);
		}
	}
	
	public void clearRow(final int row) {
		Predicate<Block> filter = new Predicate<Block>() {

			@Override
			public boolean test(final Block block) {
				return block.getY() == row;
			}
			
		};
		
		this.blocks.removeIf(filter);
	}

	public void clearColumn(final int column) {
		Predicate<Block> filter = new Predicate<Block>() {

			@Override
			public boolean test(final Block block) {
				return block.getX() == column;
			}
			
		};
		
		this.blocks.removeIf(filter);
	}
	
	/**
	 * Leert ein 3x3-Feld im Spielfeld.
	 * 
	 * @param mittelpunkt
	 * 		Der Mittelpunkt des zu entfernenden 3x3-Feldes.
	 * 
	 */
	public void clear3x3(final Position mittelpunkt) {
		Predicate<Block> filter = new Predicate<Block>() {

			@Override
			public boolean test(final Block block) {
				return (Math.abs(block.getX() - mittelpunkt.getX()) <= 1)
						&& (Math.abs(block.getY() - mittelpunkt.getY()) <= 1);
			}
			
		};
		
		this.blocks.removeIf(filter);
	}
	
	/**
	 * Gibt alle Blöcke zurück, die sich im angegebenen Koordinatenbereich befinden.
	 * @param yMax Unterer Rand des Koordinatenbereichs
	 * @param xMin Linker Rand des Koordinatenbereichs
	 * @param xMax Rechter Rand des Koordinatenbereichs
	 */
	public HashSet<Block> getBlocks(final int yMax, final int xMin, final int xMax) {
		HashSet<Block> result = new HashSet<Block>();
		for (Block block : this.blocks) {
			if ((block.getY() <= yMax)
					&& (block.getX() >= xMin)
					&& (block.getX() <= xMax)) {
				result.add(block);
			}
		}
		return result;
	}
	
	public void generateCheeseRow(final int row) {
		int rand = this.lastRand;
		while (rand == this.lastRand) {
			rand = this.random.nextInt(Config.breite);
		}
		this.lastRand = rand;
		
		for (int x = 0; x < Config.breite; x++) {
			if (x == this.lastRand)
				continue;
			this.blocks.add(new Block(x, row, Asset.TEXTURE_ORANGE, true));
		}
	}
	
	public void addBlocks(final HashSet<Position> positions, final BlockTexture texture,
			final boolean isCheese) {
		for (Position position : positions) {
			this.blocks.add(new Block(position.getX(), position.getY(), texture, isCheese));
		}
	}
	
	/**
	 * Gibt die Blöcke des Spielfelds zurück.
	 */
	public HashSet<BlockPaintObject> getBlocks() {
		return new HashSet<BlockPaintObject>(this.blocks);
	}
	
	public int getRemainingCheeseRows() {
		HashSet<Integer> cheeseRows = new HashSet<Integer>();
		for (Block block : this.blocks) {
			if (block.isCheese()) {
				cheeseRows.add(block.getY());
			}
		}
		return cheeseRows.size();
	}

	public boolean isBlocked(HashSet<Position> positions) {
		for (Position position : positions) {
			if (position.getX() < 0 || position.getX() >= Config.breite
					|| position.getY() >= Config.hoehe)
				return true;
		}
		
		for (Block block : this.blocks) {
			for (Position position : positions) {
				if (block.getX() == position.getX() && block.getY() == position.getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean rowIsComplete(final int row) {
		int blocks = 0;
		
		for (Block block : this.blocks) {
			if (block.getY() == row) {
				blocks++;
			}
		}
		
		return blocks == Config.breite;
	}
	
}