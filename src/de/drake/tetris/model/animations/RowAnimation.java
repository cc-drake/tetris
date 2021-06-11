package de.drake.tetris.model.animations;

import java.util.HashSet;

import de.drake.tetris.model.Player;

public class RowAnimation extends Animation {
	
	private final RowAnimationType type;
	
	private final HashSet<Integer> rows;
	
	public RowAnimation(final RowAnimationType type, final Player player,
			final HashSet<Integer> rows) {
		super(player);
		this.type = type;
		this.rows = rows;
	}
	
	public HashSet<Integer> getRows() {
		return this.rows;
	}

	@Override
	protected void registerAnimation(final AnimationManager manager) {
		manager.setRowAnimation(this);
	}

	@Override
	protected void unregisterAnimation(final AnimationManager manager) {
		manager.clearAnimation();
	}
	
	public RowAnimationType getType() {
		return this.type;
	}
	
}