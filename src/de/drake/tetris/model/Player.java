package de.drake.tetris.model;

import java.util.Random;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.model.animations.AnimationManager;
import de.drake.tetris.model.processes.Process;
import de.drake.tetris.model.spielfeld.Spielfeld;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.stones.StoneFactory;
import de.drake.tetris.model.util.Action;
import de.drake.tetris.model.util.PlayerStatus;

/**
 * Der Player verwaltet das Spielfeld eines Spielers und f�hrt Bewegungseingaben ("Links", "Rechts", "Drehen") aus.
 */
public class Player {
	
	/**
	 * Der Name des Spielers
	 */
	private final String name;
	
	/**
	 * Das aktuelle Spiel
	 */
	private final Game game;
	
	/**
	 * Das Spielfeld, in dem die Steine fallen.
	 */
	private final Spielfeld spielfeld;
	
	/**
	 * Der Zufallsgenerator, der die neuen Steine erzeugt
	 */
	private final StoneFactory steinFactory;
	
	/**
	 * Verwaltet alle derzeit aktiven Animationen.
	 */
	private final AnimationManager animationManager;
	
	/**
	 * Der Stein, der aktuell im Spielfeld f�llt.
	 */
	private Stone stone;
	
	/**
	 * Der Stein, der als n�chstes ins Spielfeld fallen wird.
	 */
	private Stone nextStone;
	
	/**
	 * Der aktuell ausgef�hrte Prozess. Null, wenn kein Prozess ausgef�hrt wird.
	 */
	private Process currentProcess = null;
	
	/**
	 * Anzahl der Fallvorg�nge pro Sekunde.
	 */
	private double speed;
	
	/**
	 * Der Laufzeitindex, an dem zuletzt ein Stein gefallen ist
	 */
	private long letzteFallzeit = 0;
	
	/**
	 * Der Laufzeitindex, an dem zuletzt eine Sekunde vergangen war (zur Speederh�hung)
	 */
	private long letzteSeczeit = 0;
	
	/**
	 * Die Spielzeit des Spielers in Nanosekunden.
	 */
	private long laufzeitNano = 0;
	
	/**
	 * Die Anzahl der Reihen, die fertiggestellt wurden.
	 */
	private int clearedRows = 0;
	
	/**
	 * Die Anzahl der Reihen, die dem Spieler "draufgeworfen" werden sollen
	 */
	private int pendingRows = 0;
	
	/**
	 * Die Anzahl der Steine, die gespawnt sind.
	 */
	private int spawnedStones = 0;
	
	/**
	 * Gibt den aktuellen Zustand des Spielers an.
	 */
	private PlayerStatus status = PlayerStatus.ACTIVE;
	
	/**
	 * Erzeugt einen neuen Spieler.
	 */
	public Player(final PlayerConfig config, final Game game, final long seed) {
		this.name = config.getName();
		this.speed = config.getInitialSpeed();
		this.game = game;
		Random random = new Random(seed);
		this.spielfeld = new Spielfeld(random.nextLong());
		this.steinFactory = new StoneFactory(random.nextLong());
		this.animationManager = new AnimationManager();
		this.nextStone = this.steinFactory.erzeugeRandomStein(this);
		this.spawnStone();
	}
	
	/**
	 * F�hrt die angegebene Bewegungsaktion aus, sofern der Spieler aktiv ist
	 */
	public void performMoveAction(final Action action) {
		
		if (!this.hasStatus(PlayerStatus.ACTIVE))
			return;
		
		switch (action) {
		case LINKS:
			this.stone.bewege(-1, 0, null);
			return;
		case RECHTS:
			this.stone.bewege(1, 0, null);
			return;
		case RUNTER:
			if (this.stone.bewege(0, 1, null) == false) {
				this.stone.detonate();
			}
			return;
		case GANZ_RUNTER:
			while(this.stone.bewege(0, 1, null));
			this.stone.detonate();
			return;
		case DREHUNG_UHRZEIGERSINN:
			if (!this.stone.bewege(0, 0, true))
				if (!this.stone.bewege(1, 0, true))
					this.stone.bewege(-1, 0, true);
			break;
		case DREHUNG_ENTGEGEN_UHRZEIGERSINN:
			if (!this.stone.bewege(0, 0, false))
				if (!this.stone.bewege(-1, 0, false))
					this.stone.bewege(1, 0, false);
			break;
		default:
			break;
		}
	}
	
