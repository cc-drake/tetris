package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.drake.tetris.gfx.Assets;

public class LogoPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public LogoPanel() {
			this.setBackground(Color.black);
	}
	
	/**
	 * Zeichnet den Screen.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage logo = Assets.logo;
		int logoWidth = this.getWidth() * 2 / 3;
		int logoHeight = logoWidth * logo.getHeight() / logo.getWidth();
		int positionX = this.getWidth() / 2 - logoWidth / 2;
		int positionY = this.getHeight() / 2 - logoHeight / 2;

		g.drawImage(logo, positionX, positionY, logoWidth, logoHeight, null);
		g.dispose();
	}
}
