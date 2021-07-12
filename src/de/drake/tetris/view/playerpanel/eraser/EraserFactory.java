package de.drake.tetris.view.playerpanel.eraser;

import java.awt.Color;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.animations.Animation;

public class EraserFactory {
	
	private final static Color rowEraserColor = new Color(86, 255, 249);
	private final static Color primaryBombColor = new Color(255, 106, 0);
	private final static Color secondaryBombColor = new Color(255, 216, 0);
	
	public static Eraser createClearRowEraser(final Animation animation, final int block_width, final int block_height) {
		double sizeFactor = 0;
		double aoE = Config.breite / 2.;
		boolean horizontal = true;
		boolean vertical = false;
		ColorScheme colorScheme = new ColorScheme(EraserFactory.rowEraserColor, EraserFactory.rowEraserColor);
		return new Eraser(animation, block_width, block_height, sizeFactor, aoE, horizontal, vertical, colorScheme);
	}
	
	public static Eraser createDestroyRowEraser(final Animation animation, final int block_width, final int block_height) {
		double sizeFactor = 0;
		double aoE = Math.max(0.5 + animation.getColumn(), Config.breite - animation.getColumn() - 0.5);
		boolean horizontal = true;
		boolean vertical = false;
		ColorScheme colorScheme = new ColorScheme(EraserFactory.primaryBombColor, EraserFactory.secondaryBombColor);
		return new Eraser(animation, block_width, block_height, sizeFactor, aoE, horizontal, vertical, colorScheme);
	}
	
	public static Eraser createDestroyColumnEraser(final Animation animation, final int block_width, final int block_height) {
		double sizeFactor = 0;
		double aoE = Math.max(0.5 + animation.getRow(), Config.hoehe - animation.getRow() - 0.5);
		boolean horizontal = false;
		boolean vertical = true;
		ColorScheme colorScheme = new ColorScheme(EraserFactory.primaryBombColor, EraserFactory.secondaryBombColor);
		return new Eraser(animation, block_width, block_height, sizeFactor, aoE, horizontal, vertical, colorScheme);
	}
	
	public static Eraser createDestroySquareEraser(final Animation animation, final int block_width, final int block_height) {
		double sizeFactor = 0;
		double aoE = 1.5;
		boolean horizontal = true;
		boolean vertical = true;
		ColorScheme colorScheme = new ColorScheme(EraserFactory.primaryBombColor, EraserFactory.secondaryBombColor);
		return new Eraser(animation, block_width, block_height, sizeFactor, aoE, horizontal, vertical, colorScheme);
	}
	
}