	/**
	 * F�hrt zeitgesteuerte Vorg�nge aus, wie den n�chsten automatischen "Steinfall"
	 * oder die Aktualisierung der Zeit.
	 */
	public void tick() {
		
		// Tote Spieler ticken nicht
		if (this.isDead())
			return;
		
		// Laufzeit des Spielers aktualisieren
		this.laufzeitNano = game.getLaufzeitNano();
		
		// Zeitgesteuert "Runter" ausl�sen
		long ZeitProSteinfall = (long) (1000000000. / this.speed);
		if ((this.laufzeitNano - this.letzteFallzeit) >= ZeitProSteinfall) {
			this.letzteFallzeit += ZeitProSteinfall;
			this.performMoveAction(Action.RUNTER);
		}
		
		// Zeitgesteuert Speed erh�hen
		if ((this.laufzeitNano - this.letzteSeczeit) >= 1000000000.) {
			this.letzteSeczeit += 1000000000.;
			this.speed *= (1 + GameMode.getSpeedIncreaseSec() / 100.);
		}
		
		// Aktuellen Prozess aktualisieren
		if (this.hasStatus(PlayerStatus.PROCESSING)) {
			this.currentProcess.tick();
		}
		
	}
	
	public void rowsCompleted(final int amount) {
		this.clearedRows += amount;
		for (int i = 0; i < amount; i++) {
			this.speed *= (1 + GameMode.getSpeedIncreaseRow() / 100.);
		}
		
		int wurfreihen = 0;
		if (GameMode.getCombatType().equals(GameMode.COMBAT_CLASSIC)) {
			switch (amount) {
			case 0:
			case 1:
				wurfreihen = 0;
				break;
			case 2:
			case 3:
				wurfreihen = amount - 1;
				break;
			default:
				wurfreihen = amount;
			}
		}
		if (GameMode.getCombatType().equals(GameMode.COMBAT_BADASS)) {
			wurfreihen = amount;
		}
		
		for (Player player : this.game.getPlayers()) {
			if (player != this && (player.status == PlayerStatus.ACTIVE || player.status == PlayerStatus.PROCESSING))
				player.pendingRows += wurfreihen;
		}
	}
	
	public void spawnStone() {
		this.spawnedStones++;
		this.stone = this.nextStone;
		this.nextStone = this.steinFactory.erzeugeRandomStein(this);
		this.status = PlayerStatus.ACTIVE;
		if (this.spielfeld.isBlocked(this.stone.getPositionen())) {
			this.status = PlayerStatus.STUCK;
		}
	}
	
	public void startProcess(final Process process) {
		this.status = PlayerStatus.PROCESSING;
		this.currentProcess = process;
	}
	
	void setStatus(final PlayerStatus state) {
		this.status = state;
	}
	
	/**
	 * Gibt den aktuellen Status dieses Spielers zur�ck.
	 */
	public PlayerStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Gibt zur�ck, ob der Status des Spielers dem angefragten Zustand entspricht.
	 */
	public boolean hasStatus(final PlayerStatus state) {
		return this.status == state;
	}
	
	/**
	 * Gibt zur�ck, ob der Spieler gestorben ist.
	 */
	public boolean isDead() {
		return this.status != PlayerStatus.ACTIVE && this.status != PlayerStatus.PROCESSING;
	}

	/**
	 * Gibt das Spielfeld des Spielers zur�ck.
	 */
	public Spielfeld getSpielfeld() {
		return this.spielfeld;
	}
	
	public AnimationManager getAnimationManager() {
		return this.animationManager;
	}
	
	public void destroyStone() {
		this.stone = null;
	}
	
	/**
	 * Gibt den aktuellen Stein zur�ck.
	 */
	public Stone getStone() {
		return this.stone;
	}
	
	/**
	 * Gibt den n�chsten Stein zur�ck.
	 */
	public Stone getNextStein() {
		return this.nextStone;
	}
	
	/**
	 * Gibt den Namen des Spielers zur�ck.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gibt die vergangene Zeit in Nanosekunden zur�ck.
	 */
	public long getLaufzeit() {
		return this.laufzeitNano;
	}
	
	/**
	 * Gibt die vergangene Zeit in Sekunden zur�ck.
	 */
	public int getVergangeneZeitSec() {
		return (int) (this.laufzeitNano / 1000000000.);
	}
	
	/**
	 * Gibt die verbleibende Spielzeit in Sekunden zur�ck.
	 */
	public int getVerbleibendeZeitSec() {
		return GameMode.getTimeLimit() - this.getVergangeneZeitSec();
	}
	
	/**
	 * Gibt die Zahl der gespawnten Steine zur�ck.
	 */
	public int getSpawnedStones() {
		return this.spawnedStones;
	}
	
	/**
	 * Gibt die fertigen Reihen zur�ck.
	 */
	public int getClearedRows() {
		return this.clearedRows;
	}
	
	/**
	 * Gibt die verbleibende Zahl zu eliminierender Race-Reihen zur�ck.
	 */
	public int getRemainingRaceRows() {
		return GameMode.getRaceRows() - this.clearedRows;
	}
	
	/**
	 * Gibt die verbleibende Zahl zu eliminierender K�se-Reihen zur�ck.
	 */
	public int getRemainingCheeseRows() {
		return this.spielfeld.getRemainingCheeseRows();
	}
	
	public void reducePendingRows(final int amount) {
		this.pendingRows -= amount;
	}

	public int getPendingRows() {
		return this.pendingRows;
	}
	
}