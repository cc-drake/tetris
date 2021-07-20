package de.drake.tetris.assets;

import java.awt.image.BufferedImage;

import de.drake.tetris.assets.audio.SoundFile;
import de.drake.tetris.assets.audio.SoundPlayer;
import de.drake.tetris.assets.gfx.BlockTexture;
import de.drake.tetris.assets.gfx.ImageTools;
import de.drake.tetris.assets.gfx.SpriteSheet;

public class Asset {
	
	public static BufferedImage IMAGE_LOGO, ERASER_CLEAR;
	
	public static BlockTexture TEXTURE_YELLOW, TEXTURE_BLUE, TEXTURE_RED, TEXTURE_GREEN,
			TEXTURE_ORANGE, TEXTURE_SQUAREBOMB, TEXTURE_HORIZONTALBOMB, TEXTURE_VERTICALBOMB;
	
	public static SoundFile SOUND_ADD, SOUND_ADDFOUR, SOUND_BOOM, SOUND_DREH, SOUND_DROP,
			SOUND_EMPTY, SOUND_FALL, SOUND_ROW, SOUND_TETRIS;
	
	public final static int SPRITE_WIDTH = 60;
	
	public final static int SPRITE_HEIGHT = 40;
	
	public static void init() throws Exception {
		
		SpriteSheet blocks = new SpriteSheet(ImageTools.loadImage("/textures/blockSprites.png", false),
				Asset.SPRITE_WIDTH, Asset.SPRITE_HEIGHT);
		
		Asset.TEXTURE_YELLOW = new BlockTexture(blocks.getSprite(0, 0), blocks.getSprite(1, 0));
		Asset.TEXTURE_BLUE = new BlockTexture(blocks.getSprite(0, 1), blocks.getSprite(1, 1));
		Asset.TEXTURE_RED = new BlockTexture(blocks.getSprite(0, 2), blocks.getSprite(1, 2));
		Asset.TEXTURE_GREEN = new BlockTexture(blocks.getSprite(0, 3), blocks.getSprite(1, 3));
		Asset.TEXTURE_ORANGE = new BlockTexture(blocks.getSprite(0, 4), blocks.getSprite(1, 4));
		
		Asset.TEXTURE_SQUAREBOMB = new BlockTexture(blocks.getSprite(0, 6), blocks.getSprite(1, 6));
		Asset.TEXTURE_HORIZONTALBOMB = new BlockTexture(blocks.getSprite(0, 7), blocks.getSprite(1, 7));
		Asset.TEXTURE_VERTICALBOMB = new BlockTexture(blocks.getSprite(0, 8), blocks.getSprite(1, 8));
		
		Asset.IMAGE_LOGO = ImageTools.loadImage("/textures/logo.png", false);
		
		Asset.SOUND_ADD = new SoundFile("/sounds/add.wav");
		Asset.SOUND_ADDFOUR = new SoundFile("/sounds/addFour.wav");
		Asset.SOUND_BOOM = new SoundFile("/sounds/boom.wav");
		Asset.SOUND_DREH = new SoundFile("/sounds/dreh.wav");
		Asset.SOUND_DROP = new SoundFile("/sounds/drop.wav");
		Asset.SOUND_FALL = new SoundFile("/sounds/fall.wav");
		Asset.SOUND_ROW = new SoundFile("/sounds/row.wav");
		Asset.SOUND_TETRIS = new SoundFile("/sounds/tetris.wav");
		
		SoundPlayer.addSoundFile(Asset.SOUND_ADD, 5);
		SoundPlayer.addSoundFile(Asset.SOUND_ADDFOUR, 5);
		SoundPlayer.addSoundFile(Asset.SOUND_BOOM, 5);
		SoundPlayer.addSoundFile(Asset.SOUND_DREH, 5);
		SoundPlayer.addSoundFile(Asset.SOUND_DROP, 5);
		SoundPlayer.addSoundFile(Asset.SOUND_FALL, 5);
		SoundPlayer.addSoundFile(Asset.SOUND_ROW, 5);
		SoundPlayer.addSoundFile(Asset.SOUND_TETRIS, 5);
		SoundPlayer.start();
		
	}
	
}