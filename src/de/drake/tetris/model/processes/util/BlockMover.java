package de.drake.tetris.model.processes.util;

import java.util.HashSet;

import de.drake.tetris.model.spielfeld.Block;

public class BlockMover {
		
	private final HashSet<Block> movingBlocks;
	
	private final int movingAmount;
	
	private double cumulativeMovement = 0.;
	
	public BlockMover(final HashSet<Block> movingBlocks, final int movingAmount) {
		this.movingBlocks = movingBlocks;
		this.movingAmount = movingAmount;
	}
	
	public void move(final double progress) {
		double currentBlockshift = progress * this.movingAmount;
		double newMovement = currentBlockshift - this.cumulativeMovement;
		this.cumulativeMovement += newMovement;
		for (Block block : this.movingBlocks) {
			block.move(newMovement);
			if (progress == 1.)
				block.roundPosition();
		}
	}
	
}