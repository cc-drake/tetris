package de.drake.tetris.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.drake.tetris.states.GameStateManager;

/**
 * Das Display, in dem die GameStates angezeigt werden.
 */
public class Display extends JFrame {

	/**
	 * Die serialVersionUID für das Display
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel currentPanel;
	
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
		this.setFocusable(false);
		this.setVisible(true);
	}
	
	public void render() {
		if (this.currentPanel != GameStateManager.getState().getJPanel()) {
			if (this.currentPanel != null)
				this.remove(this.currentPanel);
			this.currentPanel = GameStateManager.getState().getJPanel();
			this.add(this.currentPanel);
			this.revalidate();
			this.currentPanel.requestFocusInWindow();
		}
		this.currentPanel.repaint();
	}
	
}