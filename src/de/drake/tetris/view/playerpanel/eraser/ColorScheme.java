package de.drake.tetris.view.playerpanel.eraser;

import java.awt.Color;

public class ColorScheme {
	
	private Color primaryColor, secondaryColor;
	
	public ColorScheme(final Color primaryColor, final Color secondaryColor) {
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}
	
	/**
	 * Gibt die Farbe zurück, die durch Mischung der Primary und Secondary Farbe entsteht.
	 * @param level
	 * 		Der Anteil der Primary-Farbe. Level 0. ergibt die Secondary Color, Level 1. ergibt die Primary Color.
	 */
	public Color getColor(final double level) {
		int red = (int) (this.secondaryColor.getRed() + level * (this.primaryColor.getRed() - this.secondaryColor.getRed()));
		int green = (int) (this.secondaryColor.getGreen() + level * (this.primaryColor.getGreen() - this.secondaryColor.getGreen()));
		int blue = (int) (this.secondaryColor.getBlue() + level * (this.primaryColor.getBlue() - this.secondaryColor.getBlue()));
		int alpha = (int) (level * 255);
		return new Color(red, green, blue, alpha);
	}
	
}