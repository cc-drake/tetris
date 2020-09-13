package de.drake.tetris.model;

import de.drake.tetris.util.Color;

/**
 * Ein Feld innerhalb des Tetris-Spielfeldes.
 */
public class Feld {
	
	/**
	 * Das Seitenverh�ltnis, mit dem die Tetrisfelder gezeichnet werden (Breite durch H�he)
	 */
	public final static double seitenverhaeltnis = 1.5/1.;
	
	/**
	 * Die Farbe des Feldes, sofern es geblockt ist. 
	 */
	private Color color;
	
	/**
	 * Gibt an, ob das Feld bereits gef�llt und damit f�r fallende Steine blockiert ist.
	 */
	private boolean isBlocked = false;
	
	/**
	 * Gibt an, ob das Feld bereits gef�llt und damit f�r fallende Steine blockiert ist.
	 */
	boolean isBlocked() {
		return this.isBlocked;
	}
	
	/**
	 * Blockiert das Feld mit der angegebenen Farbe.
	 * 
	 * @param color
	 * 		Die Farbe, mit der das Feld geblockt werden soll.
	 */
	void block(final Color color) {
		this.isBlocked = true;
		this.color = color;
	}
	
	/**
	 * Gibt die Farbe des Feldes zur�ck.
	 */
	Color getColor() {
		return this.color;
	}
	
	/**
	 * �bernimmt die Attribute eines anderen Feldes.
	 * 
	 * @param feld
	 * 		Das Feld, dessen Attribute �bernommen werden sollen.
	 */
	void set(final Feld feld) {
		this.color = feld.color;
		this.isBlocked = feld.isBlocked;
	}
	
	/**
	 * Stellt den Inhalt des Felds als String dar.
	 */
	public String toString() {
		if (this.isBlocked)
			return "X";
		return " ";
	}
}