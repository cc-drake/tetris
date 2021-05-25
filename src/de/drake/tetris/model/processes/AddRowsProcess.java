package de.drake.tetris.model.processes;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;

public class AddRowsProcess extends Process {
	
	private final int rowsToAdd;
	
	public AddRowsProcess(final Player player) {
		super(player);
		this.rowsToAdd = player.getPendingRows();
		
		if (this.rowsToAdd >= 4) {
			Asset.SOUND_ADDFOUR.play();
		} else if (this.rowsToAdd > 0) {
			Asset.SOUND_ADD.play();
		}
	}
	
	@Override
	protected long getDuration() {
		return this.rowsToAdd == 0 ? 0l : 1000000000l;
	}
	
	@Override
	protected void update() {
	}
	
	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		player.getSpielfeld().generateCheeseRows(this.rowsToAdd);
		player.reducePendingRows(this.rowsToAdd);
		player.spawnStone();
	}
	
}