package de.drake.tetris.view.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.drake.tetris.input.InputDevice;
import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.view.playerpanel.PlayerPanel;

public class GameScreen extends JPanel implements ComponentListener {
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Color BGCOLOR = Color.black;
	
	public static final Color BGCOLOR2 = Color.getHSBColor(0f, 0f, 0.1f);
	
	public static final Color FRONTCOLOR = Color.lightGray;
	
	private final ArrayList<Integer[]> possibleGrids = new ArrayList<Integer[]>();

	public GameScreen(final Game game) {
		this.setBackground(GameScreen.FRONTCOLOR);
		InputDevice.registerInputDevices(this);
		this.addComponentListener(this);
		
		for (Player player : game.getPlayers()) {
			this.add(new PlayerPanel(game, player));
		}
		
		int players = game.getPlayers().size();
		for (int rows = 1; rows <= players; rows++) {
			int columns = (int) Math.ceil(players / ((double) rows));
			if ((rows - 1) * columns < players) {
				Integer[] grid = {rows, columns};
				this.possibleGrids.add(grid);
			}
		}
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Integer[] bestGrid = null;
		int largestWidth = 0;
		for (Integer[] grid : this.possibleGrids) {
			int width = (this.getWidth() - 5 * (grid[1] - 1)) / grid[1];
			int height = (this.getHeight() - 5 * (grid[0] - 1)) / grid[0];
			Dimension size = PlayerPanel.getPlayerPanelSize(width, height);
			if (size.width > largestWidth) {
				largestWidth = size.width;
				bestGrid = grid;
			}
		}
		
		this.setLayout(new GridLayout(bestGrid[0], bestGrid[1], 5, 5));
		this.revalidate();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
			
}