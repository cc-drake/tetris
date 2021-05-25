package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;

public class ClearRowProcess extends Process {
	
	private final HashSet<Integer> rowsToClear;
	
	public ClearRowProcess(final Player player, final HashSet<Integer> rowsToClear) {
		super(player);
		this.rowsToClear = rowsToClear;
		
		if (this.rowsToClear.size() >= 4) {
			Asset.SOUND_TETRIS.play();
		} else if (this.rowsToClear.size() > 0) {
			Asset.SOUND_ROW.play();
		}
	}
	
	@Override
	protected long getDuration() {
		return this.rowsToClear.isEmpty() ? 0l : 1000000000l;
	}
	
	@Override
	protected void update() {
	}
	
	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		
		for (int row : this.rowsToClear) {
			player.getSpielfeld().clearRow(row);
		}
		
		player.rowsCompleted(rowsToClear.size());
		player.startProcess(new FallingRowsProcess(player, this.rowsToClear, 0, Config.breite - 1));
	}
	
}