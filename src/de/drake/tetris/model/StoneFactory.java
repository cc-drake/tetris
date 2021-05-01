package de.drake.tetris.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import de.drake.tetris.model.util.StoneType;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.util.Position;

/**
 * Erzeugt zufällige Tetris-Steine
 */
class StoneFactory {
	
	private final static int STONE_SMALL = 1;
	private final static int STONE_REGULAR = 2;
	private final static int STONE_LARGE = 3;
	private final static int STONE_BOMB = 4;
	
	/**
	 * Zufallsgenerator zur Erzeugung zufälliger Steine.
	 */
	private final Random random;
	
	/**
	 * Grundgesamtheit aller Steinarten
	 */
	private final ArrayList<Integer> grundgesamtheit = new ArrayList<Integer>();
	
	/**
	 * Zuordnung der Steinarten zu den jeweiligen Grundgesamtheiten
	 */
	private final HashMap<Integer, ArrayList<Stone>> steinart2Steine = new HashMap<Integer, ArrayList<Stone>>();
	
	/**
	 * Erzeugt eine neue StoneFactory.
	 * 
	 * @param seed Der Startwert des Zufallsgenerators.
	 */
	StoneFactory(final long seed) {
		this.random = new Random(seed);
		for (int i = 0; i < Config.stone_small; i++) {
			this.grundgesamtheit.add(StoneFactory.STONE_SMALL);
		}
		for (int i = 0; i < Config.stone_regular; i++) {
			this.grundgesamtheit.add(StoneFactory.STONE_REGULAR);
		}
		for (int i = 0; i < Config.stone_large; i++) {
			this.grundgesamtheit.add(StoneFactory.STONE_LARGE);
		}
		for (int i = 0; i < Config.stone_bomb; i++) {
			this.grundgesamtheit.add(StoneFactory.STONE_BOMB);
		}
		
		ArrayList<Stone> ggSmall = new ArrayList<Stone>();
		ggSmall.add(this.create_Stein_1());
		ggSmall.add(this.create_Stein_2());
		ggSmall.add(this.create_Stein_31());
		ggSmall.add(this.create_Stein_32());
		this.steinart2Steine.put(StoneFactory.STONE_SMALL, ggSmall);
		
		ArrayList<Stone> ggRegular = new ArrayList<Stone>();
		ggRegular.add(this.create_Stein_41());
		ggRegular.add(this.create_Stein_42());
		ggRegular.add(this.create_Stein_43());
		ggRegular.add(this.create_Stein_44());
		ggRegular.add(this.create_Stein_45());
		ggRegular.add(this.create_Stein_46());
		ggRegular.add(this.create_Stein_47());
		this.steinart2Steine.put(StoneFactory.STONE_REGULAR, ggRegular);
		
		ArrayList<Stone> ggLarge = new ArrayList<Stone>();
		ggLarge.add(this.create_Stein_501());
		ggLarge.add(this.create_Stein_502());
		ggLarge.add(this.create_Stein_503());
		ggLarge.add(this.create_Stein_504());
		ggLarge.add(this.create_Stein_505());
		ggLarge.add(this.create_Stein_506());
		ggLarge.add(this.create_Stein_507());
		ggLarge.add(this.create_Stein_508());
		ggLarge.add(this.create_Stein_509());
		ggLarge.add(this.create_Stein_510());
		ggLarge.add(this.create_Stein_511());
		ggLarge.add(this.create_Stein_512());
		ggLarge.add(this.create_Stein_513());
		ggLarge.add(this.create_Stein_514());
		ggLarge.add(this.create_Stein_515());
		ggLarge.add(this.create_Stein_516());
		ggLarge.add(this.create_Stein_517());
		ggLarge.add(this.create_Stein_518());
		this.steinart2Steine.put(StoneFactory.STONE_LARGE, ggLarge);
		
		ArrayList<Stone> ggBomb = new ArrayList<Stone>();
		ggBomb.add(this.create_BombSquare());
		ggBomb.add(this.create_BombHorizontal());
		ggBomb.add(this.create_BombVertical());
		this.steinart2Steine.put(StoneFactory.STONE_BOMB, ggBomb);
		
	}
	
	/**
	 * Erzeugt einen zufälligen Stein.
	 */
	Stone erzeugeRandomStein() {
		int steinart = this.grundgesamtheit.get(this.random.nextInt(this.grundgesamtheit.size()));
		ArrayList<Stone> gg = this.steinart2Steine.get(steinart);
		return new Stone(gg.get(this.random.nextInt(gg.size())));
	}
	
