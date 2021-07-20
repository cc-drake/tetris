package de.drake.tetris.model.processes;

import java.util.Collections;
import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.assets.audio.SoundPlayer;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.processes.util.BlockMover;
import de.drake.tetris.model.spielfeld.Block;

public class FallingRowsProcess extends Process {
	
	private final HashSet<Integer> clearedRows;
	
	private final int xMin, xMax;
	
	private final BlockMover mover;
	
	public FallingRowsProcess(final Player player, final HashSet<Integer> clearedRows,
			final int xMin, final int xMax) {
		super(player);
		this.clearedRows = clearedRows;
		this.xMin = xMin;
		this.xMax = xMax;
		if (!this.clearedRows.isEmpty()) {
			HashSet<Block> movingBlocks = player.getSpielfeld()
					.getBlocks(Collections.max(this.clearedRows), xMin, xMax);
			this.mover = movingBlocks.isEmpty() ? null : new BlockMover(movingBlocks, 1);
		} else {
			this.mover = null;
		}
	}
	
	@Override
	protected long getDuration() {
		return this.mover == null ? 0l : 50000000l;
	}
	
	@Override
	protected void update() {
		if (this.mover != null) {
			this.mover.move(this.getProgress());	
		}
	}
	
	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		if (this.mover == null) {
			player.startProcess(new AddRowsProcess(player));
			return;
		}
		
		Integer highestRow = Collections.max(this.clearedRows);
		this.clearedRows.remove(highestRow);
		HashSet<Integer> remainingRows = new HashSet<Integer>();
		for (int row : this.clearedRows) {
			remainingRows.add(row + 1);
		}
		
		if (remainingRows.size() == 0) {
			SoundPlayer.play(Asset.SOUND_FALL);
			player.startProcess(new AddRowsProcess(player));
		} else {
			player.startProcess(new FallingRowsProcess(
					player, remainingRows, this.xMin, this.xMax));
		}
		
	}
	
}