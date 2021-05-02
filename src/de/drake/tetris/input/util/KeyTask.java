package de.drake.tetris.input.util;

import java.util.TimerTask;

import de.drake.tetris.model.util.Action;

class KeyTask extends TimerTask {
	
	private final InputHandler manager;
	
	private final Action action;

	KeyTask(final InputHandler manager, final Action action) {
		this.manager = manager;
		this.action = action;
	}

	@Override
	public void run() {
		this.manager.processKeyTask(action);
	}

}
