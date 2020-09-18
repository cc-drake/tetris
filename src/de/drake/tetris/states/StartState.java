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
	StartState() {
		this.screen = new StartScreen(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == StartState.newGame)
			State.setCurrentState(State.modeState);
		if (e.getActionCommand() == StartState.endGame)
			System.exit(0);
	}

	@Override
	public void tick() {
	}

	@Override
	public JComponent getScreen() {
		return this.screen;
	}
		
}