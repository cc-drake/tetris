package de.drake.tetris.assets;

import java.awt.image.BufferedImage;

import de.drake.tetris.assets.audio.SoundClip;
import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.assets.gfx.ImageTools;
import de.drake.tetris.assets.gfx.SpriteSheet;

public class Asset {
	
	public static BufferedImage IMAGE_LOGO;
	
	public static BlockTexture TEXTURE_YELLOW, TEXTURE_BLUE, TEXTURE_RED, TEXTURE_GREEN,
			TEXTURE_ORANGE, TEXTURE_SQUAREBOMB, TEXTURE_HORIZONTALBOMB, TEXTURE_VERTICALBOMB;
	
	public static SoundClip SOUND_ADD, SOUND_ADDFOUR, SOUND_BOOM, SOUND_DREH, SOUND_DROP,
			SOUND_EMPTY, SOUND_FALL, SOUND_ROW, SOUND_TETRIS;
	
	public final static int SPRITE_WIDTH = 60;
	
	public final static int SPRITE_HEIGHT = 40;
	
	public static void init() throws Exception {
		
		SpriteSheet blocks = new SpriteSheet(ImageTools.loadImage("/textures/blockSprites.png"),
				Asset.SPRITE_WIDTH, Asset.SPRITE_HEIGHT);
		
		Asset.TEXTURE_YELLOW = new BlockTexture(blocks.getSprite(0, 0), blocks.getSprite(1, 0));
		Asset.TEXTURE_BLUE = new BlockTexture(blocks.getSprite(0, 1), blocks.getSprite(1, 1));
		Asset.TEXTURE_RED = new BlockTexture(blocks.getSprite(0, 2), blocks.getSprite(1, 2));
		Asset.TEXTURE_GREEN = new BlockTexture(blocks.getSprite(0, 3), blocks.getSprite(1, 3));
		Asset.TEXTURE_ORANGE = new BlockTexture(blocks.getSprite(0, 4), blocks.getSprite(1, 4));
		
		Asset.TEXTURE_SQUAREBOMB = new BlockTexture(blocks.getSprite(0, 5));
		Asset.TEXTURE_HORIZONTALBOMB = new BlockTexture(blocks.getSprite(0, 6));
		Asset.TEXTURE_VERTICALBOMB = new BlockTexture(blocks.getSprite(1, 6));
		
		Asset.IMAGE_LOGO = ImageTools.loadImage("/textures/logo.png");
		
		Asset.SOUND_ADD = new SoundClip("/sounds/add.wav");
		Asset.SOUND_ADDFOUR = new SoundClip("/sounds/addFour.wav");
		Asset.SOUND_BOOM = new SoundClip("/sounds/boom.wav");
		Asset.SOUND_DREH = new SoundClip("/sounds/dreh.wav");
		Asset.SOUND_DROP = new SoundClip("/sounds/drop.wav");
		Asset.SOUND_EMPTY = new SoundClip("/sounds/empty.wav");
		Asset.SOUND_FALL = new SoundClip("/sounds/fall.wav");
		Asset.SOUND_ROW = new SoundClip("/sounds/row.wav");
		Asset.SOUND_TETRIS = new SoundClip("/sounds/tetris.wav");
		
	}
	
}