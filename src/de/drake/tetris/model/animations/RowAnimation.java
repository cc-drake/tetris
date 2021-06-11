package de.drake.tetris.model.animations;

import de.drake.tetris.model.Player;

public class RowAnimation extends Animation {
	
	private final RowAnimationType type;
	
	private final int row;
	
	public RowAnimation(final RowAnimationType type, final Player player,
			final int row) {
		super(player);
		this.type = type;
		this.row = row;
	}
	
	public int getRow() {
		return this.row;
	}
	
	@Override
	protected void registerAnimation(final AnimationManager manager) {
		manager.addAnimation(this);
	}
	
	@Override
	protected void unregisterAnimation(final AnimationManager manager) {
		manager.removeAnimation(this);
	}
	
	public RowAnimationType getType() {
		return this.type;
	}
	
}