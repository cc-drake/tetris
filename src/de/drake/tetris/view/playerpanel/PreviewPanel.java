package de.drake.tetris.view.playerpanel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.util.Position;
import de.drake.tetris.view.screens.GameScreen;

/**
 * Ein Panel, welches das Previewfeld eines Spielers anzeigt.
 */
class PreviewPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private final Player player;
	
	private int block_width, block_height;
	
	PreviewPanel(final Player player) {
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
		this.paintStone(g);
		this.paintBorderline(g);
		g.dispose();
	}
	
	private void paintBackground(final Graphics g) {
		for (int spalte = 0; spalte < Config.getPreviewSize(); spalte++) {
			for (int zeile = 0; zeile < Config.getPreviewSize(); zeile++) {
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
	
	private void paintStone(final Graphics g) {
		int zeile, spalte;
		Stone stone = this.player.getNextStein();
		for (Position position : stone.getDefaultRelativePositions()) {
			zeile = Config.getPreviewSize() / 2 + position.getY() - 1;
			spalte = Config.getPreviewSize() / 2 + position.getX();
			g.drawImage(stone.getTexture().getStoneTexture(),
					1 + spalte * this.block_width,
					1 + zeile * this.block_height,
					this.block_width, this.block_height, null);
		}
	}
	
	private void paintBorderline(final Graphics g) {
		g.setColor(GameScreen.BGCOLOR2);
		g.drawRect(0, 0, Config.getPreviewSize() * this.block_width + 1,
				Config.getPreviewSize() * this.block_height + 1);
	}
	
}