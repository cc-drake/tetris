package de.drake.tetris.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import de.drake.tetris.config.Config;
import de.drake.tetris.util.Color;
import de.drake.tetris.util.Position;

/**
 * Erzeugt zufällige Tetris-Steine
 */
class SteinFactory {
	
	/**
	 * Zufallsgenerator zur Erzeugung zufälliger Steine.
	 */
	private final Random random;
	
	/**
	 * Grundgesamtheit aller erzeugbaren Steine
	 */
	private final ArrayList<Stein> grundgesamtheit = new ArrayList<Stein>();
	
	/**
	 * Erzeugt eine neue SteinFactory.
	 * 
	 * @param seed Der Startwert des Zufallsgenerators.
	 */
	SteinFactory(final long seed) {
		this.random = new Random(seed);
		if (Config.steinSize_1) {
			this.grundgesamtheit.add(this.create_Stein_1());
		}
		if (Config.steinSize_2) {
			this.grundgesamtheit.add(this.create_Stein_2());
		}
		if (Config.steinSize_3) {
			this.grundgesamtheit.add(this.create_Stein_31());
			this.grundgesamtheit.add(this.create_Stein_32());
		}
		if (Config.steinSize_4) {
			this.grundgesamtheit.add(this.create_Stein_41());
			this.grundgesamtheit.add(this.create_Stein_42());
			this.grundgesamtheit.add(this.create_Stein_43());
			this.grundgesamtheit.add(this.create_Stein_44());
			this.grundgesamtheit.add(this.create_Stein_45());
			this.grundgesamtheit.add(this.create_Stein_46());
			this.grundgesamtheit.add(this.create_Stein_47());
		}
	}
	
	/**
	 * Erzeugt einen zufälligen Stein.
	 */
	Stein erzeugeRandomStein() {
		return new Stein(this.grundgesamtheit.get(this.random.nextInt(this.grundgesamtheit.size())));
	}
	
	/**
	 * Ein 1x1 Stein.
	 */
	private Stein create_Stein_1() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(1);
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		return new Stein(Color.ROT, map);
	}
	
	/**
	 * Eine 1x2 Stange.
	 */
	private Stein create_Stein_2() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(2);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(2);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		map.put(1, relativkoordinaten);
		
		return new Stein(Color.ROT, map);
	}
	
	/**
	 * Eine 1x3 Stange.
	 */
	private Stein create_Stein_31() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(3);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(3);
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, -1));
		map.put(1, relativkoordinaten);
		
		return new Stein(Color.ROT, map);
	}
	
	/**
	 * Ein 2x2 L.
	 */
	private Stein create_Stein_32() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(3);
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(3);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(3);
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(3);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 0));
		map.put(3, relativkoordinaten);
		
		return new Stein(Color.GELB, map);
	}
	
	/**
	 * Ein 3x2 L.
	 */
	private Stein create_Stein_41() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(1, -1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(3, relativkoordinaten);
		
		return new Stein(Color.BLAU, map);
	}
	
	/**
	 * Ein 3x2 T.
	 */
	private Stein create_Stein_42() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, -1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, -1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(1, 0));
		map.put(3, relativkoordinaten);
		
		return new Stein(Color.GELB, map);
	}
	
	/**
	 * Ein 3x2 umgekehrtes L.
	 */
	private Stein create_Stein_43() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, -1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, -1));
		map.put(3, relativkoordinaten);
		
		return new Stein(Color.GRÜN, map);
	}
	
	/**
	 * Ein 3x2 Z.
	 */
	private Stein create_Stein_44() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(1, -1));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		return new Stein(Color.GRÜN, map);
	}
	
	/**
	 * Ein 3x2 S.
	 */
	private Stein create_Stein_45() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(1, 1));
		map.put(1, relativkoordinaten);
		
		return new Stein(Color.BLAU, map);
	}
	
	/**
	 * Ein 2x2 Block.
	 */
	private Stein create_Stein_46() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		map.put(0, relativkoordinaten);
		
		return new Stein(Color.GELB, map);
	}
	
	/**
	 * Eine 1x4 Stange.
	 */
	private Stein create_Stein_47() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(0, -2));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		return new Stein(Color.ROT, map);
	}
}