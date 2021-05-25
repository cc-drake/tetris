package de.drake.tetris.model.processes;

import de.drake.tetris.model.Player;

public abstract class Process {
	
	private final Player player;
	
	private final long startTime;
	
	private double progress;
	
	protected Process(final Player player) {
		this.player = player;
		this.startTime = player.getLaufzeit();
		this.progress = 0.;
	}
	
	protected abstract long getDuration();
	
	protected abstract void update();
	
	protected abstract void processCompleted();
	
	public final void tick() {
		
		// Fortschritt aktualisieren
		this.progress = this.getDuration() == 0 ?
				1. : Math.min((double) (this.player.getLaufzeit() - this.startTime)
				/ ((double) this.getDuration()), 1.);
		
		// Prozess aktualisieren
		this.update();
		
		// Prozess beenden, wenn er abgeschlossen ist.
		if (this.progress >= 1.) {
			this.processCompleted();
		}
		
	}
	
	protected final Player getPlayer() {
		return this.player;
	}
	
	protected final double getProgress() {
		return this.progress;
	}
	
}