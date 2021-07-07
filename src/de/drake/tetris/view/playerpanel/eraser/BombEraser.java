package de.drake.tetris.view.playerpanel.eraser;

import java.awt.Color;

public class BombEraser extends Eraser {

	public BombEraser(final boolean horizontal, final boolean vertical,
			final double horizontalSize, final double verticalSize,
			final double centerX, final double centerY,
			final double horizontalShift, final double verticalShift) {
		super(horizontal, vertical, horizontalSize, verticalSize, centerX, centerY,
				horizontalShift, verticalShift);
	}

	@Override
	protected Color getColor(final double level) {
		int red = 255;
		int green = (int) (216 + level * (106-216));
		int blue = 0;
		int alpha = (int) (level * 255);
		return new Color(red, green, blue, alpha);
	}

}
