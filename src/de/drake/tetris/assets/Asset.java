package de.drake.tetris.assets;

import java.awt.image.BufferedImage;

import de.drake.tetris.assets.audio.SoundClip;
import de.drake.tetris.assets.gfx.ImageLoader;
import de.drake.tetris.assets.gfx.SpriteSheet;
import de.drake.tetris.model.util.StoneType;

public class Asset {
	
	public static BufferedImage gelb_aktiv, gelb_inaktiv, blau_aktiv, blau_inaktiv,
			rot_aktiv, rot_inaktiv, gruen_aktiv, gruen_inaktiv, orange_aktiv, orange_inaktiv,
			bomb_square, bomb_horizontal, bomb_vertical,
			logo;
	
	public static SoundClip add, addFour, dreh, drop, fall, row, tetris;
	
	public final static int SPRITE_BREITE = 60;
	
	public final static int SPRITE_HOEHE = 40;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"),
				Asset.SPRITE_BREITE, Asset.SPRITE_HOEHE);
		Asset.gelb_aktiv = sheet.getSprite(0, 0);
		Asset.gelb_inaktiv = sheet.getSprite(1, 0);
		Asset.blau_aktiv = sheet.getSprite(0, 1);
		Asset.blau_inaktiv = sheet.getSprite(1, 1);
		Asset.rot_aktiv = sheet.getSprite(0, 2);
		Asset.rot_inaktiv = sheet.getSprite(1, 2);
		Asset.gruen_aktiv = sheet.getSprite(0, 3);
		Asset.gruen_inaktiv = sheet.getSprite(1, 3);
		Asset.orange_aktiv = sheet.getSprite(0, 4);
		Asset.orange_inaktiv = sheet.getSprite(1, 4);
		Asset.bomb_square = sheet.getSprite(0, 5);
		Asset.bomb_horizontal = sheet.getSprite(0, 6);
		Asset.bomb_vertical = sheet.getSprite(1, 6);
		Asset.logo = ImageLoader.loadImage("/textures/logo.png");
		
		Asset.add = new SoundClip("/sounds/add.wav");
		Asset.addFour = new SoundClip("/sounds/addFour.wav");
		Asset.dreh = new SoundClip("/sounds/dreh.wav");
		Asset.drop = new SoundClip("/sounds/drop.wav");
		Asset.fall = new SoundClip("/sounds/fall.wav");
		Asset.row = new SoundClip("/sounds/row.wav");
		Asset.tetris = new SoundClip("/sounds/tetris.wav");
	}
	
	public static BufferedImage getImage(final StoneType type, final boolean isActive) {
		if (isActive) {
			switch (type) {
			case YELLOW:
				return Asset.gelb_aktiv;
			case BLUE:
				return Asset.blau_aktiv;
			case RED:
				return Asset.rot_aktiv;
			case GREEN:
				return Asset.gruen_aktiv;
			case CHEESE:
				return Asset.orange_aktiv;
			case BOMB_SQUARE:
				return Asset.bomb_square;
			case BOMB_HORIZONTAL:
				return Asset.bomb_horizontal;
			case BOMB_VERTICAL:
				return Asset.bomb_vertical;
			default:
			}
		} else {
			switch (type) {
			case YELLOW:
				return Asset.gelb_inaktiv;
			case BLUE:
				return Asset.blau_inaktiv;
			case RED:
				return Asset.rot_inaktiv;
			case GREEN:
				return Asset.gruen_inaktiv;
			case CHEESE:
				return Asset.orange_inaktiv;
			default:
			}
		}
		throw new Error ("Ungültiger Typ");
	}
	
}