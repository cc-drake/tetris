package de.drake.tetris.model.animations;

import de.drake.tetris.model.Player;

abstract class Animation {
	
	private final AnimationManager manager;
	
	private double progress = 0.;
	
	protected Animation(final Player player) {
		this.manager = player.getAnimationManager();
		this.registerAnimation(this.manager);
	}
	
	protected abstract void registerAnimation(final AnimationManager manager);
	
	protected abstract void unregisterAnimation(final AnimationManager manager);
	
	public void setProgress(final double progress) {
		this.progress = progress;
	}
	
	public void close() {
		this.unregisterAnimation(this.manager);
	}
	
	public double getProgress() {
		return this.progress;
	}
	
}