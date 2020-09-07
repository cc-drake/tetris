package de.drake.tetris.model;

import de.drake.tetris.config.PlayerTemplate;
import de.drake.tetris.input.AIManager;
import de.drake.tetris.input.GamepadManager;
import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.KeyboardManager;
import de.drake.tetris.states.PlayState;
import de.drake.tetris.util.Action;
import de.drake.tetris.util.Position;

/**
 * Der Spieler verwaltet das Spielfeld eines Spielers und führt Bewegungseingaben ("Links", "Rechts", "Drehen") aus.
 */
public class Spieler {
	
	public final static int ACTIVE = 1;
	
	public final static int LOSER = 2;
	
	public final static int WINNER = 3;
	
	/**
	 * Der PlayState, der das laufende Spiel verwaltet.
	 */
	private final PlayState playState;
	
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
	 * Der Zeitpunkt, an dem zuletzt ein Stein gefallen ist
	 */
	private long letzteFallzeit;
	
	/**
	 * Die Anzahl der Reihen, die fertiggestellt wurden.
	 */
	private int fertigeReihen = 0;
	
	/**
	 * Die Anzahl der Steine, die bereits erzeugt wurden.
	 */
	private int anzahlSteine = 1;
	
	/**
	 * Gibt den aktuellen Zustand des Spielers an.
	 */
	private int currentState = Spieler.ACTIVE;
	
	/**
	 * Erzeugt einen neuen Spieler aus einem SpielerTemplate.
	 */
	public Spieler(final PlayerTemplate playerTemplate, final PlayState playState, final long seed) {
		this.playState = playState;
		this.name = playerTemplate.getName();
		this.speed = playerTemplate.getSpeed();
		switch (playerTemplate.getInputManagerType()) {
		case InputManager.KEYBOARD:
			this.inputManager = new KeyboardManager(this.playState.getJPanel(), playerTemplate.getKeyBinding());
			break;
		case InputManager.GAMEPAD_0:
			this.inputManager = new GamepadManager(this.playState.getJPanel(), 0, playerTemplate.getKeyBinding());
			break;
		default:
			this.inputManager = new AIManager();
		}
		this.spielfeld = new Spielfeld();
		this.steinFactory = new SteinFactory(seed);
		this.stein = this.steinFactory.erzeugeRandomStein();
		this.nächsterStein = this.steinFactory.erzeugeRandomStein();
	}
	
	/**
	 * Führt die nächte vom InputManager gewünschte Action aus.
	 * @param currentState Gibt an, ob das Spiel derzeit pausiert ist.
	 * @param currentTime 
	 */
	public void performNextInputAction(final int currentState) {
		
		Action action = this.inputManager.getNextAction(currentState);
		
		if (action == null) {
			return;
		} else if (action == Action.PAUSE) {
			this.playState.togglePause();
			return;
		} else if (currentState == PlayState.RUNNING){
			this.performMoveAction(action);
		}
	}
	
	/**
	 * Prüft, ob es Zeit für den nächsten automatischen "Steinfall" ist, und führt diesen ggfs. aus.
	 */
	public void performSteinfall(final int currentState, final long currentTime) {
		
		switch (currentState) {
		case PlayState.PREPARED:
		case PlayState.PAUSED:
			this.letzteFallzeit = currentTime;
			return;
		case PlayState.RUNNING:
			long ZeitProSteinfall = (long) (1000000000. / this.speed);
			if ((currentTime - this.letzteFallzeit) >= ZeitProSteinfall) {
				this.letzteFallzeit += ZeitProSteinfall;
				this.performMoveAction(Action.RUNTER);
			}
		}
	}
	
	/**
	 * Führt die angegebene Bewegungsaktion aus.
	 */
	private void performMoveAction(final Action action) {

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
			while(this.bewegeStein(0, 1, null)) {
			}
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
	
	public void winGame() {
		this.currentState = Spieler.WINNER;
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
		this.fertigeReihen += this.spielfeld.entferneFertigeReihen();
		this.anzahlSteine++;
		this.stein = this.nächsterStein;
		this.nächsterStein = this.steinFactory.erzeugeRandomStein();
		for (Position position : this.stein.getPositionen()) {
			if (this.spielfeld.isBlocked(position)) {
				this.currentState = Spieler.LOSER;
				break;
			}
		}
	}
	
	/**
	 * Gibt den aktuellen Zustand dieses Spielers zurück.
	 */
	public int getCurrentState() {
		return this.currentState;
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
	 * Gibt die Zahl der bereits erzeugten Steine zurück.
	 */
	public int getAnzahlSteine() {
		return this.anzahlSteine;
	}
	
	/**
	 * Gibt die Zahl der eliminierten Reihen zurück.
	 */
	public int getFertigeReihen() {
		return this.fertigeReihen;
	}
	
	public PlayState getPlayState() {
		return this.playState;
	}
	
}