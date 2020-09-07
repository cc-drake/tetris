package de.drake.tetris.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Repr�sentation von Gittern, deren Indexierung analog zum Koordinatensystem �ber X- und Y-Koordinaten erfolgt.
 * @param <ContentClass> Klasse der Eintr�ge, z.B. Integer
 */
public class Gitter<ContentClass> {
	
	/**
	 * Speichert den Inhalt des Gitters
	 */
	private final HashMap<Position, ContentClass> map;
	
	/**
	 * Speichert die Breite des Gitters (Ausdehnung in X-Richtung)
	 */
	private final int breite;
	
	/**
	 * Speichert die H�he des Gitters (Ausdehnung in y-Richtung)
	 */
	private final int hoehe;
	
	/**
	 * Konstruktor zum Erzeugen eines Gitters einer bestimmten Gr��e. Das Gitter wird mit null initialisiert.
	 * 
	 * @param breite
	 * 		die Breite des Gitters (x-Richtung)
	 * @param hoehe
	 * 		die H�he des Gitters (y-Richtung)

	 */
	public Gitter(final int breite, final int hoehe) {
		this.breite = breite;
		this.hoehe = hoehe;
		this.map = new HashMap<Position, ContentClass>();
		for (int x = 0; x < this.breite; x++) {
			for (int y = 0; y < this.hoehe; y++) {
				this.map.put(new Position(x, y), null);
			}
		}
	}
	
	/**
	 * Stellt ein Gitter als String dar.
	 * 
	 * @return der erstellte String
	 */
	@Override
	public String toString() {
		String result = "";
		for (int y = this.hoehe - 1; y >= 0; y--) {
			for (int x = 0; x < this.breite; x++) {
				result += this.map.get(new Position(x, y)) + ", ";
			}
			result += "\n";
		}
		return result;
	}
	
	/**
	 * get-Methode f�r die Breite des Gitters
	 */
	public int getBreite() {
		return this.breite;
	}
	
	/**
	 * get-Methode f�r die H�he des Gitters
	 */
	public int getHoehe() {
		return this.hoehe;
	}
	
	/**
	 * get-Methode f�r einen ausgew�hlten Eintrag des Gitters
	 * 
	 * @param position
	 * 		Die Position des Eintrags im Gitter
	 */
	public ContentClass get(final Position position) {
		return this.map.get(position);
	}
	
	/**
	 * get-Methode f�r einen ausgew�hlten Eintrag des Gitters
	 * 
	 * @param x
	 * 		x-Koordinate des angeforderten Eintrags
	 * @param y
	 * 		y-Koordinate des angeforderten Eintrags
	 */
	public ContentClass get(final int x, final int y) {
		return this.get(new Position(x, y));
	}
	
	/**
	 * set-Methode f�r einen ausgew�hlten Eintrag des Gitters
	 * 
	 * @param position
	 * 		Die Position des Eintrags im Gitter
	 * @param wert
	 * 		der Wert, auf den der Eintrag gesetzt werden soll
	 */
	public void set(final Position position, final ContentClass wert) {
		this.map.put(position, wert);
	}
	
	/**
	 * set-Methode f�r einen ausgew�hlten Eintrag des Gitters
	 * 
	 * @param x
	 * 		x-Koordinate des angeforderten Eintrags
	 * @param y
	 * 		y-Koordinate des angeforderten Eintrags
	 * @param wert
	 * 		der Wert, auf den der Eintrag gesetzt werden soll
	 */
	public void set(final int x, final int y, final ContentClass wert) {
		this.set(new Position(x, y), wert);
	}
	
	/**
	 * get-Methode f�r eine ausgew�hlte Zeile des Gitters
	 * 
	 * @param y
	 * 		Index der angeforderten Zeile (y-Koordinate)
	 */
	public ArrayList<ContentClass> getZeile(final int y) {
		ArrayList<ContentClass> result = new ArrayList<ContentClass>(this.breite);
		for (int x = 0; x < this.breite; x++) {
			result.add(this.map.get(new Position(x, y)));
		}
		return result;
	}
	
	/**
	 * get-Methode f�r eine ausgew�hlte Spalte des Gitters
	 * 
	 * @param x
	 * 		Index der angeforderten Spalte (x-Koordinate)
	 */
	public ArrayList<ContentClass> getSpalte(final int x) {
		ArrayList<ContentClass> result = new ArrayList<ContentClass>(this.hoehe);
		for (int y = 0; y < this.hoehe; y++) {
			result.add(this.map.get(new Position(x, y)));
		}
		return result;
	}
}