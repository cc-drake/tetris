package de.drake.tetris.screens;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;

import de.drake.tetris.states.State;

/**
 * Das Display, in dem die GameStates angezeigt werden.
 */
public class Display extends JFrame {

	/**
	 * Die serialVersionUID für das Display
	 */
	private static final long serialVersionUID = 1L;
	
	private Component currentScreen;
	
	/**
	 * Konstruktor zum Erzeugen des Displays
	 */
	public Display() {
		super("Tetris");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 1));
		this.setVisible(true);
	}
	
	public void render() {
		if (this.currentScreen != State.getCurrentState().getScreen()) {
			if (this.currentScreen != null)
				this.remove(this.currentScreen);
			this.currentScreen = State.getCurrentState().getScreen();
			this.add(this.currentScreen);
			this.revalidate();
			this.currentScreen.requestFocusInWindow();
		}
		this.currentScreen.repaint();
	}
	
}