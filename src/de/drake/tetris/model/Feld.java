package de.drake.tetris.model;

import de.drake.tetris.util.Color;

/**
 * Ein Feld innerhalb des Tetris-Spielfeldes.
 */
class Feld {
	
	/**
	 * Die Farbe des Feldes, sofern es geblockt ist. 
	 */
	private Color color;
	
	/**
	 * Gibt an, ob das Feld bereits gefüllt und damit für fallende Steine blockiert ist.
	 */
	private boolean isBlocked = false;
	
	/**
	 * Gibt an, ob das Feld bereits gefüllt und damit für fallende Steine blockiert ist.
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
	 * Gibt die Farbe des Feldes zurück.
	 */
	Color getColor() {
		return this.color;
	}
	
	/**
	 * Übernimmt die Attribute eines anderen Feldes.
	 * 
	 * @param feld
	 * 		Das Feld, dessen Attribute übernommen werden sollen.
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