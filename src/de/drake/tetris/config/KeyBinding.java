package de.drake.tetris.config;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import de.drake.tetris.util.Action;

public class KeyBinding {
	
	public static KeyBinding createKeyBindingP1() {
		KeyBinding result = new KeyBinding();
		result.key2action.put(KeyEvent.VK_ESCAPE, Action.QUIT);
		result.key2action.put(KeyEvent.VK_ENTER, Action.PAUSE);
		result.key2action.put(KeyEvent.VK_A, Action.LINKS);
		result.key2action.put(KeyEvent.VK_D, Action.RECHTS);
		result.key2action.put(KeyEvent.VK_S, Action.RUNTER);
		result.key2action.put(KeyEvent.VK_SPACE, Action.GANZ_RUNTER);
		result.key2action.put(KeyEvent.VK_NUMPAD5, Action.DREHUNG_UHRZEIGERSINN);
		result.key2action.put(KeyEvent.VK_NUMPAD4, Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		return result;
	}
	
	public static KeyBinding createKeyBindingP1P1() {
		KeyBinding result = new KeyBinding();
		result.key2action.put(KeyEvent.VK_ESCAPE, Action.QUIT);
		result.key2action.put(KeyEvent.VK_ENTER, Action.PAUSE);
		result.key2action.put(KeyEvent.VK_A, Action.LINKS);
		result.key2action.put(KeyEvent.VK_D, Action.RECHTS);
		result.key2action.put(KeyEvent.VK_S, Action.RUNTER);
		result.key2action.put(KeyEvent.VK_SPACE, Action.GANZ_RUNTER);
		result.key2action.put(KeyEvent.VK_F, Action.DREHUNG_UHRZEIGERSINN);
		result.key2action.put(KeyEvent.VK_G, Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		return result;
	}
	
	public static KeyBinding createKeyBindingP1P2() {
		KeyBinding result = new KeyBinding();
		result.key2action.put(KeyEvent.VK_SUBTRACT, Action.QUIT);
		result.key2action.put(KeyEvent.VK_ADD, Action.PAUSE);
		result.key2action.put(KeyEvent.VK_LEFT, Action.LINKS);
		result.key2action.put(KeyEvent.VK_RIGHT, Action.RECHTS);
		result.key2action.put(KeyEvent.VK_DOWN, Action.RUNTER);
		result.key2action.put(KeyEvent.VK_NUMPAD0, Action.GANZ_RUNTER);
		result.key2action.put(KeyEvent.VK_NUMPAD5, Action.DREHUNG_UHRZEIGERSINN);
		result.key2action.put(KeyEvent.VK_NUMPAD4, Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		return result;
	}
	
	public static KeyBinding createKeyBindingGamePad() {
		KeyBinding result = new KeyBinding();
		result.key2action.put(KeyEvent.VK_6, Action.QUIT);
		result.key2action.put(KeyEvent.VK_7, Action.PAUSE);
		result.key2action.put(KeyEvent.VK_LEFT, Action.LINKS);
		result.key2action.put(KeyEvent.VK_RIGHT, Action.RECHTS);
		result.key2action.put(KeyEvent.VK_DOWN, Action.RUNTER);
		result.key2action.put(KeyEvent.VK_4, Action.GANZ_RUNTER);
		result.key2action.put(KeyEvent.VK_5, Action.GANZ_RUNTER);
		result.key2action.put(KeyEvent.VK_0, Action.DREHUNG_UHRZEIGERSINN);
		result.key2action.put(KeyEvent.VK_1, Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		return result;
	}
	
	private HashMap<Integer, Action> key2action = new HashMap<Integer, Action>();
	
	public Action getAction(final KeyEvent e) {
		return this.key2action.get(e.getKeyCode());
	}
	
	public boolean isRelevant(final KeyEvent e) {
		return this.key2action.containsKey(e.getKeyCode());
	}
}
