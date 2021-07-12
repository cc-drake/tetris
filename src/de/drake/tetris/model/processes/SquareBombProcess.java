package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.animations.Animation;
import de.drake.tetris.model.animations.AnimationType;
import de.drake.tetris.model.util.Position;

public class SquareBombProcess extends Process {
	
	private final Position mittelpunkt;
	
	public final Animation animation;

	public SquareBombProcess(final Player player, final Position mittelpunkt) {
		super(player);
		this.mittelpunkt = mittelpunkt;
		
		player.getSpielfeld().addBlocks(player.getStone().getPositionen(), player.getStone().getTexture(), false);
		player.destroyStone();
		this.animation = new Animation(AnimationType.DESTROY_SQUARE, mittelpunkt.getX(), mittelpunkt.getY());
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
		player.getSpielfeld().clear3x3(this.mittelpunkt);
		player.removeAnimation(this.animation);
		
		HashSet<Integer> rowsToRemove = new HashSet<Integer>();
		rowsToRemove.add(this.mittelpunkt.getY() - 1);
		rowsToRemove.add(this.mittelpunkt.getY());
		if (this.mittelpunkt.getY() < Config.hoehe - 1)
			rowsToRemove.add(this.mittelpunkt.getY() + 1);
		player.startProcess(new FallingRowsProcess(
				player, rowsToRemove, this.mittelpunkt.getX() - 1, this.mittelpunkt.getX() + 1));
	}
	
}