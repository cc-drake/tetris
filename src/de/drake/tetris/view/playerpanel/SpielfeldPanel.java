package de.drake.tetris.view.playerpanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.spielfeld.BlockPaintObject;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.util.Position;
import de.drake.tetris.view.screens.GameScreen;

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
		this.setFocusable(false);
	}
	
	void setBlockSize(final Dimension blockSize) {
		this.block_width = blockSize.width;
		this.block_height = blockSize.height;
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
		for (int spalte = 0; spalte < Config.breite; spalte++) {
			for (int zeile = 0; zeile < Config.hoehe; zeile++) {
				if ((spalte + zeile) % 2 == 0) {
					g.setColor(GameScreen.BGCOLOR2);
				} else {
					g.setColor(GameScreen.BGCOLOR);
				}
				g.fillRect(
						1 + spalte * this.block_width,
						1 + zeile * this.block_height,
						this.block_width, this.block_height);
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
		g.setColor(GameScreen.FRONTCOLOR);
		int fontsize = (int) (0.16 * this.getWidth());
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		int height = (int) Math.max(.5 * this.getHeight(), .8 * fontsize);
		
		switch (this.game.getStatus()) {
		case PREPARED:
			g.drawString("READY?", (int) (this.getWidth() * .18), height);
			break;
		case QUIT:
			g.drawString("BEENDEN?", (int) (this.getWidth() * .08), height);
			break;
		case PAUSED:
			g.drawString("PAUSE", (int) (this.getWidth() * .24), height);
			break;
		default:
			switch (this.player.getStatus()) {
			case WINNER:
				g.drawString("WINNER", (int) (this.getWidth() * .16), height);
				break;
			case LOSER:
				g.drawString("LOSER", (int) (this.getWidth() * .23), height);
				break;
			default:
				break;
			}
		}
	}

	private void paintBorderline(final Graphics g) {
		g.setColor(GameScreen.BGCOLOR2);
		g.drawRect(0, 0, Config.breite * this.block_width + 1, Config.hoehe * this.block_height + 1);
	}
	
}