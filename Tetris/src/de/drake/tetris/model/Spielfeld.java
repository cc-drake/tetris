package de.drake.tetris.model;

import de.drake.tetris.config.Config;

import de.drake.tetris.data.Farbe;
import de.drake.tetris.data.Gitter;
import de.drake.tetris.data.Position;

/**
 * Das Spielfeld von Tetris. Dieses wird als ein Gitter angesehen - im Original mit einer Größe von 10x20 Feldern.
 */
public class Spielfeld {
	
	/**
	 * Die Breite des Spielfelds.
	 */
	private final int breite = Config.breite;
	
	/**
	 * Die Höhe des Spielfelds.
	 */
	private final int hoehe = Config.hoehe + Config.getMaxSteinSize();
	
	/**
	 * Ein Gitter, welche die im Spielfeld enthaltenen Felder beinhält.
	 */
	private final Gitter<Feld> gitter;
	
	/**
	 * Erzeugt ein neues Spielfeld.
	 */
	Spielfeld() {
		this.gitter = new Gitter<Feld>(this.breite, this.hoehe);
		for (int x = 0; x < this.breite; x++) {
			for (int y = 0; y < this.hoehe; y++) {
				this.gitter.set(x, y, new Feld());
			}
		}
	}
	
	/**
	 * Gibt an, ob das Spielfeld an der angegebenen Position blockiert ist. Liegt die Position links, rechts oder unten außerhalb des Spielfeldes,
	 * so wird stets true zurückgegeben.
	 */
	public boolean isBlocked(final Position position) {
		if (position.getX() < 0 || position.getX() >= this.breite || position.getY() < 0)
			return true;
		if (position.getY() >= this.hoehe)
			throw new Error ("Maximale Spielfeldhöhe überschritten");
		return this.gitter.get(position).isBlocked();
	}
	
	/**
	 * Blockiert ein Feld des Spielfelds mit der angegebenen Farbe.
	 * 
	 * @param position
	 * 		Die Position des Feldes, das geblockt werden soll.
	 * @param farbe
	 * 		Die Farbe, mit der das Feld geblockt werden soll.
	 */
	void block(final Position position, final Farbe farbe) {
		this.gitter.get(position).block(farbe);
	}
	
	/**
	 * Entfernt alle fertigen Reihen aus dem Spielfeld.
	 * 
	 * @return
	 * 		Die Anzahl der fertigen Reihen, die entfernt wurden.
	 */
	int entferneFertigeReihen() {
		int fertigeReihen = 0;
		boolean zeileFertig;
		for (int zeilenindex = this.hoehe - 1; zeilenindex >= 0; zeilenindex--) {
			zeileFertig = true;
			for (Feld feld : this.gitter.getZeile(zeilenindex)) {
				if (!feld.isBlocked()) {
					zeileFertig = false;
					break;
				}
			}
			if (zeileFertig) {
				this.entferneReihe(zeilenindex);
				fertigeReihen++;
			}
		}
		return fertigeReihen;
	}

	/**
	 * Entfernt eine Reihe aus dem Spielfeld und lässt die darüberliegenden Felder nachrutschen.
	 * 
	 * @param zeile
	 * 		Der Index der Zeile, die entfernt werden soll.
	 * 
	 */
	private void entferneReihe(final int zeile) {
		for (int y = zeile; y < this.hoehe; y++) {
			for (int x = 0; x < this.breite; x++) {
				if (y < this.hoehe - 1) {
					this.gitter.get(x, y).set(this.gitter.get(x, y + 1));
				} else {
					this.gitter.get(x, y).set(new Feld());
				}
			}
		}
	}
	
	/**
	 * Gibt die Farbe des Spielfelds an der angegebenen Position zurück.
	 */
	public Farbe getFarbe(final Position position) {
		return this.gitter.get(position).getFarbe();
	}
}