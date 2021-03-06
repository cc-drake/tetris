package de.drake.tetris.view.screens.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;

public class LogoPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public LogoPanel() {
			this.setBackground(Config.COLOR_BACKGROUND);
	}
	
	/**
	 * Zeichnet den Screen.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage logo = Asset.IMAGE_LOGO;
		int logoWidth = this.getWidth() * 2 / 3;
		int logoHeight = logoWidth * logo.getHeight() / logo.getWidth();
		int positionX = this.getWidth() / 2 - logoWidth / 2;
		int positionY = this.getHeight() / 2 - logoHeight / 2;

		g.drawImage(logo, positionX, positionY, logoWidth, logoHeight, null);
		g.dispose();
	}
}
