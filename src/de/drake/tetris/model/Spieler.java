package de.drake.tetris.model;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.PlayerTemplate;
import de.drake.tetris.input.GamepadManager;
import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.KeyboardManager;
import de.drake.tetris.states.GameState;
import de.drake.tetris.util.Action;
import de.drake.tetris.util.Position;

/**
 * Der Spieler verwaltet das Spielfeld eines Spielers und führt Bewegungseingaben ("Links", "Rechts", "Drehen") aus.
 */
public class Spieler {
	
	public final static int UNDEF = 0;
	
	public final static int ACTIVE = 1;
	
	public final static int LOSER = 2;
	
	public final static int WINNER = 3;
	
	/**
	 * Der Name des Spielers.
	 */
	private final String name;
	
	/**
	 * Der InputManager, über den der Spieler gesteuert wird.
	 */
	private final InputManager inputManager;
	
	/**
	 * Das Spielfeld, in dem die Steine fallen.
	 */
	private final Spielfeld spielfeld;
	
	/**
	 * Der Zufallsgenerator, der die neuen Steine erzeugt
	 */
	private final SteinFactory steinFactory;
	
	/**
	 * Der Stein, der aktuell im Spielfeld fällt.
	 */
	private Stein stein;
	
	/**
	 * Der Stein, der als nächstes ins Spielfeld fallen wird.
	 */
	private Stein nächsterStein;
	
	/**
	 * Anzahl der Fallvorgänge pro Sekunde.
	 */
	private double speed;
	
	/**
	 * Der Laufzeitindex, an dem zuletzt ein Stein gefallen ist
	 */
	private long letzteFallzeit = 0;
	
	/**
	 * Der Laufzeitindex, an dem zuletzt eine Sekunde vergangen war (zur Speederhöhung)
	 */
	private long letzteSeczeit = 0;
	
	/**
	 * Die Spielzeit des Spielers in Sekunden.
	 */
	private int LaufzeitSec = 0;
	
	/**
	 * Die Anzahl der Reihen, die fertiggestellt wurden.
	 */
	private int fertigeReihen = 0;
	
	/**
	 * Die Anzahl der Steine, die bereits gesetzt wurden.
	 */
	private int anzahlSteine = 0;
	
	/**
	 * Gibt den aktuellen Zustand des Spielers an.
	 */
	private int state = Spieler.ACTIVE;
	
	/**
	 * Erzeugt einen neuen Spieler aus einem SpielerTemplate.
	 */
	public Spieler(final PlayerTemplate playerTemplate, final GameState gameState, final long seed) {
		this.name = playerTemplate.getName();
		this.speed = playerTemplate.getSpeed();
		switch (playerTemplate.getInputManagerType()) {
		case InputManager.KEYBOARD:
			this.inputManager = new KeyboardManager(gameState.getScreen(), playerTemplate.getKeyBinding());
			break;
		case InputManager.GAMEPAD_0:
			this.inputManager = new GamepadManager(gameState.getScreen(), 0, playerTemplate.getKeyBinding());
			break;
		case InputManager.GAMEPAD_1:
		default:
			this.inputManager = new GamepadManager(gameState.getScreen(), 1, playerTemplate.getKeyBinding());
		}
		this.spielfeld = new Spielfeld(seed);
		this.steinFactory = new SteinFactory(seed);
		this.stein = this.steinFactory.erzeugeRandomStein();
		this.nächsterStein = this.steinFactory.erzeugeRandomStein();
	}
	
	/**
	 * Führt die nächte vom InputManager gewünschte Action aus.
	 */
	public Action getInputAction() {
		return this.inputManager.getNextAction();
	}
	
