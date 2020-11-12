package de.drake.tetris.model;

import de.drake.tetris.model.util.Color;

/**
 * Ein Feld innerhalb des Tetris-Spielfeldes.
 */
class Feld {
	
	/**
	 * Die Farbe des Feldes, sofern es geblockt ist. 
	 */
	private Color color;
	
	/**
	 * Gibt an, ob das Feld bereits gef�llt und damit f�r fallende Steine blockiert ist.
	 */
	private boolean isBlocked = false;
	
	/**
	 * Gibt an, ob das Feld aus K�se besteht.
	 */
	private boolean isCheese = false;
	
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
		this.isCheese = feld.isCheese;
	}
	
	/**
	 * Stellt den Inhalt des Felds als String dar.
	 */
	public String toString() {
		if (this.isBlocked)
			return "X";
		return " ";
	}
	
	/**
	 * Markiert ein Feld als K�se.
	 */
	void setCheese(final boolean isCheese) {
		if (isCheese) {
			this.isCheese = true;
			this.isBlocked = true;
			this.color = Color.ORANGE;
		} else {
			this.isCheese = false;
			this.isBlocked = false;
			this.color = null;
		}
	}
	
	/**
	 * Gibt zur�ck, ob das Feld aus K�se besteht.
	 */
	boolean isCheese() {
		return this.isCheese;
	}
}