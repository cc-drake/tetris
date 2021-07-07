package de.drake.tetris.view.playerpanel.eraser;

import java.awt.Color;

public abstract class Eraser {
	
	/**
	 * Gibt an, ob der Eraser eine horizontale Komponente besitzt
	 * (d.h. Auflˆsung in vertikaler Richtung)
	 */
	private final boolean horizontal;
	
	/**
	 * Gibt an, ob der Eraser eine vertikale Komponente besitzt
	 * (d.h. Auflˆsung in vertikaler Richtung)
	 */
	private final boolean vertical;
	
	/**
	 * Horizontaler Radius des Erasers, d.h. horizontaler Abstand zwischen der aktuellen
	 * Eraser-Position und dem Rand der Grafik.
	 */
	private final double horizontalSize;
	
	/**
	 * Vertikaler Radius des Erasers, d.h. vertikaler Abstand zwischen der aktuellen
	 * Eraser-Position und dem Rand der Grafik.
	 */
	private final double verticalSize;
	
	/**
	 * X-Koordinate des Mittelpunkts, bei dem die Auflˆsung beginnt
	 */
	private final double centerX;
	
	/**
	 * Y-Koordinate des Mittelpunkts, bei dem die Auflˆsung beginnt
	 */
	private final double centerY;
	
	/**
	 * Zahl der Pixel in horizontaler Richtung, die sich der Eraser bereits vom Mittelpunkt
	 * entfernt hat
	 */
	private final double horizontalShift;
	
	/**
	 * Zahl der Pixel in vertikaler Richtung, die sich der Eraser bereits vom Mittelpunkt
	 * entfernt hat
	 */
	private final double verticalShift;
	
	/**
	 * Bevor der Eraser am Beginn startet, muss er eingeblendet werden.
	 * Der Fortschritt der Einblendung wird hiermit gekennzeichnet, d.h.
	 * dimmer = 0. => Eraser ist voll sichtbar
	 * dimmer >= 1. => Eraser ist unsichtbar
	 * 
	 */
	private final double dimmerX, dimmerY;
	
	protected Eraser(final boolean horizontal, final boolean vertical,
			final double horizontalSize, final double verticalSize,
			final double centerX, final double centerY,
			final double horizontalShift, final double verticalShift) {
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.horizontalSize = horizontalSize;
		this.verticalSize = verticalSize;
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (horizontalShift < 0.) {
			this.horizontalShift = 0.;
			this.dimmerX = - horizontalShift / horizontalSize;
		} else {
			this.horizontalShift = horizontalShift;
			this.dimmerX = 0.;
		}
		
		if (verticalShift < 0.) {
			this.verticalShift = 0.;
			this.dimmerY = - verticalShift / verticalSize;
		} else {
			this.verticalShift = verticalShift;
			this.dimmerY = 0.;
		}
	}

	/**
	 * Gibt die St‰rke des Erasers an der angegebenen Position zur¸ck. Sie h‰ngt vom
	 * Abstand zur aktuellen Eraser-Position und vom Dimmer ab.
	 * 
	 * level = 1. => Der Pixel befindet sich im Zentrum der Eraser-Grafik.
	 * level = 0. => Der Pixel befindet sich weit vom Eraser entfernt,
	 * 				  so dass er unver‰ndert bleibt.
	 */
	private double getLevel(final int x, final int y) {
		
		double distX = Math.min(diff(x, this.centerX + this.horizontalShift),
				diff(x, this.centerX - this.horizontalShift));
		double distY = Math.min(diff(y, this.centerY + this.verticalShift),
				diff(y, this.centerY - this.verticalShift));
		
		double levelX = this.vertical   ? 1. - distX / this.horizontalSize : 0.;
		double levelY = this.horizontal ? 1. - distY / this.verticalSize : 0.;
		double level = Math.max(levelX - this.dimmerX, levelY - this.dimmerY);
		return level < 0. ? 0. : level;
		
	}
	
	private double diff(final double x, final double y) {
		return Math.abs(x - y);
	}
	
	public Color getColor(final int x, final int y) {
		return this.getColor(this.getLevel(x, y));
	}
	
	/**
	 * Gibt die Farbe zur¸ck, die in Abh‰ngigkeit vom Level des Erasers
	 * ¸ber das Bild gezeichnet werden soll.
	 * 
	 * level = 1. => Ergibt die Farbe im Zentrum des Erasers. Der Alpha-Wert muss hier 255 sein.
	 * level nahe 1. => Ergibt die Farbe nahe des Zentrums des Erasers.
	 * level nahe 0. => Ergibt die Farbe am Rand des Erasers.
	 * level = 0. => Der Pixel befindet sich auﬂerhalb des Erasers. Der Alpha-Wert ist hier 0.
	 */
	protected abstract Color getColor(final double level);
	
}