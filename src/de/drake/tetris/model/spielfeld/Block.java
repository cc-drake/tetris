package de.drake.tetris.model.spielfeld;

import java.awt.image.BufferedImage;

import de.drake.tetris.assets.gfx.BlockTexture;

class Block implements BlockPaintObject {
	
	private final int x;
	
	private int y;
	
	private final BlockTexture texture;
	
	private final boolean isCheese;
	
	private BufferedImage currentAnimation = null;
	
	private double verticalShift = 0.;
	
	Block(final int x, final int y, final BlockTexture texture, final boolean isCheese) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.isCheese = isCheese;
	}
	
	synchronized void move(final int y, final double newVerticalShift) {
		this.y += y;
		this.verticalShift = newVerticalShift;
	}
	
	void setAnimation(final BufferedImage animation) {
		this.currentAnimation = animation;
	}
	
	boolean isCheese() {
		return this.isCheese;
	}
	
	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}
	
	@Override
	public double getDoubleX() {
		return this.x;
	}

	@Override
	public synchronized double getDoubleY() {
		return this.y + this.verticalShift;
	}
	
	@Override
	public BlockTexture getTexture() {
		return this.texture;
	}
	
	@Override
	public BufferedImage getAnimation() {
		return this.currentAnimation;
	}
	
}