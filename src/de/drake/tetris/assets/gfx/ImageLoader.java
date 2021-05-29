package de.drake.tetris.assets.gfx;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(final String path) throws Exception {
		return ImageIO.read(ImageLoader.class.getResource(path));
	}
	
}