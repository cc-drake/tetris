package de.drake.tetris.view.playerpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.spielfeld.BlockPaintObject;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.util.Position;

/**
 * Ein Panel, welches das Spielfeld eines Spielers anzeigt.
 */
class SpielfeldPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private final Game game;
	
	private final Player player;

	private int block_width, block_height;
	
	SpielfeldPanel(final Game game, final Player player) {
		this.game = game;
		this.player = player;
		this.setBackground(Color.black);
		this.setFocusable(false);
	}
	
	void setBlockDimension(int block_width, int block_height) {
		this.block_width = block_width;
		this.block_height = block_height;
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		this.paintBackground(g);
		this.paintBlocks(g);
		this.paintStone(g);
		this.paintStatus(g);
		this.paintBorderline(g);
		g.dispose();
	}
	
	private void paintBackground(final Graphics g) {
		g.setColor(PlayerPanel.BGCOLOR);
		for (int spalte = 0; spalte < Config.breite; spalte++) {
			for (int zeile = 0; zeile < Config.hoehe; zeile++) {
				if ((spalte + zeile) % 2 == 0) {
					g.fillRect(
							1 + spalte * this.block_width,
							1 + zeile * this.block_height,
							this.block_width, this.block_height);
				}
			}
		}
	}
	
	private void paintBlocks(final Graphics g) {
		for (BlockPaintObject block : this.player.getSpielfeld().getBlocks()) {
			if (block.getDoubleY() < 0)
				continue;
			g.drawImage(block.getTexture().getSpielfeldTexture(),
					1 + (int) (block.getDoubleX() * this.block_width),
					1 + (int) (block.getDoubleY() * this.block_height),
					this.block_width, this.block_height, null);
		}
	}
	
	private void paintStone(final Graphics g) {
		int zeile, spalte;
		Stone stone = this.player.getStone();
		if (stone == null)
			return;
		for (Position position : stone.getPositionen()) {
			zeile = position.getY();
			spalte = position.getX();
			if (zeile < 0)
				continue;
			g.drawImage(stone.getTexture().getStoneTexture(),
					1 + spalte * this.block_width,
					1 + zeile * this.block_height,
					this.block_width, this.block_height, null);
		}
	}

	private void paintStatus(final Graphics g) {
		g.setColor(PlayerPanel.TEXTCOLOR);
		int fontsize = 3 * this.block_height;
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		
		switch (this.game.getStatus()) {
		case PREPARED:
			g.drawString("READY?", 
					1 + this.block_width * (Config.breite / 2 - 4),
					1 + this.block_height * (Config.hoehe / 2));
			break;
		case QUIT:
			g.drawString("BEENDEN?", 
					1 + this.block_width * (Config.breite / 2 - 5),
					1 + this.block_height * (Config.hoehe / 2));
			break;
		case PAUSED:
			g.drawString("PAUSE", 
					1 + this.block_width * (Config.breite / 2 - 3),
					1 + this.block_height * (Config.hoehe / 2));
			break;
		default:
			switch (this.player.getStatus()) {
			case WINNER:
				g.drawString("WINNER", 
						1 + this.block_width * (Config.breite / 2 - 4),
						1 + this.block_height * (Config.hoehe / 2));
				break;
			case LOSER:
				g.drawString("LOSER", 
						1 + this.block_width * (Config.breite / 2 - 3),
						1 + this.block_height * (Config.hoehe / 2));
				break;
			default:
				break;
			}
		}
	}

	private void paintBorderline(final Graphics g) {
		g.setColor(PlayerPanel.BGCOLOR);
		g.drawRect(0, 0, Config.breite * this.block_width + 1, Config.hoehe * this.block_height + 1);
	}
	
}