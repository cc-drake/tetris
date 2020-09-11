package de.drake.tetris.screens;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import de.drake.tetris.model.Spieler;
import de.drake.tetris.screens.comp.PlayerPanel;
import de.drake.tetris.states.GameState;

public class GameScreen extends JPanel {
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public GameScreen() {
		super.setBackground(Color.white);
	}
	
	public void addPlayer(final GameState gameState, final Spieler spieler) {
		this.add(new PlayerPanel(gameState, spieler));
		switch (this.getComponentCount()) {
		case 1:
			this.setLayout(new GridLayout(1, 1, 5, 5));
			break;
		case 2:
			this.setLayout(new GridLayout(1, 2, 5, 5));
			break;
		case 3:
		case 4:
			this.setLayout(new GridLayout(2, 2, 5, 5));
			break;
		default:
			this.setLayout(new GridLayout(2, 3, 5, 5));
		}
	}
			
}
