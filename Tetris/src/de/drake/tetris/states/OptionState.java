package de.drake.tetris.states;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Der PlayState verwaltet das aktive Tetris-Spiel.
 */
public class OptionState extends State {
	
	/**
	 * Das Panel, in dem gezeichnet wird.
	 */
	private final JPanel jPanel;
	
	/**
	 * Erstellt einen neuen OptionState.
	 */
	OptionState() {
		
		this.jPanel = new JPanel();
		this.jPanel.setBackground(Color.white);
		this.jPanel.setFocusable(true);
		this.jPanel.setLayout(new GridLayout(1, 1, 5, 5));
		
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public JPanel getScreen() {
		return this.jPanel;
	}
}