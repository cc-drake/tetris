package de.drake.tetris.model;

import java.util.Random;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.input.util.InputManager;
import de.drake.tetris.model.util.Action;
import de.drake.tetris.model.util.Position;
import de.drake.tetris.states.GameState;

/**
 * Der Player verwaltet das Spielfeld eines Spielers und f�hrt Bewegungseingaben ("Links", "Rechts", "Drehen") aus.
 */
public class Player {
	
	public final static int UNDEF = 0;
	
	public final static int ACTIVE = 1;
	
	public final static int LOSER = 2;
	
	public final static int WINNER = 3;
	
	/**
	 * Der Name des Spielers
	 */
	private final String name;
	
	/**
	 * Der InputManager, der die Spieleingaben verarbeitet
	 */
	private final InputManager inputManager;
	
	/**
	 * Der GameState, der das aktuelle Spiel verwaltet.
	 */
	private final GameState gameState;
	
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
	private long laufzeit = 0;
	
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
	private int state = Player.ACTIVE;
	
	/**
	 * Erzeugt einen neuen Spieler.
	 */
	public Player(final PlayerConfig config, final GameState gameState, final long seed) {
		this.name = config.getName();
		this.inputManager = config.getInputManager();
		this.speed = config.getSpeed();
		this.gameState = gameState;
		Random random = new Random(seed);
		this.spielfeld = new Spielfeld(random.nextLong());
		this.steinFactory = new StoneFactory(random.nextLong());
		this.n�chsterStein = this.steinFactory.erzeugeRandomStein();
		this.initialisiereNaechstenStein();
	}
	
	/**
	 * Gibt die n�chste vom InputManager vorgesehene Aktionseingabe zur�ck.
	 */
	public Action getInputAction() {
		return this.inputManager.getNextAction();
	}
	
	/**
	 * F�hrt die angegebene Bewegungsaktion aus, sofern der Spieler noch aktiv ist.
	 */
	public void performMoveAction(final Action action) {
		
		if (this.state != Player.ACTIVE)
			return;

		switch (action) {
		case LINKS:
			this.bewegeStein(-1, 0, null);
			break;
		case RECHTS:
			this.bewegeStein(1, 0, null);
			break;
		case RUNTER:
			this.bewegeStein(0, 1, null);
			break;
		case GANZ_RUNTER:
			while(this.bewegeStein(0, 1, null));
			break;
		case DREHUNG_UHRZEIGERSINN:
			if (!this.bewegeStein(0, 0, true))
				if (!this.bewegeStein(1, 0, true))
					this.bewegeStein(-1, 0, true);
			break;
		case DREHUNG_ENTGEGEN_UHRZEIGERSINN:
			if (!this.bewegeStein(0, 0, false))
				if (!this.bewegeStein(-1, 0, false))
					this.bewegeStein(1, 0, false);
			break;
		default:
			break;
		}
	}
	
	/**
	 * F�hrt zeitgesteuerte Vorg�nge aus, wie den n�chsten automatischen "Steinfall"
	 * oder die Aktualisierung der Zeit.
	 */
	public void tick(final int gameStateState, final long laufzeitIndex) {
		
		if (this.state != Player.ACTIVE)
			return;
		
		this.laufzeit = laufzeitIndex;
		
		switch (gameStateState) {
		case GameState.RUNNING:
			
			long ZeitProSteinfall = (long) (1000000000. / this.speed);
			if ((laufzeitIndex - this.letzteFallzeit) >= ZeitProSteinfall) {
				this.letzteFallzeit += ZeitProSteinfall;
				this.performMoveAction(Action.RUNTER);
			}
			
			if ((laufzeitIndex - this.letzteSeczeit) >= 1000000000.) {
				this.letzteSeczeit += 1000000000.;
				this.speed *= (1 + GameMode.getSpeedIncreaseSec() / 100.);
			}

		default:

		}
		
	}
	
	/**
	 * Bewegt den aktiven Stein in der angegebenen Richtung und Drehung, sofern m�glich.
	 * 
	 * @param x Die Anzahl der Felder in horizontaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen f�r eine Bewegung nach links, positive f�r eine Bewegung nach rechts.
	 * @param y Die Anzahl der Felder in vertikaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen f�r eine Bewegung nach oben, positive f�r eine Bewegung nach unten.
	 * @param imUhrzeigersinn Drehrichtung der Drehung. null, wenn nicht gedreht werden soll.
	 * 		true, wenn im Uhrzeigersinn gedreht werden soll.
	 * 		false, wenn entgegen dem Uhrzeigersinn gedreht werden soll.
	 * @return true, wenn das Bewegen funktioniert hat.
	 */
	private boolean bewegeStein(final int x, final int y, final Boolean imUhrzeigersinn) {
		for (Position position : this.stein.getBewegtePositionen(x, y, imUhrzeigersinn)) {
			if (this.spielfeld.isBlocked(position)) {
				if (y > 0)
					this.setzeSteinAb();
				return false;
			}
		}
		stein.verschiebe(x, y);
		if (imUhrzeigersinn != null)
			stein.drehe(imUhrzeigersinn);
		return true;
	}
	
	/**
	 * Setzt einen Stein ab, d.h. der Stein verfestigt sich, fertige Reihen werden entfernt und und ein neuer Stein spawnt.
	 * Ist der abzusetzende Stein eine Bombe, so detoniert diese.
	 */
	private void setzeSteinAb() {
		this.spielfeld.verarbeiteStein(this.stein);
		int entfernteReihen = this.spielfeld.entferneFertigeReihen();
		this.fertigeReihen += entfernteReihen;
		for (int i = 0; i < entfernteReihen; i++) {
			this.speed *= (1 + GameMode.getSpeedIncreaseRow() / 100.);
		}
		int draufwerfen = 0;
		if (GameMode.getCombatType().equals(GameMode.COMBAT_CLASSIC)) {
			switch (entfernteReihen) {
			case 0:
			case 1:
				draufwerfen = 0;
				break;
			case 2:
			case 3:
				draufwerfen = entfernteReihen - 1;
				break;
			default:
				draufwerfen = entfernteReihen;
			}
		}
		if (GameMode.getCombatType().equals(GameMode.COMBAT_BADASS)) {
			draufwerfen = entfernteReihen;
		}
		this.gameState.draufwerfen(this, draufwerfen);
		this.spielfeld.generateCheeseRows(this.wartendeReihen);
		this.wartendeReihen = 0;
		this.initialisiereNaechstenStein();
	}
	
	private void initialisiereNaechstenStein() {
		this.anzahlSteine++;
		this.stein = this.n�chsterStein;
		this.n�chsterStein = this.steinFactory.erzeugeRandomStein();
		for (Position position : this.stein.getPositionen()) {
			if (this.spielfeld.isBlocked(position)) {
				this.state = Player.UNDEF;
				return;
			}
		}
	}
	
	public void addRows(final int rows) {
		this.wartendeReihen += rows;
	}
	
	public void setState(final int state) {
		this.state = state;
	}
	
	/**
	 * Gibt den aktuellen Zustand dieses Spielers zur�ck.
	 */
	public int getState() {
		return this.state;
	}
	
	/**
	 * Gibt zur�ck, ob der State des Spielers dem angefragten Zustand entspricht.
	 */
	public boolean hasState(final int state) {
		return this.state == state;
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
		return this.laufzeit;
	}
	
	/**
	 * Gibt die vergangene Zeit in Sekunden zur�ck.
	 */
	public int getVergangeneZeitSec() {
		return (int) (this.laufzeit / 1000000000.);
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