package de.drake.tetris.model.stones;

import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.processes.DropStoneProcess;

/**
 * Ein klassischer Tetrisstein ohne weitere Funktionen.
 */
abstract class DumbStone extends Stone {
	
	DumbStone(final BlockTexture texture) {
		super(texture);
	}
	
	@Override
	public final void detonate() {
		Player player = super.getPlayer();
		player.startProcess(new DropStoneProcess(player));
	}
	
}