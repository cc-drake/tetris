package de.drake.tetris.view;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;

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
		this.setVisible(true);
	}

	public synchronized void setScreen(final JComponent newScreen) {
		if (this.currentScreen != newScreen) {
			if (this.currentScreen != null)
				this.remove(this.currentScreen);
			this.currentScreen = newScreen;
			this.add(newScreen);
			this.revalidate();
			newScreen.requestFocusInWindow();
		}
		newScreen.repaint();
	}
	
}