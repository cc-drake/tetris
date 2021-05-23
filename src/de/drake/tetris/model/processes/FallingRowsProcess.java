package de.drake.tetris.model.processes;

import java.util.Collections;
import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;

public class FallingRowsProcess extends Process {
	
	private final HashSet<Integer> rowsToRemove;
	
	private final int xMin, xMax;
	
	private final boolean hasBlocksToFall;
	
	public FallingRowsProcess(final Player player, final HashSet<Integer> rowsToRemove,
			final int xMin, final int xMax) {
		super(player);
		this.hasBlocksToFall = !rowsToRemove.isEmpty()
				&& player.getSpielfeld().hasBlocksAbove(rowsToRemove, xMin, xMax);
		this.rowsToRemove = rowsToRemove;
		this.xMin = xMin;
		this.xMax = xMax;
	}
	
	@Override
	protected long getDuration() {
		return this.hasBlocksToFall ? 1000000000l : 0l;
	}
	
	@Override
	protected void update() {
	}
	
	@Override
	protected void processCompleted() {
		if (!this.hasBlocksToFall) {
			this.player.startProcess(new AddRowsProcess(this.player));
			return;
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