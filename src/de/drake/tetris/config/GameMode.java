package de.drake.tetris.config;

public class GameMode {
	
	final public static String SOLITAER = "Solitär";
	
	final public static String COMBAT = "Combat";
	
	final public static String RACE = "Race";
	
	final public static String CHEESE = "Cheese";
	
	final public static String COMBAT_CLASSIC = "Classic";
	
	final public static String COMBAT_BADASS = "Badass";
	
	final public static String COMBAT_PEACE = "Peace";
	
	private static GameMode instance;
	
	/**
	 * Gibt an, welchen Modus das Spiel besitzt.
	 */
	private final String mode;
	
	/**
	 * Das Zeitlimit in Sekunden, nach dem das Spiel beendet wird.
	 */
	private int timeLimit;
	
	/**
	 * Die Beschleunigung nach Elimination einer Reihe in %
	 */
	private double speedIncreaseRow;
	
	/**
	 * Die Beschleunigung je Sekunde in %
	 */
	private double speedIncreaseSec;
	
	/**
	 * Die Logik, nach der fertige Reihen den Gegnern "draufgeworfen" werden.
	 */
	private String combatType;
	
	/**
	 * Anzahl der Reihen, die im Race-Modus zu eliminieren sind.
	 */
	private int raceRows;
	
	/**
	 * Anzahl der Reihen, die im Cheese-Modus zu eliminieren sind.
	 */
	private int cheeseRows;
	
	public GameMode(String mode) {
		this.mode = mode;
		GameMode.instance = this;
	}

	public void setTimeLimit(final int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public void setSpeedIncreaseRow(final double speedIncreaseRow) {
		this.speedIncreaseRow = speedIncreaseRow;
	}

	public void setSpeedIncreaseSec(final double speedIncreaseSec) {
		this.speedIncreaseSec = speedIncreaseSec;
	}

	public void setCombatType(final String combatType) {
		this.combatType = combatType;
	}
	
	public void setRaceRows(final int raceRows) {
		this.raceRows = raceRows;
	}

	public void setCheeseRows(final int cheeseRows) {
		this.cheeseRows = cheeseRows;
	}
	
	public static GameMode getInstance() {
		return GameMode.instance;
	}
	
	public static String getMode() {
		return GameMode.instance.mode;
	}
	
	public static int getTimeLimit() {
		return GameMode.instance.timeLimit;
	}
	
	public static double getSpeedIncreaseRow() {
		return GameMode.instance.speedIncreaseRow;
	}
	
	public static double getSpeedIncreaseSec() {
		return GameMode.instance.speedIncreaseSec;
	}
	
	public static String getCombatType() {
		return GameMode.instance.combatType;
	}
	
	public static int getRaceRows() {
		return GameMode.instance.raceRows;
	}
	
	public static int getCheeseRows() {
		return GameMode.instance.cheeseRows;
	}
	
}