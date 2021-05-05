package de.drake.tetris.model.stones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.Spielfeld;

/**
 * Erzeugt zufällige Tetris-Steine
 */
public class StoneFactory {
	
	/**
	 * Zufallsgenerator zur Erzeugung zufälliger Steine.
	 */
	private final Random random;
	
	/**
	 * Grundgesamtheit aller Steinarten
	 */
	private final ArrayList<StoneGroup> grundgesamtheit = new ArrayList<StoneGroup>();
	
	/**
	 * Zuordnung der Steinarten zu den jeweiligen Grundgesamtheiten
	 */
	private final HashMap<StoneGroup, ArrayList<Stone>> steinart2Steine = new HashMap<StoneGroup, ArrayList<Stone>>();
	
	/**
	 * Erzeugt eine neue StoneFactory.
	 * 
	 * @param seed Der Startwert des Zufallsgenerators.
	 */
	public StoneFactory(final long seed) {
		this.random = new Random(seed);
		for (int i = 0; i < Config.stone_small; i++) {
			this.grundgesamtheit.add(StoneGroup.STONE_SMALL);
		}
		for (int i = 0; i < Config.stone_regular; i++) {
			this.grundgesamtheit.add(StoneGroup.STONE_REGULAR);
		}
		for (int i = 0; i < Config.stone_large; i++) {
			this.grundgesamtheit.add(StoneGroup.STONE_LARGE);
		}
		for (int i = 0; i < Config.stone_bomb; i++) {
			this.grundgesamtheit.add(StoneGroup.STONE_BOMB);
		}
		
		ArrayList<Stone> ggSmall = new ArrayList<Stone>();
		ggSmall.add(new Stone_I1());
		ggSmall.add(new Stone_I2());
		ggSmall.add(new Stone_I3());
		ggSmall.add(new Stone_T3());
		this.steinart2Steine.put(StoneGroup.STONE_SMALL, ggSmall);
		
		ArrayList<Stone> ggRegular = new ArrayList<Stone>();
		ggRegular.add(new Stone_I4());
		ggRegular.add(new Stone_J4());
		ggRegular.add(new Stone_L4());
		ggRegular.add(new Stone_O4());
		ggRegular.add(new Stone_T4());
		ggRegular.add(new Stone_S4());
		ggRegular.add(new Stone_Z4());
		this.steinart2Steine.put(StoneGroup.STONE_REGULAR, ggRegular);
		
		ArrayList<Stone> ggLarge = new ArrayList<Stone>();
		ggLarge.add(new Stone_I5());
		ggLarge.add(new Stone_I5A());
		ggLarge.add(new Stone_J5());
		ggLarge.add(new Stone_L5());
		ggLarge.add(new Stone_J5A());
		ggLarge.add(new Stone_L5A());
		ggLarge.add(new Stone_J5B());
		ggLarge.add(new Stone_L5B());
		ggLarge.add(new Stone_T5());
		ggLarge.add(new Stone_U5());
		ggLarge.add(new Stone_W5());
		ggLarge.add(new Stone_X5());
		ggLarge.add(new Stone_S5());
		ggLarge.add(new Stone_Z5());
		ggLarge.add(new Stone_S5A());
		ggLarge.add(new Stone_Z5A());
		ggLarge.add(new Stone_S5B());
		ggLarge.add(new Stone_Z5B());

		this.steinart2Steine.put(StoneGroup.STONE_LARGE, ggLarge);
		
		ArrayList<Stone> ggBomb = new ArrayList<Stone>();
		ggBomb.add(new SquareBomb());
		ggBomb.add(new HorizontalBomb());
		ggBomb.add(new VerticalBomb());
		this.steinart2Steine.put(StoneGroup.STONE_BOMB, ggBomb);
		
	}
	
	/**
	 * Erzeugt einen zufälligen Stein.
	 */
	public Stone erzeugeRandomStein(final Spielfeld spielfeld) {
		StoneGroup steinart = this.grundgesamtheit.get(this.random.nextInt(this.grundgesamtheit.size()));
		ArrayList<Stone> gg = this.steinart2Steine.get(steinart);
		Stone stone = gg.get(this.random.nextInt(gg.size())).clone();
		stone.setSpielfeld(spielfeld);
		return stone;
	}
	
}