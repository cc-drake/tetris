package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;
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
		Player player = super.getPlayer();
		player.startProcess(new VerticalBombProcess(player, this.getPosition().getX()));
	}

	@Override
	public Stone clone() {
		return new VerticalBomb();
	}
}