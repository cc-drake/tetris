package de.drake.tetris.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.drake.tetris.screens.StartScreen;

/**
 * Der StartState besteht aus dem Startbildschirm.
 */
public class StartState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final StartScreen screen;
	
	public final static String newGame = "Neues Spiel";
	public final static String endGame = "Beenden";
	
	/**
	 * Erstellt einen neuen StartState.
	 */
	public StartState() {
		this.screen = new StartScreen(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case StartState.newGame:
			State.setCurrentState(new ModeState());
			return;
		case StartState.endGame:
			System.exit(0);
			return;
		}
	}

	@Override
	public void tick() {
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}
		
}