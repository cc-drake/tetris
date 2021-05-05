package de.drake.tetris.model;

import de.drake.tetris.assets.gfx.BlockTexture;

public class Block {
	
	private final BlockTexture texture;
	
	private final boolean isCheese;
	
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
	
}