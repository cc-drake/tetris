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
	 * Eine Liste von Blocks, welche sich derzeit bewegen (z.B. fallende Blöcke).
	 */
	private HashSet<Block> movingBlocks;
	
	/**
	 * Der Zufallsgenerator, der für die Erzeugung der Cheese-Rows zuständig ist
	 */
	private final Random random;
	
	/**
	 * Speichert die Zahl der verbleibenden Käse-Reihen zwischen, um die Zugriffszeit zu verkürzen
	 */
	private int remainingCheeseRows = 0;
	
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
		this.generateCheeseRows(GameMode.getCheeseRows(), false);
	}
	
	public void clearRow(final int row) {
		Predicate<Block> filter = new Predicate<Block>() {

			@Override
			public boolean test(final Block block) {
				return block.getY() == row;
			}
			
		};
		
		this.blocks.removeIf(filter);
		this.updateRemainingCheeseRows();
	}

	public void clearColumn(final int column) {
		Predicate<Block> filter = new Predicate<Block>() {

			@Override
			public boolean test(final Block block) {
				return block.getX() == column;
			}
			
		};
		
		this.blocks.removeIf(filter);
		this.updateRemainingCheeseRows();
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
		this.updateRemainingCheeseRows();
	}
	
	/**
	 * Markiert alle Blöcke als "moving", die sich im angegebenen Koordinatenbereich befinden.
	 * @return Gibt zurück, ob sich im Koordinatenbereich Blöcke befinden.
	 * @param yMax Unterer Rand des Koordinatenbereichs
	 * @param xMin Linker Rand des Koordinatenbereichs
	 * @param xMax Rechter Rand des Koordinatenbereichs
	 */
	public boolean setMovingBlocks(final int yMax, final int xMin, final int xMax) {
		this.movingBlocks = new HashSet<Block>();
		for (Block block : this.blocks) {
			if ((block.getY() <= yMax)
					&& (block.getX() >= xMin)
					&& (block.getX() <= xMax)) {
				this.movingBlocks.add(block);
			}
		}
		if (this.movingBlocks.isEmpty()) {
			this.movingBlocks = null;
			return false;
		}
		return true;
	}
	
	public void moveBlocks(final int y, final double newVerticalShift) {
		if (this.movingBlocks == null)
			return;
		for (Block block : this.movingBlocks) {
			block.move(y, newVerticalShift);
		}
		
		this.updateRemainingCheeseRows();
	}
	
	public void generateCheeseRows(final int amount, final boolean delayByVerticalShift) {
		
		for (Block block : this.blocks) {
			block.move(-amount, delayByVerticalShift ? -amount : 0.);
		}
		
		for (int y = Config.hoehe - 1; y >= Config.hoehe - amount; y--) {
			int rand = this.lastRand;
			while (rand == this.lastRand) {
				rand = this.random.nextInt(Config.breite);
			}
			this.lastRand = rand;
			
			for (int x = 0; x < Config.breite; x++) {
				if (x == this.lastRand)
					continue;
				this.blocks.add(new Block(x, y, Asset.TEXTURE_ORANGE, true));
			}
		}
		this.remainingCheeseRows += amount;
	}
	
	private void updateRemainingCheeseRows() {
		HashSet<Integer> cheeseRows = new HashSet<Integer>();
		for (Block block : this.blocks) {
			if (block.isCheese()) {
				cheeseRows.add(block.getY());
			}
		}
		this.remainingCheeseRows = cheeseRows.size();
	}
	
	public void addBlocks(final HashSet<Position> positions, final BlockTexture texture,
			final boolean isCheese) {
		for (Position position : positions) {
			this.blocks.add(new Block(position.getX(), position.getY(), texture, isCheese));
		}
		this.updateRemainingCheeseRows();
	}
	
	/**
	 * Gibt die Blöcke des Spielfelds zurück.
	 */
	public HashSet<BlockPaintObject> getBlocks() {
		return new HashSet<BlockPaintObject>(this.blocks);
	}
	
	public int getRemainingCheeseRows() {
		return this.remainingCheeseRows;
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