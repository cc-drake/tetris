package de.drake.tetris.input.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.drake.tetris.config.Config;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.util.Action;

public class InputManager implements KeyListener {
	
	/**
	 * Speichert die aktuell gedrückten Tasten. Wird benötigt, um das windowseigene 
	 * Key-Repeat bei gedrückter Taste zu unterdrücken.
	 */
	private final HashSet<Key> pressedKeys = new HashSet<Key>();
	
	/**
	 * Die Zuordnung der Keys zu den Aktionen ("Tastenbelegung").
	 */
	private final HashMap<Key, Action> key2action;
	
	/**
	 * FIFO-Speicher zum Puffern von Tasteneingaben.
	 */
	private final ConcurrentLinkedQueue<Action> actionQueue = new ConcurrentLinkedQueue<Action>();
	
	/**
	 * Timer, die beim Gedrückthalten einer Taste nach einer bestimmten Zeit die zugehörige Aktion
	 * automatisch erneut auslösen.
	 */
	private final HashMap<Action, Timer> timers = new HashMap<Action, Timer>();
	
	public InputManager(final InputDevice device, final HashMap<Key, Action> tastenbelegung) {
		this.key2action = tastenbelegung;
		device.addKeyListener(this);
	}
	
	void processKeyTask(final Action action) {
		this.actionQueue.add(action);
	}
	
	public Action getNextAction() {
		return this.actionQueue.poll();
	}
	
	@Override
	public void keyPressed(Key key) {
		if (this.pressedKeys.contains(key) || !this.key2action.containsKey(key)) {
			return;
		}
		this.pressedKeys.add(key);
			
		Action action = this.key2action.get(key);
		this.actionQueue.add(action);
		if (action == Action.LINKS || action == Action.RECHTS || action == Action.RUNTER) {
			Timer timer = new Timer(true);
			timer.scheduleAtFixedRate(new KeyTask(this, action), Config.keyRepeatDelay, 1000 / Config.keyRepeatSpeed);
			this.timers.put(action, timer);
		}
	}

	@Override
	public void keyReleased(Key key) {
		if (!this.pressedKeys.contains(key) || !this.key2action.containsKey(key)) {
			return;
		}
		this.pressedKeys.remove(key);
		
		Timer timer = this.timers.get(this.key2action.get(key));
		if (timer != null) {
			timer.cancel();
		}
	}

}