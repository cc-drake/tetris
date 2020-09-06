package de.drake.tetris.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.KeyBinding;
import de.drake.tetris.data.Action;

abstract class KeyManager implements InputManager, KeyListener, FocusListener {
	
	private KeyBinding keyBinding;
	
	/**
	 * FIFO-Speicher zum Puffern von Tasteneingaben.
	 */
	private ConcurrentLinkedQueue<Action> actionQueue = new ConcurrentLinkedQueue<Action>();
	
	/**
	 * Speichert die KeyCodes der aktuell gedr�ckten Tasten. Wird ben�tigt, um das windowseigene 
	 * Key-Repeat bei gedr�ckter Taste zu unterdr�cken.
	 */
	private HashSet<Integer> pressedKeys = new HashSet<Integer>();
	
	/**
	 * Timer, die beim Gedr�ckthalten einer Taste nach einer bestimmten Zeit die zugeh�rige Aktion
	 * automatisch erneut ausl�sen.
	 */
	private HashMap<Action, Timer> timers = new HashMap<Action, Timer>();
	
	protected KeyManager(final KeyBinding keyBinding) {
		this.keyBinding = keyBinding;
	}
	
	void processKeyTask(final Action action) {
		this.actionQueue.add(action);
	}
	
	@Override
	public Action getNextAction(final int currentState) {
		return this.actionQueue.poll();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.keyBinding.isRelevant(e) || this.pressedKeys.contains(e.getKeyCode())) {
			return;
		}
		this.pressedKeys.add(e.getKeyCode());
			
		Action action = this.keyBinding.getAction(e);
		this.actionQueue.add(action);
		if (action == Action.LINKS || action == Action.RECHTS || action == Action.RUNTER) {
			Timer timer = new Timer(true);
			timer.scheduleAtFixedRate(new KeyTask(this, action), Config.keyRepeatDelay, 1000 / Config.keyRepeatSpeed);
			this.timers.put(action, timer);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (this.keyBinding.isRelevant(e)) {
			this.pressedKeys.remove(e.getKeyCode());
			
			Action action = this.keyBinding.getAction(e);
			if (this.timers.containsKey(action)) {
				this.timers.get(action).cancel();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void focusGained(FocusEvent arg0) {
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		this.pressedKeys.clear();
		for (Timer timer : this.timers.values()) {
			timer.cancel();
		}
	}

}