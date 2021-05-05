package de.drake.tetris.model.stones;

import java.util.HashMap;
import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.util.Position;

/**
 * Ein 3x3 Winkel.
 */
class Stone_I5A extends DumbStone {
	
	Stone_I5A() {
		super(Asset.TEXTURE_RED);
	}
	
	@Override
	protected HashMap<Integer, HashSet<Position>> getRelativkoordinaten() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-1, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		map.put(3, relativkoordinaten);
		
		return map;
	}
	
	@Override
	public Stone clone() {
		return new Stone_I5A();
	}
	
}