	/**
	 * Führt die angegebene Bewegungsaktion aus, sofern der Spieler noch aktiv ist.
	 */
	public void performMoveAction(final Action action) {
		
		if (this.state != Spieler.ACTIVE)
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
	 * Führt zeitgesteuerte Vorgänge aus, wie den nächsten automatischen "Steinfall"
	 * oder die Aktualisierung der Zeit.
	 */
	public void tick(final int gameStateState, final long laufzeitIndex) {
		
		if (this.state != Spieler.ACTIVE)
			return;
		
		this.LaufzeitSec = (int) (laufzeitIndex / 1000000000.);
		
		switch (gameStateState) {
		case GameState.RUNNING:
			
			long ZeitProSteinfall = (long) (1000000000. / this.speed);
			if ((laufzeitIndex - this.letzteFallzeit) >= ZeitProSteinfall) {
				this.letzteFallzeit += ZeitProSteinfall;
				this.performMoveAction(Action.RUNTER);
			}
			
			if ((laufzeitIndex - this.letzteSeczeit) >= 1000000000.) {
				this.letzteSeczeit += 1000000000.;
				this.speed *= (1 + Config.speedIncreaseSec / 100.);
			}

		default:

		}
		
	}
	
	public void setState(final int state) {
		this.state = state;
	}
	
	/**
	 * Bewegt den aktiven Stein in der angegebenen Richtung und Drehung, sofern möglich.
	 * 
	 * @param x Die Anzahl der Felder in horizontaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen für eine Bewegung nach links, positive für eine Bewegung nach rechts.
	 * @param y Die Anzahl der Felder in vertikaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen für eine Bewegung nach oben, positive für eine Bewegung nach unten.
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
	 */
	private void setzeSteinAb() {
		for (Position position : this.stein.getPositionen()) {
			this.spielfeld.block(position, this.stein.getColor());
		}
		int entfernteReihen = this.spielfeld.entferneFertigeReihen();
		this.fertigeReihen += entfernteReihen;
		for (int i = 0; i < entfernteReihen; i++) {
			this.speed *= (1 + Config.speedIncreaseRow / 100.);
		}
		this.anzahlSteine++;
		this.stein = this.nächsterStein;
		this.nächsterStein = this.steinFactory.erzeugeRandomStein();
		for (Position position : this.stein.getPositionen()) {
			if (this.spielfeld.isBlocked(position)) {
				this.state = Spieler.UNDEF;
				break;
			}
		}
	}
	
	/**
	 * Gibt den aktuellen Zustand dieses Spielers zurück.
	 */
	public int getState() {
		return this.state;
	}
	
	/**
	 * Gibt zurück, ob der State des Spielers dem angefragten Zustand entspricht.
	 */
	public boolean hasState(final int state) {
		return this.state == state;
	}

	/**
	 * Gibt das Spielfeld des Spielers zurück.
	 */
	public Spielfeld getSpielfeld() {
		return this.spielfeld;
	}
	
	/**
	 * Gibt den aktuellen Stein zurück.
	 */
	public Stein getStein() {
		return this.stein;
	}
	
	/**
	 * Gibt den nächsten Stein zurück.
	 */
	public Stein getNextStein() {
		return this.nächsterStein;
	}
	
	/**
	 * Gibt den Namen des Spielers zurück.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gibt die vergangene Zeit in Sekunden zurück.
	 */
	public int getVergangeneZeit() {
		return this.LaufzeitSec;
	}
	
	/**
	 * Gibt die verbleibende Spielzeit in Sekunden zurück.
	 */
	public int getVerbleibendeZeit() {
		return Config.timeLimit - this.LaufzeitSec;
	}
	
	/**
	 * Gibt die Zahl der bereits gesetzten Steine zurück.
	 */
	public int getAnzahlSteine() {
		return this.anzahlSteine;
	}
	
	/**
	 * Gibt die fertigen Reihen zurück.
	 */
	public int getFertigeReihen() {
		return this.fertigeReihen;
	}
	
	/**
	 * Gibt die verbleibende Zahl zu eliminierender Reihen zurück.
	 */
	public int getVerbleibendeReihen() {
		return Config.raceRows - this.fertigeReihen;
	}
	
	/**
	 * Gibt die verbleibende Zahl zu eliminierender Käse-Reihen zurück.
	 */
	public int getCheeseReihen() {
		return this.spielfeld.getCheeseReihen();
	}
	
}