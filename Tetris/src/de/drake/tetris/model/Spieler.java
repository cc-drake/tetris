package de.drake.tetris.model;

import de.drake.tetris.config.PlayerTemplate;
import de.drake.tetris.input.AIManager;
import de.drake.tetris.input.GamepadManager;
import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.KeyboardManager;
import de.drake.tetris.states.GameState;
import de.drake.tetris.util.Action;
import de.drake.tetris.util.Position;

/**
 * Der Spieler verwaltet das Spielfeld eines Spielers und f�hrt Bewegungseingaben ("Links", "Rechts", "Drehen") aus.
 */
public class Spieler {
	
	public final static int ACTIVE = 1;
	
	public final static int LOSER = 2;
	
	public final static int WINNER = 3;
	
	/**
	 * Der Name des Spielers.
	 */
	private final String name;
	
	/**
	 * Der InputManager, �ber den der Spieler gesteuert wird.
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
	 * Der Stein, der aktuell im Spielfeld f�llt.
	 */
	private Stein stein;
	
	/**
	 * Der Stein, der als n�chstes ins Spielfeld fallen wird.
	 */
	private Stein n�chsterStein;
	
	/**
	 * Anzahl der Fallvorg�nge pro Sekunde.
	 */
	private double speed;
	
	/**
	 * Der Zeitpunkt, an dem zuletzt ein Stein gefallen ist
	 */
	private long letzteFallzeit;
	
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
		default:
			this.inputManager = new AIManager();
		}
		this.spielfeld = new Spielfeld();
		this.steinFactory = new SteinFactory(seed);
		this.stein = this.steinFactory.erzeugeRandomStein();
		this.n�chsterStein = this.steinFactory.erzeugeRandomStein();
	}
	
	/**
	 * F�hrt die n�chte vom InputManager gew�nschte Action aus.
	 */
	public Action getInputAction() {
		return this.inputManager.getNextAction();
	}
	
	/**
	 * F�hrt die angegebene Bewegungsaktion aus, sofern der Spieler noch aktiv ist.
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
	 * Pr�ft, ob es Zeit f�r den n�chsten automatischen "Steinfall" ist, und f�hrt diesen ggfs. aus.
	 */
	public void performSteinfall(final int gameStateState, final long currentTime) {
		
		switch (gameStateState) {
		case GameState.RUNNING:
			long ZeitProSteinfall = (long) (1000000000. / this.speed);
			if ((currentTime - this.letzteFallzeit) >= ZeitProSteinfall) {
				this.letzteFallzeit += ZeitProSteinfall;
				this.performMoveAction(Action.RUNTER);
			}
			break;
		default:
			this.letzteFallzeit = currentTime;
		}
		
	}
	
	public void winGame() {
		this.state = Spieler.WINNER;
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
	 */
	private void setzeSteinAb() {
		for (Position position : this.stein.getPositionen()) {
			this.spielfeld.block(position, this.stein.getColor());
		}
		this.fertigeReihen += this.spielfeld.entferneFertigeReihen();
		this.anzahlSteine++;
		this.stein = this.n�chsterStein;
		this.n�chsterStein = this.steinFactory.erzeugeRandomStein();
		for (Position position : this.stein.getPositionen()) {
			if (this.spielfeld.isBlocked(position)) {
				this.state = Spieler.LOSER;
				break;
			}
		}
	}
	
	/**
	 * Gibt den aktuellen Zustand dieses Spielers zur�ck.
	 */
	public int getState() {
		return this.state;
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
	public Stein getStein() {
		return this.stein;
	}
	
	/**
	 * Gibt den n�chsten Stein zur�ck.
	 */
	public Stein getNextStein() {
		return this.n�chsterStein;
	}
	
	/**
	 * Gibt den Namen des Spielers zur�ck.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gibt die Zahl der bereits gesetzten Steine zur�ck.
	 */
	public int getAnzahlSteine() {
		return this.anzahlSteine;
	}
	
	/**
	 * Gibt die Zahl der eliminierten Reihen zur�ck.
	 */
	public int getFertigeReihen() {
		return this.fertigeReihen;
	}
	
}