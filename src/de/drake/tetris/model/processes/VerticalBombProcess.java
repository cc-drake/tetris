package de.drake.tetris.model.processes;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.animations.Animation;
import de.drake.tetris.model.animations.AnimationType;
import de.drake.tetris.model.util.Position;

public class VerticalBombProcess extends Process {

	private final int column;
	
	public final Animation animation;

	public VerticalBombProcess(final Player player, final Position position) {
		super(player);
		this.column = position.getX();
		
		player.getSpielfeld().addBlocks(player.getStone().getPositionen(), player.getStone().getTexture(), false);
		player.destroyStone();
		this.animation = new Animation(AnimationType.DESTROY_COLUMN, position.getX(), position.getY());
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
		player.getSpielfeld().clearColumn(this.column);
		player.removeAnimation(this.animation);
		player.startProcess(new AddRowsProcess(player));
	}
	
}