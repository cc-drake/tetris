package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.assets.gfx.BlockTexture;

/**
 * Ein klassischer Tetrisstein ohne weitere Funktionen.
 */
abstract class DumbStone extends Stone {
	
	DumbStone(final BlockTexture texture) {
		super(texture);
	}
	
	@Override
	public void detonate() {
		this.spielfeld.addBlocks(this.getPositionen(), this.texture);
		Asset.SOUND_DROP.play();
	}
	
}