package de.drake.tetris.model.processes;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;

public class AddRowsProcess extends Process {
	
	private final int rowsToAdd;
	
	public AddRowsProcess(final Player player) {
		super(player);
		this.rowsToAdd = player.getPendingRows();
		player.getSpielfeld().generateCheeseRows(this.rowsToAdd, true);
		
		player.getSpielfeld().setMovingBlocks(Config.hoehe, 0, Config.breite - 1);
		if (this.rowsToAdd >= 4) {
			Asset.SOUND_ADDFOUR.play();
		} else if (this.rowsToAdd > 0) {
			Asset.SOUND_ADD.play();
		}
	}
	
	@Override
	protected long getDuration() {
		return this.rowsToAdd == 0 ? 0l : 100000000l;
	}
	
	@Override
	protected void update() {
		super.getPlayer().getSpielfeld().moveBlocks(0, this.rowsToAdd * (1. - super.getProgress()));
	}
	
	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		player.getSpielfeld().moveBlocks(0, 0.);
		player.reducePendingRows(this.rowsToAdd);
		player.spawnStone();
	}
	
}