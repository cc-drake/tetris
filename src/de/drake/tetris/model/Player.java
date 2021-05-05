package de.drake.tetris.model;

import java.util.Random;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.stones.StoneFactory;
import de.drake.tetris.model.util.Action;
import de.drake.tetris.model.util.PlayerStatus;
import de.drake.tetris.model.util.Position;

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
	 * Der Stein, der aktuell im Spielfeld f�llt.
	 */
	private Stone stein;
	
	/**
	 * Der Stein, der als n�chstes ins Spielfeld fallen wird.
	 */
	private Stone n�chsterStein;
	
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
	private int fertigeReihen = 0;
	
	/**
	 * Die Anzahl der Reihen, die dem Spieler "draufgeworfen" werden sollen
	 */
	private int wartendeReihen = 0;
	
	/**
	 * Die Anzahl der Steine, die gespawnt sind.
	 */
	private int anzahlSteine = 0;
	
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
		this.n�chsterStein = this.steinFactory.erzeugeRandomStein(this.spielfeld);
		this.initialisiereNaechstenStein();
	}
	
	/**
	 * F�hrt die angegebene Bewegungsaktion aus.
	 */
	public void performMoveAction(final Action action) {

		switch (action) {
		case LINKS:
			stein.bewege(-1, 0, null);
			break;
		case RECHTS:
			stein.bewege(1, 0, null);
			break;
		case RUNTER:
			if (stein.bewege(0, 1, null) == false) {
				this.setzeSteinAb();
			}
			break;
		case GANZ_RUNTER:
			while(stein.bewege(0, 1, null));
			this.setzeSteinAb();
			break;
		case DREHUNG_UHRZEIGERSINN:
			if (!stein.bewege(0, 0, true))
				if (!stein.bewege(1, 0, true))
					stein.bewege(-1, 0, true);
			break;
		case DREHUNG_ENTGEGEN_UHRZEIGERSINN:
			if (!stein.bewege(0, 0, false))
				if (!stein.bewege(-1, 0, false))
					stein.bewege(1, 0, false);
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
		
		// Laufzeit des Spielers aktualisieren, sofern dieser noch lebt
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
		
	}
	
	/**
	 * Setzt einen Stein ab, d.h. der Stein detoniert, fertige Reihen werden entfernt,
	 * wartende Reihen hinzugef�gt und ein neuer Stein spawnt.
	 */
	private void setzeSteinAb() {
		this.stein.detonate();
		int entfernteReihen = this.spielfeld.entferneFertigeReihen();
		this.fertigeReihen += entfernteReihen;
		for (int i = 0; i < entfernteReihen; i++) {
			this.speed *= (1 + GameMode.getSpeedIncreaseRow() / 100.);
		}
		this.draufwerfen(entfernteReihen);
		this.spielfeld.generateCheeseRows(this.wartendeReihen);
		if (this.wartendeReihen >= 4) {
			Asset.SOUND_ADDFOUR.play();
		} else if (this.wartendeReihen > 0) {
			Asset.SOUND_ADD.play();
		}
		this.wartendeReihen = 0;
		this.initialisiereNaechstenStein();
	}
	
	/**
	 * Wirft den anderen Spielern einige Reihen drauf.
	 */
	private void draufwerfen(final int entfernteReihen) {
		int wurfreihen = 0;
		if (GameMode.getCombatType().equals(GameMode.COMBAT_CLASSIC)) {
			switch (entfernteReihen) {
			case 0:
			case 1:
				wurfreihen = 0;
				break;
			case 2:
			case 3:
				wurfreihen = entfernteReihen - 1;
				break;
			default:
				wurfreihen = entfernteReihen;
			}
		}
		if (GameMode.getCombatType().equals(GameMode.COMBAT_BADASS)) {
			wurfreihen = entfernteReihen;
		}
		
		for (Player player : this.game.getPlayers()) {
			if (player != this && player.hasStatus(PlayerStatus.ACTIVE))
				player.wartendeReihen += wurfreihen;
		}
	}
	
	private void initialisiereNaechstenStein() {
		this.anzahlSteine++;
		this.stein = this.n�chsterStein;
		this.n�chsterStein = this.steinFactory.erzeugeRandomStein(this.spielfeld);
		for (Position position : this.stein.getPositionen()) {
			if (this.spielfeld.isBlocked(position)) {
				this.status = PlayerStatus.STUCK;
				return;
			}
		}
	}
	
	void setStatus(final PlayerStatus state) {
		this.status = state;
	}
	
	/**
	 * Gibt den aktuellen Zustand dieses Spielers zur�ck.
	 */
	public PlayerStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Gibt zur�ck, ob der State des Spielers dem angefragten Zustand entspricht.
	 */
	public boolean hasStatus(final PlayerStatus state) {
		return this.status == state;
	}

	/**
	 * Gibt das Spielfeld des Spielers zur�ck.
	 */
	public Spielfeld getSpielfeld() {
		return this.spielfeld;
	}
	
	/**
	 * Gibt den aktuellen Stein zur�ck.
	 */
	public Stone getStein() {
		return this.stein;
	}
	
	/**
	 * Gibt den n�chsten Stein zur�ck.
	 */
	public Stone getNextStein() {
		return this.n�chsterStein;
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
	public int getAnzahlSteine() {
		return this.anzahlSteine;
	}
	
	/**
	 * Gibt die fertigen Reihen zur�ck.
	 */
	public int getFertigeReihen() {
		return this.fertigeReihen;
	}
	
	/**
	 * Gibt die verbleibende Zahl zu eliminierender Reihen zur�ck.
	 */
	public int getVerbleibendeReihen() {
		return GameMode.getRaceRows() - this.fertigeReihen;
	}
	
	/**
	 * Gibt die verbleibende Zahl zu eliminierender K�se-Reihen zur�ck.
	 */
	public int getCheeseReihen() {
		return this.spielfeld.getCheeseReihen();
	}

	public int getWartendeReihen() {
		return this.wartendeReihen;
	}
	
}