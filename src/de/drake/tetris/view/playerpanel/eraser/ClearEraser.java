package de.drake.tetris.view.playerpanel.eraser;

import java.awt.Color;

public class ClearEraser extends Eraser {

	public ClearEraser(final double horizontalSize, final double verticalSize,
			final double centerX, final double centerY,
			final double horizontalShift, final double verticalShift) {
		super(true, false, horizontalSize, verticalSize, centerX, centerY,
				horizontalShift, verticalShift);
	}

	@Override
	protected Color getColor(final double level) {
		int red = 86;
		int green = 255;
		int blue = 249;
		int alpha = (int) (level * 255);
		return new Color(red, green, blue, alpha);
	}

}
