package de.drake.tetris.assets.gfx;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.drake.tetris.assets.Asset;

public class ImageTools {
	
	public static BufferedImage loadImage(final String path) throws Exception {
		return ImageIO.read(Asset.class.getResource(path));
	}
	
	public static void writeImages(final ArrayList<BufferedImage> images, final String filename) throws Exception {
		if (images.size() == 0)
			return;
		BufferedImage output = images.get(0);
		for (int index = 1; index < images.size(); index++) {
			output = ImageTools.flowImages(output, images.get(index));
		}
		ImageIO.write(output, "png", new File(filename));
	}
	
	public static void writeImage(BufferedImage image, final String filename) throws Exception {
		ImageIO.write(image, "png", new File(filename));
	}
	
	public static BufferedImage clone(final BufferedImage image) {
		BufferedImage clone = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics2D g = clone.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return clone;
	}
	
	public static void removeX(final BufferedImage image, final int xMin, final int xMax) {
		Graphics2D g = image.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		g.fillRect(xMin, 0, xMax - xMin + 1, image.getHeight());
		g.dispose();
	}
	
	public static void addOverlay(final BufferedImage image, final BufferedImage overlay,
			final int x, final int y, final int width, final int height) {
		Graphics2D g = image.createGraphics();
		g.drawImage(overlay, x, y, width, height, null);
		g.dispose();
	}

	public static BufferedImage flowImages(final BufferedImage left, final BufferedImage right) {
		BufferedImage flow = new BufferedImage(left.getWidth() + right.getWidth(), left.getHeight(), left.getType());
		Graphics2D g = flow.createGraphics();
		g.drawImage(left, 0, 0, null);
		g.drawImage(right, left.getWidth(), 0, null);
		g.dispose();
		return flow;
	}
	
}