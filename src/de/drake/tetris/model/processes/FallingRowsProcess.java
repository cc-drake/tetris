package de.drake.tetris.model.processes;

import java.util.Collections;
import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Block;
import de.drake.tetris.model.Player;

public class FallingRowsProcess extends Process {
	
	private final HashSet<Integer> rowsToRemove;
	
	private final int xMin, xMax;
	
	private final HashSet<Block> fallingBlocks;
	
	public FallingRowsProcess(final Player player, final HashSet<Integer> rowsToRemove,
			final int xMin, final int xMax) {
		super(player);
		this.fallingBlocks = player.getSpielfeld().getBlocksAbove(rowsToRemove, xMin, xMax);
		this.rowsToRemove = rowsToRemove;
		this.xMin = xMin;
		this.xMax = xMax;
	}
	
	@Override
	protected long getDuration() {
		return this.fallingBlocks.isEmpty() ? 0l : 100000000l;
	}
	
	@Override
	protected void update() {
		for (Block block : this.fallingBlocks) {
			block.setVerticalShift(this.progress);
		}
	}
	
	@Override
	protected void processCompleted() {
		if (this.fallingBlocks.isEmpty()) {
			this.player.startProcess(new AddRowsProcess(this.player));
			return;
		}
		
		for (Block block : this.fallingBlocks) {
			block.setVerticalShift(0.);
		}
		
		Integer highestRow = Collections.max(this.rowsToRemove);
		this.player.getSpielfeld().removeRow(highestRow, this.xMin, this.xMax);
		this.rowsToRemove.remove(highestRow);
		HashSet<Integer> remainingRows = new HashSet<Integer>();
		for (int row : this.rowsToRemove) {
			remainingRows.add(row + 1);
		}
		
		if (remainingRows.size() == 0) {
			Asset.SOUND_FALL.play();
			this.player.startProcess(new AddRowsProcess(this.player));
		} else {
			this.player.startProcess(new FallingRowsProcess(
					this.player, remainingRows, this.xMin, this.xMax));
		}
		
	}
	
}