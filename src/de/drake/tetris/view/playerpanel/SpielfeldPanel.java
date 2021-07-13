package de.drake.tetris.view.playerpanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import javax.swing.JPanel;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.animations.Animation;
import de.drake.tetris.model.spielfeld.BlockPaintObject;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.util.Position;
import de.drake.tetris.view.playerpanel.eraser.Eraser;
import de.drake.tetris.view.playerpanel.eraser.EraserFactory;
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
		this.paintBlockLayer(g);
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
	
	private void paintBlockLayer(final Graphics g) {
		//Animations zwischenspeichern, damit zwischenzeitlich im Model entfernte Animations nicht zu Anzeigebugs führen
		HashSet<Animation> animations = this.player.getAnimations();
		BufferedImage blocklayer = this.paintBlocks();
		Eraser eraser;
		for (Animation animation : animations) {
			switch (animation.getType()) {
			case CLEAR_ROW:
				eraser = EraserFactory.createClearRowEraser(animation, this.block_width, this.block_height);
				eraser.paint(blocklayer);
				break;
			case DESTROY_ROW:
				eraser = EraserFactory.createDestroyRowEraser(animation, this.block_width, this.block_height);
				eraser.paint(blocklayer);
				break;
			case DESTROY_COLUMN:
				eraser = EraserFactory.createDestroyColumnEraser(animation, this.block_width, this.block_height);
				eraser.paint(blocklayer);
				break;
			case DESTROY_SQUARE:
				eraser = EraserFactory.createDestroySquareEraser(animation, this.block_width, this.block_height);
				eraser.paint(blocklayer);
				break;
			}
		}
		
		g.drawImage(blocklayer, 1, 1, null);
	}

	private BufferedImage paintBlocks() {
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		for (BlockPaintObject block : this.player.getSpielfeld().getBlocks()) {
			g.drawImage(block.getTexture().getSpielfeldTexture(),
					(int) (block.getDoubleX() * this.block_width),
					(int) (block.getDoubleY() * this.block_height),
					this.block_width, this.block_height, null);
		}
		g.dispose();
		return image;
	}
	
	private void paintStone(final Graphics g) {
		Stone stone = this.player.getStone();
		if (stone == null)
			return;
		for (Position position : stone.getPositionen()) {
			int column = position.getX();
			int row = position.getY();
			if (row < 0)
				continue;
			g.drawImage(stone.getTexture().getStoneTexture(),
					1 + column * this.block_width,
					1 + row * this.block_height,
					this.block_width, this.block_height, null);
		}
	}

	private void paintStatus(final Graphics g) {
		switch (this.game.getStatus()) {
		case PREPARED:
			this.printCenteredString("READY?", g);
			break;
		case QUIT:
			this.printCenteredString("BEENDEN?", g);
			break;
		case PAUSED:
			this.printCenteredString("PAUSE", g);
			break;
		default:
			switch (this.player.getStatus()) {
			case WINNER:
				this.printCenteredString("WINNER", g);
				break;
			case LOSER:
				this.printCenteredString("LOSER", g);
				break;
			default:
				break;
			}
		}
	}
	
	private void printCenteredString(final String text, final Graphics g) {
		g.setColor(GameScreen.FRONTCOLOR);
		int fontsize = (int) Math.min(0.16 * this.getWidth(), .5 * this.getHeight());
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		FontMetrics metric = g.getFontMetrics();
		Rectangle2D bounds = metric.getStringBounds(text, g);
		int x = (int) ((this.getWidth() - bounds.getWidth()) / 2.);
		int y = (int) ((this.getHeight() - bounds.getHeight()) / 2.) + metric.getAscent();
		g.drawString(text, x, y);
	}

	private void paintBorderline(final Graphics g) {
		g.setColor(GameScreen.BGCOLOR2);
		g.drawRect(0, 0, Config.breite * this.block_width + 1, Config.hoehe * this.block_height + 1);
	}
	
}