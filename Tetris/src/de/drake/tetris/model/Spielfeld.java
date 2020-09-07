package de.drake.tetris.model;

import java.util.HashMap;

import de.drake.tetris.config.Config;
import de.drake.tetris.util.Color;
import de.drake.tetris.util.Position;

/**
 * Das Spielfeld von Tetris, in dem der Stein fällt.
 */
public class Spielfeld {
	
	/**
	 * Die Anzahl zusätzlicher, nicht sichtbarer Zeilen oberhalb des Spielfelds.
	 * Diese werden benötigt, wenn ein in der obersten Zeile befindlicher Stein gedreht wird
	 * und oben aus dem Spielfeld ragt.
	 */
	private final int zusatzzeilen = Config.getMaxSteinSize();
	
	/**
	 * Eine Liste der Felder des Spielfelds, strukturiert nach ihrer Position.
	 */
	private final HashMap<Position, Feld> felder = new HashMap<Position, Feld>();
	
	/**
	 * Erzeugt ein neues Spielfeld.
	 */
	Spielfeld() {
		for (int x = 0; x < Config.breite; x++) {
			for (int y = -this.zusatzzeilen; y < Config.hoehe; y++) {
				this.felder.put(new Position(x, y), new Feld());
			}
		}
	}
	
	/**
	 * Gibt an, ob das Spielfeld an der angegebenen Position blockiert ist.
	 * Für Positionen außerhalb des Spielfelds wird stets true zurückgegeben.
	 */
	public boolean isBlocked(final Position position) {
		if (!this.felder.containsKey(position))
			return true;
		return this.felder.get(position).isBlocked();
	}
	
	/**
	 * Blockiert ein Feld des Spielfelds mit der angegebenen Farbe.
	 * 
	 * @param position
	 * 		Die Position des Feldes, das geblockt werden soll.
	 * @param color
	 * 		Die Farbe, mit der das Feld geblockt werden soll.
	 */
	void block(final Position position, final Color color) {
		this.felder.get(position).block(color);
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
		for (int y = -this.zusatzzeilen; y < Config.hoehe; y++) {
			zeileFertig = true;
			for (int x = 0; x < Config.breite; x++) {
				if (!this.isBlocked(new Position(x, y))) {
					zeileFertig = false;
					break;
				}
			}
			if (zeileFertig) {
				this.entferneReihe(y);
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
		for (int y = zeile; y >= -this.zusatzzeilen; y--) {
			for (int x = 0; x < Config.breite; x++) {
				if (y == -this.zusatzzeilen) {
					this.felder.get(new Position(x, y)).set(new Feld());
				} else {
					this.felder.get(new Position(x, y)).set(this.felder.get(new Position(x, y - 1)));
				}
			}
		}
	}
	
	/**
	 * Gibt die Farbe des Spielfelds an der angegebenen Position zurück.
	 */
	public Color getColor(final Position position) {
		return this.felder.get(position).getColor();
	}
}