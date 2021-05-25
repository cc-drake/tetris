package de.drake.tetris.model.processes;

import java.util.Collections;
import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;

public class FallingRowsProcess extends Process {
	
	private final HashSet<Integer> clearedRows;
	
	private final int xMin, xMax;
	
	private final boolean hasBlocksToFall;
	
	public FallingRowsProcess(final Player player, final HashSet<Integer> clearedRows,
			final int xMin, final int xMax) {
		super(player);
		this.clearedRows = clearedRows;
		this.xMin = xMin;
		this.xMax = xMax;
		this.hasBlocksToFall = this.clearedRows.isEmpty() ? false : this.player.getSpielfeld()
				.setMovingBlocks(Collections.max(this.clearedRows), xMin, xMax);
	}
	
	@Override
	protected long getDuration() {
		return this.hasBlocksToFall ? 100000000l : 0l;
	}
	
	@Override
	protected void update() {
		if (this.hasBlocksToFall) {
			this.player.getSpielfeld().moveBlocks(0, this.progress);	
		}
	}
	
	@Override
	protected void processCompleted() {
		if (!this.hasBlocksToFall) {
			this.player.startProcess(new AddRowsProcess(this.player));
			return;
		}
		
		Integer highestRow = Collections.max(this.clearedRows);
		this.player.getSpielfeld().moveBlocks(1, 0.);
		this.clearedRows.remove(highestRow);
		HashSet<Integer> remainingRows = new HashSet<Integer>();
		for (int row : this.clearedRows) {
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