package de.drake.tetris.config;

public class Config {
	
	/**
	 * Die Breite des Spielfeldes (Anzahl der Spalten)
	 */
	public static int breite = 10;
	
	/**
	 * Die sichtbare Höhe des Spielfelds, d.h. die Anzahl der in der GUI dargestellten Zeilen.
	 * Die tatsächliche Höhe des Spielfelds ist etwas größer, damit die Steine oben herausragen können.
	 */
	public static int hoehe = 20;
	
	/**
	 * Gibt an, ob Steine bestehend aus einem Block zulässig sind.
	 */
	public static boolean steinSize_1 = true; 
	
	/**
	 * Gibt an, ob Steine bestehend aus zwei Blöcken zulässig sind.
	 */
	public static boolean steinSize_2 = true; 
	
	/**
	 * Gibt an, ob Steine bestehend aus drei Blöcken zulässig sind.
	 */
	public static boolean steinSize_3 = true; 
	
	/**
	 * Gibt an, ob Steine bestehend aus vier Blöcken zulässig sind.
	 */
	public static boolean steinSize_4 = true; 
	
	/**
	 * Das Seitenverhältnis, mit dem die Tetrisfelder gezeichnet werden (Breite durch Höhe)
	 */
	public static double feld_seitenverhaeltnis = 1.5/1.;
	
	/**
	 * Framerate in Aktionen pro Spieler pro Sekunde.
	 */
	public static int fps = 100;
	
	/**
	 * Geschwindigkeit, mit der beim Halten der "Links"-Taste der Stein nach links laufen soll
	 * (in Bewegungen je Sekunde).
	 */
	public static int keyRepeatSpeed = 25;
	
	/**
	 * Zeit, nach der beim Halten der "Links"-Taste das automatische "Weiterlaufen" des Steins nach
	 * links beginnen soll (in Millisekunden).
	 */
	public static int keyRepeatDelay = 200;
	
	/**
	 * Gibt die maximale Steingröße zurück.
	 */
	public static int getMaxSteinSize() {
		if (Config.steinSize_4)
			return 4;
		if (Config.steinSize_3)
			return 3;
		if (Config.steinSize_2)
			return 2;
		return 1;
	}
	
}