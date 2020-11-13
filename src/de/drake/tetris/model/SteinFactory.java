package de.drake.tetris.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.util.Color;
import de.drake.tetris.model.util.Position;

/**
 * Erzeugt zufällige Tetris-Steine
 */
class SteinFactory {
	
	private final static int STONE_SMALL = 1;
	private final static int STONE_REGULAR = 2;
	private final static int STONE_LARGE = 3;
	private final static int STONE_BOMB = 4;
	
	/**
	 * Zufallsgenerator zur Erzeugung zufälliger Steine.
	 */
	private final Random random;
	
	/**
	 * Grundgesamtheit aller Steintypen
	 */
	private final ArrayList<Integer> grundgesamtheit = new ArrayList<Integer>();
	
	/**
	 * Zuordnung der Steintypen zu den jeweiligen Grundgesamtheiten
	 */
	private final HashMap<Integer, ArrayList<Stein>> steintyp2Steine = new HashMap<Integer, ArrayList<Stein>>();
	
	/**
	 * Erzeugt eine neue SteinFactory.
	 * 
	 * @param seed Der Startwert des Zufallsgenerators.
	 */
	SteinFactory(final long seed) {
		this.random = new Random(seed);
		for (int i = 0; i < Config.stone_small; i++) {
			this.grundgesamtheit.add(SteinFactory.STONE_SMALL);
		}
		for (int i = 0; i < Config.stone_regular; i++) {
			this.grundgesamtheit.add(SteinFactory.STONE_REGULAR);
		}
		for (int i = 0; i < Config.stone_large; i++) {
			this.grundgesamtheit.add(SteinFactory.STONE_LARGE);
		}
		for (int i = 0; i < Config.stone_bomb; i++) {
			this.grundgesamtheit.add(SteinFactory.STONE_BOMB);
		}
		
		ArrayList<Stein> ggSmall = new ArrayList<Stein>();
		ggSmall.add(this.create_Stein_1());
		ggSmall.add(this.create_Stein_2());
		ggSmall.add(this.create_Stein_31());
		ggSmall.add(this.create_Stein_32());
		this.steintyp2Steine.put(SteinFactory.STONE_SMALL, ggSmall);
		
		ArrayList<Stein> ggRegular = new ArrayList<Stein>();
		ggRegular.add(this.create_Stein_41());
		ggRegular.add(this.create_Stein_42());
		ggRegular.add(this.create_Stein_43());
		ggRegular.add(this.create_Stein_44());
		ggRegular.add(this.create_Stein_45());
		ggRegular.add(this.create_Stein_46());
		ggRegular.add(this.create_Stein_47());
		this.steintyp2Steine.put(SteinFactory.STONE_REGULAR, ggRegular);
		
		ArrayList<Stein> ggLarge = new ArrayList<Stein>();//TODO
		this.steintyp2Steine.put(SteinFactory.STONE_LARGE, ggLarge);
		
		ArrayList<Stein> ggBomb = new ArrayList<Stein>();//TODO
		this.steintyp2Steine.put(SteinFactory.STONE_BOMB, ggBomb);
		
	}
	
	/**
	 * Erzeugt einen zufälligen Stein.
	 */
	Stein erzeugeRandomStein() {
		int steintyp = this.grundgesamtheit.get(this.random.nextInt(this.grundgesamtheit.size()));
		ArrayList<Stein> gg = this.steintyp2Steine.get(steintyp);
		return new Stein(gg.get(this.random.nextInt(gg.size())));
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