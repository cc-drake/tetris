package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;

public class HorizontalBombProcess extends Process {
	
	private final int row;

	public HorizontalBombProcess(final Player player, final int row) {
		super(player);
		this.row = row;
		
		this.player.destroyStone();
		Asset.SOUND_BOOM.play();
	}
	
	@Override
	protected long getDuration() {
		return 1000000000l;
	}

	@Override
	protected void update() {
	}

	@Override
	protected void processCompleted() {
		this.player.getSpielfeld().clearRow(this.row);
		
		HashSet<Integer> rowsToRemove = new HashSet<Integer>();
		rowsToRemove.add(this.row);
		this.player.startProcess(new FallingRowsProcess(
				this.player, rowsToRemove, 0, Config.breite - 1));
	}
	
}