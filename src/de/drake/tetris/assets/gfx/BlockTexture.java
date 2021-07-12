package de.drake.tetris.assets.gfx;

import java.awt.image.BufferedImage;

public class BlockTexture {
	
	private final BufferedImage stoneImage;
	private final BufferedImage fieldImage;
	
	public BlockTexture(final BufferedImage stoneImage,
			final BufferedImage fieldImage) {
		this.stoneImage = stoneImage;
		this.fieldImage = fieldImage;
	}

	public BufferedImage getStoneTexture() {
		return this.stoneImage;
	}
	
	public BufferedImage getSpielfeldTexture() {
		return this.fieldImage;
	}
	
}