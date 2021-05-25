package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.processes.SquareBombProcess;

/**
 * Eine 3x3-Bombe.
 */
public class SquareBomb extends OneBlockStone {
	
	SquareBomb() {
		super(Asset.TEXTURE_SQUAREBOMB);
	}

	@Override
	public void detonate() {
		Player player = super.getPlayer();
		player.startProcess(new SquareBombProcess(player, this.getPosition()));
	}

	@Override
	public Stone clone() {
		return new SquareBomb();
	}
}