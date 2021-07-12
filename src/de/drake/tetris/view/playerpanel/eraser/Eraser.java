package de.drake.tetris.view.playerpanel.eraser;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.drake.tetris.model.animations.Animation;

public class Eraser {
	
	/**
	 * Gibt an, ob der Eraser eine horizontale Komponente besitzt
	 * (d.h. Auflösung in vertikaler Richtung)
	 */
	private final boolean horizontal;
	
	/**
	 * Gibt an, ob der Eraser eine vertikale Komponente besitzt
	 * (d.h. Auflösung in vertikaler Richtung)
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
	 * X-Koordinate des Mittelpunkts, bei dem die Auflösung beginnt
	 */
	private final double centerX;
	
	/**
	 * Y-Koordinate des Mittelpunkts, bei dem die Auflösung beginnt
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
	 * Farbschema für den Eraser, mit dem der Farbverlauf definiert wird.
	 */
	private final ColorScheme colorScheme;
	
	/**
	 * Bevor der Eraser am Beginn startet, muss er eingeblendet werden.
	 * Der Fortschritt der Einblendung wird hiermit gekennzeichnet, d.h.
	 * dimmer = 0. => Eraser ist voll sichtbar
	 * dimmer >= 1. => Eraser ist unsichtbar
	 */
	private final double dimmerX, dimmerY;
	
	Eraser(final Animation animation, final int block_width, final int block_height, final double sizeFactor, final double aoE,
			final boolean horizontal, final boolean vertical, final ColorScheme colorScheme) {
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.horizontalSize = block_width * sizeFactor;
		this.verticalSize = block_height * sizeFactor;
		this.centerX = block_width * (animation.getColumn() + .5) - .5;
		this.centerY = block_height * (animation.getRow() + .5) - .5;
		double maxShiftX = aoE * block_width;
		double maxShiftY = aoE * block_height;
		
		if (this.horizontal) {
			double signedHorizontalShift = - this.horizontalSize
					+ animation.getProgress() * (maxShiftX + 2 * this.horizontalSize);
			if (signedHorizontalShift < 0.) {
				this.horizontalShift = 0.;
				this.dimmerX = - signedHorizontalShift / this.horizontalSize;
			} else if (signedHorizontalShift > maxShiftX) {
				this.horizontalShift = maxShiftX;
				this.dimmerX = (signedHorizontalShift - maxShiftX) / this.horizontalSize;
			} else {
				this.horizontalShift = signedHorizontalShift;
				this.dimmerX = 0.;
			}
		} else {
			this.horizontalShift = block_width / 2. - 0.5;
			this.dimmerX = 0.;
		}
		
		if (this.vertical) {
			double signedVerticalShift = - this.verticalSize
					+ animation.getProgress() * (maxShiftY + 2 * this.verticalSize);
			if (signedVerticalShift < 0.) {
				this.verticalShift = 0.;
				this.dimmerY = - signedVerticalShift / this.verticalSize;
			} else if (signedVerticalShift > maxShiftY) {
				this.verticalShift = maxShiftY;
				this.dimmerY = (signedVerticalShift - maxShiftY) / this.verticalSize;
			} else {
				this.verticalShift = signedVerticalShift;
				this.dimmerY = 0.;
			}
		} else {
			this.verticalShift = block_height / 2. - 0.5;
			this.dimmerY = 0.;
		}
		
		this.colorScheme = colorScheme;
	}
	
	public void paint(final BufferedImage image) {
		
		//Rechteck ausschneiden
		Graphics2D g = image.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		int x = (int) Math.ceil(this.centerX - this.horizontalShift);
		int y = (int) Math.ceil(this.centerY - this.verticalShift);
		int width = this.horizontalShift == 0 ? 0 : 1 + (int) Math.floor(this.centerX + this.horizontalShift) - x;
		int height =  this.verticalShift == 0 ? 0 : 1 + (int) Math.floor(this.centerY + this.verticalShift) - y;
		g.fillRect(x, y, width, height);
		g.dispose();
		
		//Eraser zeichnen
		BufferedImage eraserImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);//TODO
//		for (int x = 0; x < eraserImage.getWidth(); x++) {
//			for (int y = 0; y < eraserImage.getHeight(); y++) {
//				x / this.block_width
//			}
//		}
//		g.setClip(0, y,
//				(int) Math.ceil(blocklayer.getWidth() / 2.),
//				this.block_height);
//		g.drawImage(eraser,
//				(int) Math.floor(leftEraser - eraser.getWidth() / 2.), y,
//				null);
//		g.setClip((int) Math.floor(blocklayer.getWidth() / 2.), y,
//				(int) Math.ceil(blocklayer.getWidth() / 2.),
//				this.block_height);
//		g.drawImage(eraser,
//				(int) Math.ceil(rightEraser - eraser.getWidth() / 2.), y,
//				null);
		
		//Grafiken kombinieren
		g = image.createGraphics();
		g.drawImage(eraserImage, 0, 0, null);
		g.dispose();
		
	}

	/**
	 * Gibt die Stärke des Erasers an der angegebenen Position zurück. Sie hängt vom
	 * Abstand zur aktuellen Eraser-Position und vom Dimmer ab.
	 * 
	 * level = 1. => Der Pixel befindet sich im Zentrum der Eraser-Grafik.
	 * level = 0. => Der Pixel befindet sich weit vom Eraser entfernt,
	 * 				  so dass er unverändert bleibt.
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
	
}