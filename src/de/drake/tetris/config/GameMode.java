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
	private final int timeLimit;
	
	/**
	 * Die Beschleunigung nach Elimination einer Reihe in %
	 */
	private final double speedIncreaseRow;
	
	/**
	 * Die Beschleunigung je Sekunde in %
	 */
	private final double speedIncreaseSec;
	
	/**
	 * Die Logik, nach der fertige Reihen den Gegnern "draufgeworfen" werden.
	 */
	private final String combatType;
	
	/**
	 * Anzahl der Reihen, die im Race-Modus zu eliminieren sind.
	 */
	private final int raceRows;
	
	/**
	 * Anzahl der Reihen, die im Cheese-Modus zu eliminieren sind.
	 */
	private final int cheeseRows;
	
	private GameMode(final String mode) {
		this.mode = mode;
		if (mode == GameMode.SOLITAER) {
			this.timeLimit = 0;
			this.speedIncreaseRow = Config.speedIncreaseRow;
			this.speedIncreaseSec = 0.;
			this.combatType = GameMode.COMBAT_PEACE;
			this.raceRows = 0;
			this.cheeseRows = 0;
		} else if (mode == GameMode.COMBAT) {
			this.timeLimit = Config.timeLimitCombat;
			this.speedIncreaseRow = 0.;
			this.speedIncreaseSec = Config.speedIncreaseSec;
			this.combatType = Config.combatType;
			this.raceRows = 0;
			this.cheeseRows = 0;
		} else if (mode == GameMode.RACE) {
			this.timeLimit = Config.timeLimitRace;
			this.speedIncreaseRow = 0.;
			this.speedIncreaseSec = 0.;
			this.combatType = GameMode.COMBAT_PEACE;
			this.raceRows = Config.raceRows;
			this.cheeseRows = 0;
		} else if (mode == GameMode.CHEESE) {
			this.timeLimit = Config.timeLimitCheese;
			this.speedIncreaseRow = 0.;
			this.speedIncreaseSec = 0.;
			this.combatType = GameMode.COMBAT_PEACE;
			this.raceRows = 0;
			this.cheeseRows = Config.cheeseRows;
		} else throw (new Error());
	}
	
	public static void createInstance(final String mode) {
		GameMode.instance = new GameMode(mode);
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