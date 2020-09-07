package de.drake.tetris.model;

import java.util.HashMap;
import java.util.HashSet;

import de.drake.tetris.config.Config;
import de.drake.tetris.util.Color;
import de.drake.tetris.util.Position;

/**
 * Ein Stein, der im Tetrisspielfeld fällt. Steine können verschiedene Formen besitzen, welche durch Erweiterungen dieser Klasse gebildet werden.
 */
public class Stein {
	
	/**
	 * Die Farbe des Steins.
	 */
	private final Color color;
	
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
	
	Stein(final Color color, HashMap<Integer, HashSet<Position>> relativkoordinaten) {
		this.color = color;
		this.mittelpunkt = new Position(Config.breite / 2, Config.hoehe - 1);
		this.drehung = 0;
		this.relativkoordinaten = relativkoordinaten;
	}
	
	Stein(final Stein stein) {
		this.color = stein.color;
		this.mittelpunkt = new Position(stein.mittelpunkt);
		this.drehung = stein.drehung;
		this.relativkoordinaten = stein.relativkoordinaten;
	}
	
	/**
	 * Verschiebt den Stein in der angegebenen Richtung.
	 * 
	 * @param x Die Zahl der Felder, die in horizontaler Richtung verschoben wird. Negative Werte verschieben nach links.
	 * 
	 * @param y Die Zahl der Felder, die in vertikaler Richtung verschoben wird. Negative Werte verschieben nach unten.
	 */
	void verschiebe(final int x, final int y) {
		this.mittelpunkt.verschiebe(x, y);
	}
	
	/**
	 * Dreht den Stein.
	 * 
	 * @param imUhrzeigersinn Drehrichtung der Drehung. true, wenn im Uhrzeigersinn gedreht werden soll.
	 * 		false für eine Drehung entgegen dem Uhrzeigersinn.
	 */
	void drehe(final boolean imUhrzeigersinn) {
		this.drehung = this.getNewDrehung(this.drehung, imUhrzeigersinn);
	}
	
	/**
	 * Gibt den neuen Drehzustand nach Drehung zurück.
	 * @param drehung der alte Drehzustand
	 * @param imUhrzeigersinn Gibt die Richtung der Drehung an.
	 */
	private int getNewDrehung(final int drehung, final boolean imUhrzeigersinn) {
		int anzahlDrehungen = this.relativkoordinaten.keySet().size();
		int result = (imUhrzeigersinn ? drehung + 1 : drehung - 1);
		result = (result + anzahlDrehungen) % anzahlDrehungen;
		return result;
	}
	
	/**
	 * Erstellt eine Liste von allen Feldpositionen, die der Stein nach einer Verschiebung bzw. Drehung hätte.
	 * 
	 * @param x Die Anzahl der Felder in horizontaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen für eine Bewegung nach links, positive für eine Bewegung nach rechts.
	 * @param y Die Anzahl der Felder in vertikaler Richtung, um die der Stein verschoben werden soll.
	 * 		Negative Werte stehen für eine Bewegung nach unten, positive für eine Bewegung nach oben.
	 * @param imUhrzeigersinn Drehrichtung der Drehung. null, wenn nicht gedreht werden soll.
	 * 		true, wenn im Uhrzeigersinn gedreht werden soll.
	 * 		false, wenn entgegen dem Uhrzeigersinn gedreht werden soll.
	 */
	HashSet<Position> getBewegtePositionen(final int x, final int y, final Boolean imUhrzeigersinn) {
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
	 * Erzeugt eine Liste aller aktuellen Feldpositionen des Steins.
	 */
	public HashSet<Position> getPositionen() {
		return this.getBewegtePositionen(0, 0, null);
	}
	
	/**
	 * Gibt die Relativkoordinaten des Steins in der Default-Drehung zurück.
	 */
	public HashSet<Position> getRelativkoordinaten() {
		return this.relativkoordinaten.get(0);
	}
	
	/**
	 * Gibt die Farbe des Steins zurück.
	 */
	public Color getColor() {
		return this.color;
	}
}