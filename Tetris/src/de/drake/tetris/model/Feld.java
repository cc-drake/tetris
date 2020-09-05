package de.drake.tetris.model;

import de.drake.tetris.data.Farbe;

/**
 * Ein Feld innerhalb des Tetris-Spielfeldes.
 */
class Feld {
	
	/**
	 * Die Farbe des Feldes, sofern es geblockt ist. 
	 */
	private Farbe farbe;
	
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
	 * @param farbe
	 * 		Die Farbe, mit der das Feld geblockt werden soll.
	 */
	void block(final Farbe farbe) {
		this.isBlocked = true;
		this.farbe = farbe;
	}
	
	/**
	 * Gibt die Farbe des Feldes zur�ck.
	 */
	Farbe getFarbe() {
		return this.farbe;
	}
	
	/**
	 * �bernimmt die Attribute eines anderen Feldes.
	 * 
	 * @param feld
	 * 		Das Feld, dessen Attribute �bernommen werden sollen.
	 */
	void set(final Feld feld) {
		this.farbe = feld.farbe;
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