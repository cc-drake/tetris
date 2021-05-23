package de.drake.tetris.model.stones;

import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.model.processes.DropStoneProcess;

/**
 * Ein klassischer Tetrisstein ohne weitere Funktionen.
 */
abstract class DumbStone extends Stone {
	
	DumbStone(final BlockTexture texture) {
		super(texture);
	}
	
	@Override
	public void detonate() {
		this.player.startProcess(new DropStoneProcess(this.player));
	}
	
}