package de.drake.tetris.model.animations;

public class AnimationManager {
	
	private RowAnimation rowAnimation = null;
	
	public void setRowAnimation(final RowAnimation animation) {
		this.rowAnimation = animation;
	}

	public void clearAnimation() {
		this.rowAnimation = null;
	}
	
	public RowAnimation getRowAnimation() {
		return this.rowAnimation;
	}

}