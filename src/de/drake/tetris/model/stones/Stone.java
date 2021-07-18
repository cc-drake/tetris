package de.drake.tetris.model.stones;

import java.util.HashMap;
import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.util.Position;

/**
 * Ein Stein, der im Tetrisspielfeld fällt.
 */
public abstract class Stone implements Cloneable {
	
	/**
	 * Der Spieler, der den Stein kontrolliert.
	 */
	private Player player;
	
	/**
	 * Der Typ des Steins.
	 */
	private final BlockTexture texture;
	
	/**
	 * Die aktuelle Position des Mittelpunkt des Steins.
	 */
	private final Position mittelpunkt;
	
	/**
	 * Die aktuelle Drehung des Steins.
	 */
	private int drehung;
	
	/**
	 * Enthält für jeden Drehzustand eine Liste der zugehörigen relativen Koordinaten aller Steinfelder.
	 * Die relativen Koordinaten beziehen sich auf den Mittelpunkt des Steins, welcher initial
	 * mittig (bei gerader Spielfeldbreite rechts der Mitte) in der obersten sichtbaren Zeile
	 * des Spielfelds platziert wird.
	 */
	private final HashMap<Integer, HashSet<Position>> relativkoordinaten;
	
	/**
	 * Erzeugt einen neuen Stein.
	 */
	protected Stone(final BlockTexture texture) {
		this.texture = texture;
		this.mittelpunkt = new Position(Config.breite / 2, 0);
		this.drehung = 0;
		this.relativkoordinaten = this.getRelativkoordinaten();
	}
	
	/**
	 * Initialisiert das Player-Attribut.
	 */
	public final void setPlayer(final Player player) {
		this.player = player;
	}
	
	/**
	 * Erzeugt einen Satz mit den Relativkoordinaten des Steins (seiner "Form").
	 */
	protected abstract HashMap<Integer, HashSet<Position>> getRelativkoordinaten();
	
	/**
	 * Wird ausgeführt, wenn der Stein auf den Boden aufschlägt
	 */
	public abstract void detonate();
	
	/**
	 * Erzeugt einen neuen Stein des gleichen Typs.
	 */
	@Override
	public abstract Stone clone();
	
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
	public synchronized boolean bewege(final int x, final int y, final Boolean imUhrzeigersinn) {
		if (this.player.getSpielfeld().isBlocked(this.getBewegtePositionen(x, y, imUhrzeigersinn))) {
			return false;
		}
		this.mittelpunkt.verschiebe(x, y);
		if (imUhrzeigersinn != null) {
			this.drehung = this.getNewDrehung(this.drehung, imUhrzeigersinn);
			Asset.SOUND_DREH.play();
		}
		return true;
	}
	
	/**
	 * Erstellt eine Liste von allen Feldpositionen, die der Stein nach einer Verschiebung bzw. Drehung hätte.
	 * 
	 * @param x Die Anzahl der Felder in horizontaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen für eine Bewegung nach links, positive für eine Bewegung nach rechts.
	 * @param y Die Anzahl der Felder in vertikaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen für eine Bewegung nach oben, positive für eine Bewegung nach unten.
	 * @param imUhrzeigersinn Drehrichtung der Drehung. null, wenn nicht gedreht werden soll.
	 * 		true, wenn im Uhrzeigersinn gedreht werden soll.
	 * 		false, wenn entgegen dem Uhrzeigersinn gedreht werden soll.
	 */
	private final synchronized HashSet<Position> getBewegtePositionen(final int x, final int y, final Boolean imUhrzeigersinn) {
		int drehungNeu;
		if (imUhrzeigersinn == null) {
			drehungNeu = this.drehung;
		} else {
			drehungNeu = this.getNewDrehung(this.drehung, imUhrzeigersinn);
		}
		HashSet<Position> result = new HashSet<Position>(this.relativkoordinaten.get(0).size());
		Position position;
		for (Position relativposition : this.relativkoordinaten.get(drehungNeu)) {
			position = new Position(relativposition);
			position.verschiebe(this.mittelpunkt.getX() + x, this.mittelpunkt.getY() + y);
			result.add(position);
		}
		return result;
	}
	
	/**
	 * Gibt den neuen Drehzustand nach Drehung zurück.
	 * @param drehung der alte Drehzustand
	 * @param imUhrzeigersinn Gibt die Richtung der Drehung an.
	 */
	private final int getNewDrehung(final int drehung, final boolean imUhrzeigersinn) {
		int anzahlDrehungen = this.relativkoordinaten.keySet().size();
		int result = (imUhrzeigersinn ? drehung + 1 : drehung - 1);
		result = (result + anzahlDrehungen) % anzahlDrehungen;
		return result;
	}
	
	/**
	 * Erzeugt eine Liste aller aktuellen Feldpositionen des Steins.
	 */
	public final synchronized HashSet<Position> getPositionen() {
		return this.getBewegtePositionen(0, 0, null);
	}
	
	/**
	 * Gibt die Relativkoordinaten des Steins in der Default-Drehung zurück.
	 */
	public final HashSet<Position> getDefaultRelativePositions() {
		return this.relativkoordinaten.get(0);
	}
	
	/**
	 * Gibt die Blocktextur des Steins zurück.
	 */
	public final BlockTexture getTexture() {
		return this.texture;
	}
	
	protected final Player getPlayer() {
		return this.player;
	}
}