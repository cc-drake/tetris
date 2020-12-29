package de.drake.tetris.config;

public class GameMode {
	
	final public static String SOLITAER = "Solitär";
	
	final public static String COMBAT = "Combat";
	
	final public static String RACE = "Race";
	
	final public static String CHEESE = "Cheese";
	
	final public static String COMBAT_CLASSIC = "Classic";
	
	final public static String COMBAT_BADASS = "Badass";
	
	final public static String COMBAT_PEACE = "Peace";
	
	/**
	 * Gibt an, welchen Modus das Spiel besitzt.
	 */
	public static String gameMode;
	
	/**
	 * Das Zeitlimit in Sekunden, nach dem das Spiel beendet wird.
	 */
	public static int timeLimit;
	
	/**
	 * Die Logik, nach der fertige Reihen den Gegnern "draufgeworfen" werden.
	 */
	public static String combatType = GameMode.COMBAT_PEACE;
	
	/**
	 * Die Beschleunigung nach Elimination einer Reihe in %
	 */
	public static double speedIncreaseRow;
	
	/**
	 * Die Beschleunigung je Sekunde in %
	 */
	public static double speedIncreaseSec;
	
	/**
	 * Anzahl der Reihen, die im Race-Modus zu eliminieren sind.
	 */
	public static int raceRows;
	
	/**
	 * Anzahl der Reihen, die im Cheese-Modus zu eliminieren sind.
	 */
	public static int cheeseRows;
	
}
