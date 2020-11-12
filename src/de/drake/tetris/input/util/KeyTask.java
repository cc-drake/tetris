package de.drake.tetris.input.util;

import java.util.TimerTask;

import de.drake.tetris.util.Action;

class KeyTask extends TimerTask {
	
	private final InputManager manager;
	
	private final Action action;

	KeyTask(final InputManager manager, final Action action) {
		this.manager = manager;
		this.action = action;
	}

	@Override
	public void run() {
		this.manager.processKeyTask(action);
	}

}
