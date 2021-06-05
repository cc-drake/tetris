package de.drake.tetris.assets.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import de.drake.tetris.model.util.AnimationType;

public class BlockTexture {
	
	private final BufferedImage stoneImage;
	private final BufferedImage fieldImage;
	private final HashMap<AnimationType, ArrayList<BufferedImage>> animationTextures
			= new HashMap<AnimationType, ArrayList<BufferedImage>>();
	
	public BlockTexture(final BufferedImage stoneImage,
			final BufferedImage fieldImage) throws Exception {
		this.stoneImage = stoneImage;
		this.fieldImage = fieldImage;
		this.animationTextures.put(AnimationType.LEFT_TO_RIGHT, this.getLeftToRightFrames(fieldImage));
		this.animationTextures.put(AnimationType.RIGHT_TO_LEFT, this.getRightToLeftFrames(fieldImage));
		this.animationTextures.put(AnimationType.CENTER_TO_BORDERS, this.getCenterToBordersFrames(fieldImage));
//		ImageTools.writeImages(this.animationTextures.get(AnimationType.LEFT_TO_RIGHT), this.hashCode() + "l2r.png");
//		ImageTools.writeImages(this.animationTextures.get(AnimationType.RIGHT_TO_LEFT), this.hashCode() + "r2l.png");
//		ImageTools.writeImages(this.animationTextures.get(AnimationType.CENTER_TO_BORDERS), this.hashCode() + "c2b.png");
	}
	
	public BlockTexture(final BufferedImage image) throws Exception {
		this(image, image);
	}

	private ArrayList<BufferedImage> getLeftToRightFrames(final BufferedImage block) throws Exception {
		ArrayList<BufferedImage> result = new ArrayList<BufferedImage>();
		BufferedImage eraser = BlockTexture.getEraser();
		BufferedImage frame;
		for (int x = - eraser.getWidth() / 2; x <= block.getWidth() + eraser.getWidth() / 2; x++) {
			frame = ImageTools.clone(block);
			ImageTools.removeX(frame, 0, x);
			ImageTools.addOverlay(frame, eraser, x - eraser.getWidth() / 2, 0, eraser.getWidth(), frame.getHeight());
			result.add(frame);
		}
		return result;
	}
	
	private ArrayList<BufferedImage> getRightToLeftFrames(final BufferedImage block) throws Exception {
		ArrayList<BufferedImage> result = new ArrayList<BufferedImage>();
		BufferedImage eraser = BlockTexture.getEraser();
		BufferedImage frame;
		for (int x = block.getWidth() - 1 + eraser.getWidth() / 2; x >= -1 - eraser.getWidth() / 2; x--) {
			frame = ImageTools.clone(block);
			ImageTools.removeX(frame, x, frame.getWidth());
			ImageTools.addOverlay(frame, eraser, x - eraser.getWidth() / 2, 0, eraser.getWidth(), frame.getHeight());
			result.add(frame);
		}
		return result;
	}
	
	private ArrayList<BufferedImage> getCenterToBordersFrames(final BufferedImage block) throws Exception {
		ArrayList<BufferedImage> result = new ArrayList<BufferedImage>();
		BufferedImage leftPart = block.getSubimage(0, 0, block.getWidth() / 2 - 1, block.getHeight());
		BufferedImage rightPart = block.getSubimage(block.getWidth() / 2, 0, block.getWidth() / 2, block.getHeight());
		ArrayList<BufferedImage> leftFrames = this.getRightToLeftFrames(leftPart);
		ArrayList<BufferedImage> rightFrames = this.getLeftToRightFrames(rightPart);
		for (int frame = 0; frame < leftFrames.size(); frame++) {
			result.add(ImageTools.flowImages(leftFrames.get(frame), rightFrames.get(frame)));
		}
		return result;
	}

	public BufferedImage getStoneTexture() {
		return this.stoneImage;
	}
	
	public BufferedImage getSpielfeldTexture() {
		return this.fieldImage;
	}
	
	public BufferedImage getAnimationTexture(final AnimationType type, final int frame) {
		return this.animationTextures.get(type).get(frame);
	}
	
	public int getAnimationFrames(final AnimationType type) {
		return this.animationTextures.size();
	}
	
	private static BufferedImage getEraser() throws Exception {
		BufferedImage eraser = ImageTools.loadImage("/textures/rowEraser.png");
		if (eraser.getWidth() % 2 == 0) {
			throw new Exception("Breite der Eraser-Grafik muss ungerade sein!"); 
		}
		return eraser;
	}
	
}