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
		
		player.destroyStone();
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
		Player player = super.getPlayer();
		player.getSpielfeld().clearRow(this.row);
		
		HashSet<Integer> rowsToRemove = new HashSet<Integer>();
		rowsToRemove.add(this.row);
		player.startProcess(new FallingRowsProcess(
				player, rowsToRemove, 0, Config.breite - 1));
	}
	
}