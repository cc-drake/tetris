package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.animations.RowAnimation;
import de.drake.tetris.model.animations.RowAnimationType;

public class HorizontalBombProcess extends Process {
	
	private final int row;
	
	public final RowAnimation animation;

	public HorizontalBombProcess(final Player player, final int row) {
		super(player);
		this.row = row;
		this.animation = new RowAnimation(RowAnimationType.DESTROY_ROW, player, row);
		
		player.destroyStone();
		Asset.SOUND_BOOM.play();
	}
	
	@Override
	protected long getDuration() {
		return 1000000000l;
	}

	@Override
	protected void update() {
		this.animation.setProgress(this.getProgress());
	}

	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		player.getSpielfeld().clearRow(this.row);
		this.animation.close();
		
		HashSet<Integer> rowsToRemove = new HashSet<Integer>();
		rowsToRemove.add(this.row);
		player.startProcess(new FallingRowsProcess(
				player, rowsToRemove, 0, Config.breite - 1));
	}
	
}