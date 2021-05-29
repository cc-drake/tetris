package de.drake.tetris.model.stones;

import java.util.HashMap;
import java.util.HashSet;

import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.model.util.Position;

abstract class OneBlockStone extends Stone {
	
	OneBlockStone(final BlockTexture texture) {
		super(texture);
	}
	
	@Override
	protected HashMap<Integer, HashSet<Position>> getRelativkoordinaten() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(1);
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		return map;
	}
	
	@Override
	public boolean bewege(final int x, final int y, final Boolean imUhrzeigersinn) {
		if (imUhrzeigersinn == null) {
			return super.bewege(x, y, null);
		}
		return true;
	}
	
	protected final Position getPosition() {
		return this.getPositionen().iterator().next();
	}
	
}