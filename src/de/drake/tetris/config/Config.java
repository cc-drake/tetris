package de.drake.tetris.config;

import de.drake.tetris.util.GameMode;

public class Config {
	
	/**
	 * Die Breite des Spielfeldes (Anzahl der Spalten)
	 */
	public static int breite = 10;
	
	/**
	 * Die sichtbare H�he des Spielfelds, d.h. die Anzahl der in der GUI dargestellten Zeilen.
	 * Die tats�chliche H�he des Spielfelds ist etwas gr��er, damit die Steine oben herausragen k�nnen.
	 */
	public static int hoehe = 20;
	
	/**
	 * Gibt an, ob Steine bestehend aus einem Block zul�ssig sind.
	 */
	public static boolean steinSize_1 = true; 
	
	/**
	 * Gibt an, ob Steine bestehend aus zwei Bl�cken zul�ssig sind.
	 */
	public static boolean steinSize_2 = true; 
	
	/**
	 * Gibt an, ob Steine bestehend aus drei Bl�cken zul�ssig sind.
	 */
	public static boolean steinSize_3 = true; 
	
	/**
	 * Gibt an, ob Steine bestehend aus vier Bl�cken zul�ssig sind.
	 */
	public static boolean steinSize_4 = true; 
	
	/**
	 * Das Seitenverh�ltnis, mit dem die Tetrisfelder gezeichnet werden (Breite durch H�he)
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
	 * Das Zeitlimit in Sekunden, nach dem das Spiel beendet wird.
	 */
	public static int timeLimit = 0;
	
	/**
	 * Die Beschleunigung nach Elimination einer Reihe in %
	 */
	public static double speedIncreaseRow = 1.;
	
	/**
	 * Die Beschleunigung je Sekunde in %
	 */
	public static double speedIncreaseSec = 0.5;
	
	/**
	 * Anzahl der Reihen, die im Race-Modus zu eliminieren sind.
	 */
	public static int raceRows = 50;
	
	/**
	 * Anzahl der Reihen, die im Cheese-Modus zu eliminieren sind.
	 */
	public static int cheeseRows = 15;
	
	/**
	 * Gibt an, welchen Modus das Spiel besitzt.
	 */
	public static GameMode gameMode;
	
	/**
	 * Gibt die maximale Steingr��e zur�ck.
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