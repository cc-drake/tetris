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
	 * Gibt an, wie häufig kleine Steine (< 4 Blöcke) spawnen sollen.
	 */
	public static int stone_small;
	
	/**
	 * Gibt an, wie häufig "normale" Steine (aus 4 Blöcken) spawnen sollen.
	 */
	public static int stone_regular;
	
	/**
	 * Gibt an, wie häufig große Steine (aus 5 Blöcken) spawnen sollen.
	 */
	public static int stone_large;
	
	/**
	 * Gibt an, wie häufig Bomben-Steine spawnen sollen.
	 */
	public static int stone_bomb;
	
	/**
	 * Gibt die maximale Steingröße zurück.
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
		throw new Error("Ungültige Steingröße");
	}
	
	/**
	 * Die Breite des Spielfeldes (Anzahl der Spalten)
	 */
	public static int breite;
	
	/**
	 * Die sichtbare Höhe des Spielfelds, d.h. die Anzahl der in der GUI dargestellten Zeilen.
	 * Die tatsächliche Höhe des Spielfelds ist etwas größer, damit die Steine oben herausragen können.
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
	
	/**
	 * Das Default-Zeitlimit in Sekunden, nach dem ein Spiel im Combat-Modus beendet wird. Das tatsächliche Zeitlimit wird über den GameMode konfiguriert.
	 */
	public static int timeLimitCombat;
	
	/**
	 * Das Default-Zeitlimit in Sekunden, nach dem ein Spiel im Race-Modus beendet wird. Das tatsächliche Zeitlimit wird über den GameMode konfiguriert.
	 */
	public static int timeLimitRace;
	
	/**
	 * Das Default-Zeitlimit in Sekunden, nach dem ein Spiel im Cheese-Modus beendet wird. Das tatsächliche Zeitlimit wird über den GameMode konfiguriert.
	 */
	public static int timeLimitCheese;
	
	/**
	 * Die Default-Beschleunigung nach Elimination einer Reihe in %. Die tatsächliche Beschleunigung wird über den GameMode konfiguriert.
	 */
	public static double speedIncreaseRow;
	
	/**
	 * Die Default-Beschleunigung je Sekunde in %. Die tatsächliche Beschleunigung wird über den GameMode konfiguriert.
	 */
	public static double speedIncreaseSec;
	
	/**
	 * Die Default-Logik, nach der fertige Reihen den Gegnern "draufgeworfen" werden. Die tatsächliche Logik wird über den GameMode konfiguriert.
	 */
	public static String combatType;
	
	/**
	 * Die Default-Anzahl der Reihen, die im Race-Modus zu eliminieren sind. Die tatsächliche Anzahl wird über den GameMode konfiguriert.
	 */
	public static int raceRows;
	
	/**
	 * Die Default-Anzahl der Reihen, die im Cheese-Modus zu eliminieren sind. Die tatsächliche Anzahl wird über den GameMode konfiguriert.
	 */
	public static int cheeseRows;
	
}