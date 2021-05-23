package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.processes.HorizontalBombProcess;

/**
 * Eine horizontale Bombe.
 */
public class HorizontalBomb extends OneBlockStone {
	
	HorizontalBomb() {
		super(Asset.TEXTURE_HORIZONTALBOMB);
	}
	
	@Override
	public void detonate() {
		this.player.startProcess(new HorizontalBombProcess(this.player, this.mittelpunkt.getY()));
	}
	
	@Override
	public Stone clone() {
		return new HorizontalBomb();
	}
}