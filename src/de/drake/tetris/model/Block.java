package de.drake.tetris.model;

import java.awt.image.BufferedImage;

import de.drake.tetris.assets.gfx.BlockTexture;

public class Block {
	
	private final BlockTexture texture;
	
	private final boolean isCheese;
	
	private BufferedImage currentAnimation = null;
	
	private double verticalShift = 0.;
	
	Block(final BlockTexture texture, final boolean isCheese) {
		this.texture = texture;
		this.isCheese = isCheese;
	}

	public boolean isCheese() {
		return this.isCheese;
	}
	
	public BlockTexture getTexture() {
		return this.texture;
	}
	
	public void setVerticalShift(final double shift) {
		this.verticalShift = shift;
	}
	
	public double getVerticalShift() {
		return this.verticalShift;
	}
	
	public void setAnimation(final BufferedImage animation) {
		this.currentAnimation = animation;
	}
	
	public BufferedImage getAnimation() {
		return this.currentAnimation;
	}
	
}