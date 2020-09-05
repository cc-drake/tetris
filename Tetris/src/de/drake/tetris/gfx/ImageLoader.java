package de.drake.tetris.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

class ImageLoader {
	
	static BufferedImage loadImage(final String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			throw new Error(e);
		}
	}
	
}