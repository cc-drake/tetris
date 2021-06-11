package de.drake.tetris.model.spielfeld;

import de.drake.tetris.assets.gfx.BlockTexture;

public class Block implements BlockPaintObject {
	
	private final int x;
	
	private double y;
	
	private final BlockTexture texture;
	
	private final boolean isCheese;
	
	Block(final int x, final int y, final BlockTexture texture, final boolean isCheese) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.isCheese = isCheese;
	}
	
	public void move(final double y) {
		this.y += y;
	}

	public void roundPosition() {
		this.y = Math.round(this.y);
	}
	
	boolean isCheese() {
		return this.isCheese;
	}
	
	int getX() {
		return this.x;
	}
	
	int getY() {
		return (int) Math.round(this.y);
	}
	
	@Override
	public double getDoubleX() {
		return this.x;
	}

	@Override
	public double getDoubleY() {
		return this.y;
	}
	
	@Override
	public BlockTexture getTexture() {
		return this.texture;
	}
	
}