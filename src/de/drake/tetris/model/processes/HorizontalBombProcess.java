package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.animations.Animation;
import de.drake.tetris.model.animations.AnimationType;
import de.drake.tetris.model.util.Position;

public class HorizontalBombProcess extends Process {
	
	private final int row;
	
	public final Animation animation;

	public HorizontalBombProcess(final Player player, final Position position) {
		super(player);
		this.row = position.getY();
		
		player.destroyStone();
		this.animation = new Animation(AnimationType.DESTROY_ROW, position.getX(), position.getY());
		this.getPlayer().addAnimation(this.animation);
		
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
		player.removeAnimation(this.animation);
		
		HashSet<Integer> rowsToRemove = new HashSet<Integer>();
		rowsToRemove.add(this.row);
		player.startProcess(new FallingRowsProcess(
				player, rowsToRemove, 0, Config.breite - 1));
	}
	
}