package de.drake.tetris.util;

/**
 * Ein Integer-Paar, welches relative oder absolute Koordinaten innerhalb des Tetrisspielfeldes beschreibt.
 * Der erste Wert x repräsentiert die horizontale Koordinate, wobei 0 am linken Spielfeldrand liegt.
 * Der zweite Wert y repräsentiert die vertikale Koordinate, wobei 0 am unteren Spielfeldrand liegt.
 */
public class Position {
	
	/**
	 * Die X-Koordinate der Position.
	 */
	private int x;
	
	/**
	 * Die Y-Koordinate der Position.
	 */
	private int y;
	
	/**
	 * Erzeugt eine Positionsangabe.
	 * @param x Der horizontale Teil der Koordinaten, wobei 0 am linken Spielfeldrand liegt.
	 * @param y Der vertikale Teil der Koordinaten, wobei 0 am unteren Spielfeldrand liegt.
	 */
	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Erzeugt eine Position auf Basis einer Vorlage (d.h. eine Kopie).
	 * 
	 * @position Die Position, die kopiert werden soll.
	 */
	public Position(Position position) {
		this.x = position.x;
		this.y = position.y;
	}

	/**
	 * Verschiebt die Position in der angegebenen Richtung.
	 * 
	 * @param x Die Zahl der Felder, die in horizontaler Richtung verschoben wird. Negative Werte verschieben nach links.
	 * 
	 * @param y Die Zahl der Felder, die in vertikaler Richtung verschoben wird. Negative Werte verschieben nach unten.
	 */
	public void verschiebe(final int x, final int y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Gibt die X-Koordinate der Position zurück.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gibt die Y-Koordinate der Position zurück.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Testet zwei Positionen auf Gleichheit. Zwei Positionen sind gleich, wenn x- und y-Koordinate übereinstimmen. Überschreibt Object.equals(object).
	 */
	@Override
	public boolean equals(final Object object) {
		if (!object.getClass().toString().contains("Position"))
			return false;
		Position position = (Position) object;
		if (this.x == position.x && this.y == position.y)
			return true;
		return false;
	}
	
	/**
	 * Gibt die Position als String aus. Überschreibt Object.toString().
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	/**
	 * Gibt einen Hashcode zurück.
	 */
	@Override
	public int hashCode() {
		return this.x;
	}
}