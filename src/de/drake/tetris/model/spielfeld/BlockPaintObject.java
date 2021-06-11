package de.drake.tetris.model.spielfeld;

import de.drake.tetris.assets.gfx.BlockTexture;

public interface BlockPaintObject {
	
	public BlockTexture getTexture();
	
	public double getDoubleX();
	
	public double getDoubleY();
	
}