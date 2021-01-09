package de.drake.tetris.config;

import java.awt.Color;

public class Config {
	
	/**
	 * Framerate in Aktionen pro Spieler pro Sekunde.
	 */
	public final static int FPS = 100;
	
	public final static int SIZE_TEXT = 15;
	
	public final static int SIZE_HEADER = 50;
	
	public final static Color COLOR_TEXT = Color.white;
	
	public final static Color COLOR_BACKGROUND = Color.black;
	
	public final static Color COLOR_TEXTBACKGROUND = Color.darkGray;
	
	/**
	 * Gibt an, wie h�ufig kleine Steine (< 4 Bl�cke) spawnen sollen.
	 */
	public static int stone_small;
	
	/**
	 * Gibt an, wie h�ufig "normale" Steine (aus 4 Bl�cken) spawnen sollen.
	 */
	public static int stone_regular;
	
	/**
	 * Gibt an, wie h�ufig gro�e Steine (aus 5 Bl�cken) spawnen sollen.
	 */
	public static int stone_large;
	
	/**
	 * Gibt an, wie h�ufig Bomben-Steine spawnen sollen.
	 */
	public static int stone_bomb;
	
	/**
	 * Gibt die maximale Steingr��e zur�ck.
	 */
	public static int getMaxSteinSize() {
		if (Config.stone_large > 0)
			return 5;
		if (Config.stone_regular > 0)
			return 4;
		if (Config.stone_small > 0)
			return 3;
		if (Config.stone_bomb > 0)
			return 3;
		throw new Error("Ung�ltige Steingr��e");
	}
	
	/**
	 * Die Breite des Spielfeldes (Anzahl der Spalten)
	 */
	public static int breite;
	
	/**
	 * Die sichtbare H�he des Spielfelds, d.h. die Anzahl der in der GUI dargestellten Zeilen.
	 * Die tats�chliche H�he des Spielfelds ist etwas gr��er, damit die Steine oben herausragen k�nnen.
	 */
	public static int hoehe;
	
	/**
	 * Die initiale Fallgeschwindigkeit der Steine in Felder pro Sekunde.
	 */
	public static double initialSpeed;
	
	/**
	 * Zeit, nach der beim Halten der "Links"-Taste das automatische "Weiterlaufen" des Steins nach
	 * links beginnen soll (in Millisekunden).
	 */
	public static int keyRepeatDelay;
	
	/**
	 * Geschwindigkeit, mit der beim Halten der "Links"-Taste der Stein nach links laufen soll
	 * (in Bewegungen je Sekunde).
	 */
	public static int keyRepeatSpeed;
	
}