	/**
	 * Ein 1x1 Stein.
	 */
	private Stone create_Stein_1() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(1);
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		return new Stone(StoneType.RED, map);
	}
	
	/**
	 * Eine 1x2 Stange.
	 */
	private Stone create_Stein_2() {
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
		
		return new Stone(StoneType.RED, map);
	}
	
	/**
	 * Eine 1x3 Stange.
	 */
	private Stone create_Stein_31() {
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
		
		return new Stone(StoneType.RED, map);
	}
	
	/**
	 * Ein 2x2 L.
	 */
	private Stone create_Stein_32() {
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
		
		return new Stone(StoneType.YELLOW, map);
	}
	
	/**
	 * Ein 3x2 L.
	 */
	private Stone create_Stein_41() {
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
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 3x2 T.
	 */
	private Stone create_Stein_42() {
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
		
		return new Stone(StoneType.YELLOW, map);
	}
	
	/**
	 * Ein 3x2 umgekehrtes L.
	 */
	private Stone create_Stein_43() {
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
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 3x2 Z.
	 */
	private Stone create_Stein_44() {
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
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 3x2 S.
	 */
	private Stone create_Stein_45() {
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
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 2x2 Block.
	 */
	private Stone create_Stein_46() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(4);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		map.put(0, relativkoordinaten);
		
		return new Stone(StoneType.YELLOW, map);
	}
	
	/**
	 * Eine 1x4 Stange.
	 */
	private Stone create_Stein_47() {
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
		
		return new Stone(StoneType.RED, map);
	}
	
	/**
	 * Eine 1x5 Stange.
	 */
	private Stone create_Stein_501() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(2, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -2));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(1, relativkoordinaten);
		
		return new Stone(StoneType.RED, map);
	}
	
	/**
	 * Ein 4x2 L.
	 */
	private Stone create_Stein_502() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(1, 0));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 2));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 4x2 umgekehrtes L.
	 */
	private Stone create_Stein_503() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(1, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		relativkoordinaten.add(new Position(-1, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, -1));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 4x2 L mit Fuss.
	 */
	private Stone create_Stein_504() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(0, 0));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 1));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 4x2 umgekehrtes L mit Fuss.
	 */
	private Stone create_Stein_505() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		relativkoordinaten.add(new Position(-1, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 0));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 3x3 Winkel.
	 */
	private Stone create_Stein_506() {
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
		
		return new Stone(StoneType.RED, map);
	}
	
	/**
	 * Ein 3x3 T.
	 */
	private Stone create_Stein_507() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(1, 2));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 2));
		relativkoordinaten.add(new Position(1, 2));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.YELLOW, map);
	}
	
	/**
	 * Ein 3x3 Plus.
	 */
	private Stone create_Stein_508() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(0, relativkoordinaten);
		
		return new Stone(StoneType.YELLOW, map);
	}
	
	/**
	 * Ein 3x2 U.
	 */
	private Stone create_Stein_509() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, -1));
		relativkoordinaten.add(new Position(1, 0));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(1, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.YELLOW, map);
	}
	
	/**
	 * Ein 3x3 Chaos-L.
	 */
	private Stone create_Stein_510() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		relativkoordinaten.add(new Position(1, 2));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 3x3 umgekehrtes Chaos-L.
	 */
	private Stone create_Stein_511() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(1, 2));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 3x2 Klumpen-S.
	 */
	private Stone create_Stein_512() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 3x2 Klumpen-Z.
	 */
	private Stone create_Stein_513() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(1, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 1));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 3x3 Z.
	 */
	private Stone create_Stein_514() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		relativkoordinaten.add(new Position(1, 2));
		map.put(1, relativkoordinaten);
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 3x3 S.
	 */
	private Stone create_Stein_515() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(2);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(1, 2));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 2));
		map.put(1, relativkoordinaten);
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 4x2 breites S.
	 */
	private Stone create_Stein_516() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-1, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-2, 1));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.BLUE, map);
	}
	
	/**
	 * Ein 4x2 breites Z.
	 */
	private Stone create_Stein_517() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-2, 0));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, -1));
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.GREEN, map);
	}
	
	/**
	 * Ein 3x3 W.
	 */
	private Stone create_Stein_518() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(4);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(-1, 2));
		map.put(0, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(0, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(1, 2));
		map.put(1, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(1, 0));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(1, 1));
		relativkoordinaten.add(new Position(-1, 2));
		relativkoordinaten.add(new Position(0, 2));
		map.put(2, relativkoordinaten);
		
		relativkoordinaten = new HashSet<Position>(5);
		relativkoordinaten.add(new Position(-1, 0));
		relativkoordinaten.add(new Position(-1, 1));
		relativkoordinaten.add(new Position(0, 1));
		relativkoordinaten.add(new Position(0, 2));
		relativkoordinaten.add(new Position(1, 2));
		map.put(3, relativkoordinaten);
		
		return new Stone(StoneType.YELLOW, map);
	}
	
	/**
	 * Eine 1x1 Square-Bombe.
	 */
	private Stone create_BombSquare() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(1);
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		return new Stone(StoneType.BOMB_SQUARE, map);
	}
	
	/**
	 * Eine 1x1 horizontale Bombe.
	 */
	private Stone create_BombHorizontal() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(1);
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		return new Stone(StoneType.BOMB_HORIZONTAL, map);
	}
	
	/**
	 * Eine 1x1 vertikale Bombe.
	 */
	private Stone create_BombVertical() {
		HashMap<Integer, HashSet<Position>> map = new HashMap<Integer, HashSet<Position>>(1);
		HashSet<Position> relativkoordinaten;
		
		relativkoordinaten = new HashSet<Position>(1);
		relativkoordinaten.add(new Position(0, 0));
		map.put(0, relativkoordinaten);
		
		return new Stone(StoneType.BOMB_VERTICAL, map);
	}
	
}