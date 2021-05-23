package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.processes.VerticalBombProcess;

/**
 * Eine vertikale Bombe.
 */
public class VerticalBomb extends OneBlockStone {
	
	VerticalBomb() {
		super(Asset.TEXTURE_VERTICALBOMB);
	}

	@Override
	public void detonate() {
		this.player.startProcess(new VerticalBombProcess(this.player, this.mittelpunkt.getX()));
	}

	@Override
	public Stone clone() {
		return new VerticalBomb();
	}
}