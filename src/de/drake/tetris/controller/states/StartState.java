package de.drake.tetris.controller.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.view.screens.StartScreen;

/**
 * Der StartState besteht aus dem Startbildschirm.
 */
public class StartState extends State implements ActionListener {
	
	public final static String NEW_GAME = "Neues Spiel";
	public final static String QUIT_GAME = "Beenden";
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final StartScreen screen = new StartScreen(this);
	
	@Override
	public void tick() {
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case StartState.NEW_GAME:
			StateManager.setCurrentState(new ModeState());
			return;
		case StartState.QUIT_GAME:
			System.exit(0);
			return;
		}
	}
}