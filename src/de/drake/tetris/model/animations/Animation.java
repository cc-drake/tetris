package de.drake.tetris.model.animations;

public class Animation {
	
	private final AnimationType type;
	
	private final double column;
	
	private final double row;
	
	private double progress = 0.;
	
	public Animation(final AnimationType type, final double column, final double row) {
		this.type = type;
		this.column = column;
		this.row = row;
	}
	
	public void setProgress(final double progress) {
		this.progress = progress;
	}
	
	public AnimationType getType() {
		return this.type;
	}
	
	public double getColumn() {
		return this.column;
	}
	
	public double getRow() {
		return this.row;
	}
	
	public double getProgress() {
		return this.progress;
	}
	
}