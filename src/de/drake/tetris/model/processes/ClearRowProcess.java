package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.animations.Animation;
import de.drake.tetris.model.animations.AnimationType;

public class ClearRowProcess extends Process {
	
	private final HashSet<Integer> rowsToClear;
	
	public final HashSet<Animation> animations = new HashSet<Animation>();
	
	public ClearRowProcess(final Player player, final HashSet<Integer> rowsToClear) {
		super(player);
		this.rowsToClear = rowsToClear;
		for (int row : rowsToClear) {
			Animation animation = new Animation(AnimationType.CLEAR_ROW,
					Config.breite / 2. -.5, row);
			this.animations.add(animation);
			this.getPlayer().addAnimation(animation);
		}
		
		if (this.rowsToClear.size() >= 4) {
			Asset.SOUND_TETRIS.play();
		} else if (this.rowsToClear.size() > 0) {
			Asset.SOUND_ROW.play();
		}
	}
	
	@Override
	protected long getDuration() {
		return this.rowsToClear.isEmpty() ? 0l : 500000000l;
	}
	
	@Override
	protected void update() {
		for (Animation animation : this.animations) {
			animation.setProgress(this.getProgress());
		}
	}
	
	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		
		for (int row : this.rowsToClear) {
			player.getSpielfeld().clearRow(row);
		}
		for (Animation animation : this.animations) {
			player.removeAnimation(animation);
		}
		
		player.rowsCompleted(rowsToClear.size());
		player.startProcess(new FallingRowsProcess(player, this.rowsToClear, 0, Config.breite - 1));
	}
	
}