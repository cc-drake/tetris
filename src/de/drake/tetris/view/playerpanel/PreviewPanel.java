package de.drake.tetris.view.playerpanel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.util.Position;

/**
 * Ein Panel, welches das Previewfeld eines Spielers anzeigt.
 */
class PreviewPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Anzahl der Preview-Zeilen bzw. Spalten
	 */
	private final int previewfelder = Config.getMaxSteinSize() + 2;
	
	private final Player player;
	
	private int block_width, block_height;
	
	PreviewPanel(final Player player) {
		this.player = player;
		this.setBackground(Color.black);
		this.setFocusable(false);
	}
	
	void setBlockDimension(int block_width, int block_height) {
		this.block_width = block_width;
		this.block_height = block_height;
	}
	
	int getPreviewfelder() {
		return this.previewfelder;
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		this.paintBackground(g);
		this.paintStone(g);
		this.paintBorderline(g);
		g.dispose();
	}
	
	private void paintBackground(final Graphics g) {
		g.setColor(PlayerPanel.BGCOLOR);
		for (int spalte = 0; spalte < this.previewfelder; spalte++) {
			for (int zeile = 0; zeile < this.previewfelder; zeile++) {
				if ((spalte + zeile) % 2 == 0) {
					g.fillRect(
							1 + spalte * this.block_width,
							1 + zeile * this.block_height,
							this.block_width, this.block_height);
				}
			}
		}
	}
	
	private void paintStone(final Graphics g) {
		int zeile, spalte;
		Stone stone = this.player.getNextStein();
		for (Position position : stone.getDefaultRelativePositions()) {
			zeile = this.previewfelder / 2 + position.getY() - 1;
			spalte = this.previewfelder / 2 + position.getX();
			g.drawImage(stone.getTexture().getStoneTexture(),
					1 + spalte * this.block_width,
					1 + zeile * this.block_height,
					this.block_width, this.block_height, null);
		}
	}
	
	private void paintBorderline(final Graphics g) {
		g.setColor(PlayerPanel.BGCOLOR);
		g.drawRect(0, 0, this.previewfelder * this.block_width + 1,
				this.previewfelder * this.block_height + 1);
	}
	
}