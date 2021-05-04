package de.drake.tetris.assets.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private final BufferedImage sheet;
	
	private final int breite;
	
	private final int hoehe;
	
	public SpriteSheet(final BufferedImage sheet, final int breite, final int hoehe) {
		this.sheet = sheet;
		this.breite = breite;
		this.hoehe = hoehe;
	}
	
	public BufferedImage getSprite(final int zeile, final int spalte) {
		return this.sheet.getSubimage(spalte * this.breite, zeile * this.hoehe,
				this.breite, this.hoehe);
	}
}