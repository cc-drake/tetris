package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.processes.util.BlockMover;
import de.drake.tetris.model.spielfeld.Block;

public class AddRowsProcess extends Process {
	
	private final int rowsToAdd;
	
	private final BlockMover mover;
	
	public AddRowsProcess(final Player player) {
		super(player);
		this.rowsToAdd = player.getPendingRows();
		
		for (int row = Config.hoehe; row < Config.hoehe + this.rowsToAdd; row++) {
			player.getSpielfeld().generateCheeseRow(row);
		}
		HashSet<Block> movingBlocks = player.getSpielfeld().getBlocks(
				Integer.MAX_VALUE, 0, Config.breite - 1);
		this.mover = new BlockMover(movingBlocks, - this.rowsToAdd);
		
		if (this.rowsToAdd >= 4) {
			Asset.SOUND_ADDFOUR.play();
		} else if (this.rowsToAdd > 0) {
			Asset.SOUND_ADD.play();
		}
	}
	
	@Override
	protected long getDuration() {
		return this.rowsToAdd * 50000000l;
	}
	
	@Override
	protected void update() {
		this.mover.move(this.getProgress());
	}
	
	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		player.reducePendingRows(this.rowsToAdd);
		player.spawnStone();
	}
	
}