package de.drake.tetris.model.animations;

import java.util.HashSet;

public class AnimationManager {
	
	private final HashSet<RowAnimation> rowAnimations = new HashSet<RowAnimation>();
	
	public void addAnimation(final RowAnimation animation) {
		this.rowAnimations.add(animation);
	}

	public void removeAnimation(final RowAnimation animation) {
		this.rowAnimations.remove(animation);
	}
	
	public HashSet<RowAnimation> getRowAnimations() {
		return new HashSet<RowAnimation>(this.rowAnimations);
	}

}