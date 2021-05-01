package de.drake.tetris.view.screens;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.view.input.InputDevice;
import de.drake.tetris.view.screens.util.PlayerPanel;

public class GameScreen extends JPanel {
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public GameScreen(final Game game) {
		this.setBackground(Color.white);
		InputDevice.registerInputDevices(this);
		
		switch (game.getPlayers().size()) {
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
		
		for (Player player : game.getPlayers()) {
			this.add(new PlayerPanel(game, player));
		}
		
	}
			
}