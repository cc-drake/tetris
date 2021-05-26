package de.drake.tetris.view.playerpanel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.drake.tetris.config.GameMode;
import de.drake.tetris.model.Player;
import de.drake.tetris.view.screens.GameScreen;

/**
 * Ein Panel, welches Textinformationen zu einem Spieler anzeigt.
 */
class InfoPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private final Player player;
	
	InfoPanel(final Player player) {
		this.player = player;
		this.setBackground(GameScreen.BGCOLOR);
		this.setFocusable(false);
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		g.setColor(GameScreen.FRONTCOLOR);
		int fontsize = Math.min(this.getWidth() / 8, this.getHeight() / 8);
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		FontMetrics metric = g.getFontMetrics();
		
		ArrayList<String> text = this.getInfoText();
		for (int zeile = 0; zeile < text.size(); zeile++) {
			g.drawString(text.get(zeile), 0,
					metric.getAscent() + (int)(zeile * metric.getHeight() * 1.1));
		}
		
		g.dispose();
	}
	
	private ArrayList<String> getInfoText() {
		ArrayList<String> result = new ArrayList<String>();
		
		String name = this.player.getName();
		result.add(name);
		
		String time = "Zeit: ";
		if (GameMode.getTimeLimit() == 0) {
			time += InfoPanel.getTime(this.player.getVergangeneZeitSec());
		} else {
			time += InfoPanel.getTime(this.player.getVerbleibendeZeitSec());
		}
		result.add(time);
		
		String stones = "Steine: " + this.player.getSpawnedStones();
		result.add(stones);
		
		String rows = "Reihen: ";
		if (GameMode.is(GameMode.RACE)) {
			rows += this.player.getRemainingRaceRows();
		} else if (GameMode.is(GameMode.CHEESE)){
			rows += this.player.getRemainingCheeseRows();
		} else {
			rows += this.player.getClearedRows();
		}
		result.add(rows);
		
		if (GameMode.is(GameMode.COMBAT)) {
			String pending = "Wartend: " + this.player.getPendingRows();
			result.add(pending);
		}
		
		return result;
	}

	private static String getTime(int seconds) {
		int hours = seconds / 60 / 60;
		int minutes = seconds / 60 % 60;
		seconds = seconds % 60;
		
		String result = "";
		if (hours > 0) {
			result += hours + ":";
		}
		if (minutes < 10) {
			result += "0" + minutes + ":";
		} else {
			result += minutes + ":";
		}
		if (seconds < 10) {
			result += "0" + seconds;
		} else {
			result += seconds;
		}
		return result;
	}
	
}