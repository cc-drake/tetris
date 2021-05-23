package de.drake.tetris.model.util;

/**
 * Ein Integer-Paar, welches relative oder absolute Koordinaten innerhalb des Tetrisspielfeldes beschreibt.
 * Der erste Wert x repräsentiert die horizontale Koordinate, wobei 0 am linken Spielfeldrand liegt.
 * Der zweite Wert y repräsentiert die vertikale Koordinate, wobei 0 am oberen Spielfeldrand liegt.
 */
public class Position implements Comparable<Position> {
	
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
	 * @param y Der vertikale Teil der Koordinaten, wobei 0 am oberen Spielfeldrand liegt.
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
	 * @param y Die Zahl der Felder, die in vertikaler Richtung verschoben wird. Negative Werte verschieben nach oben.
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
	 * Vergleicht zwei Positions auf identität (d.h. identische Angaben zu x und y).
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	/**
	 * Gibt die Position als String aus. Überschreibt Object.toString().
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	@Override
	public int hashCode() {
		return this.x + 1013 * this.y;
	}
	
	/**
	 * Natürliche Sortierung: Erst nach y, dann nach x, d.h. Reihenfolge (0,0), (1,0), (0,1), (1,1).
	 */
	@Override
	public int compareTo(Position otherPosition) {
		if (this.y == otherPosition.y)
			return ((Integer) this.x).compareTo(otherPosition.x);
		return ((Integer) this.x).compareTo(otherPosition.x);
	}
}