package de.drake.tetris.config;

public class GameMode {
	
	final public static String SOLITAER = "Solitär";
	
	final public static String COMBAT = "Combat";
	
	final public static String RACE = "Race";
	
	final public static String CHEESE = "Cheese";
	
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
	public static int cheeseRows = 9;
	
	/**
	 * Gibt an, welchen Modus das Spiel besitzt.
	 */
	public static String gameMode;
	
}
