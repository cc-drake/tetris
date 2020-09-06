package de.drake.tetris.input;

import java.util.TimerTask;

import de.drake.tetris.data.Action;

class KeyTask extends TimerTask {
	
	private final KeyManager manager;
	
	private final Action action;

	KeyTask(final KeyManager manager, final Action action) {
		this.manager = manager;
		this.action = action;
	}

	@Override
	public void run() {
		this.manager.processKeyTask(action);
	}

}
