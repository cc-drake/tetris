package de.drake.tetris.view.gfx;

import java.awt.image.BufferedImage;

import de.drake.tetris.model.util.StoneType;

public class Assets {
	
	public static BufferedImage gelb_aktiv, gelb_inaktiv, blau_aktiv, blau_inaktiv,
			rot_aktiv, rot_inaktiv, gruen_aktiv, gruen_inaktiv, orange_aktiv, orange_inaktiv,
			bomb_square, bomb_horizontal, bomb_vertical,
			logo;
	
	public final static int SPRITE_BREITE = 60;
	
	public final static int SPRITE_HOEHE = 40;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"),
				Assets.SPRITE_BREITE, Assets.SPRITE_HOEHE);
		Assets.gelb_aktiv = sheet.getSprite(0, 0);
		Assets.gelb_inaktiv = sheet.getSprite(1, 0);
		Assets.blau_aktiv = sheet.getSprite(0, 1);
		Assets.blau_inaktiv = sheet.getSprite(1, 1);
		Assets.rot_aktiv = sheet.getSprite(0, 2);
		Assets.rot_inaktiv = sheet.getSprite(1, 2);
		Assets.gruen_aktiv = sheet.getSprite(0, 3);
		Assets.gruen_inaktiv = sheet.getSprite(1, 3);
		Assets.orange_aktiv = sheet.getSprite(0, 4);
		Assets.orange_inaktiv = sheet.getSprite(1, 4);
		Assets.bomb_square = sheet.getSprite(0, 5);
		Assets.bomb_horizontal = sheet.getSprite(0, 6);
		Assets.bomb_vertical = sheet.getSprite(1, 6);
		Assets.logo = ImageLoader.loadImage("/textures/logo.png");
	}
	
	public static BufferedImage getAsset(final StoneType type, final boolean isActive) {
		if (isActive) {
			switch (type) {
			case YELLOW:
				return Assets.gelb_aktiv;
			case BLUE:
				return Assets.blau_aktiv;
			case RED:
				return Assets.rot_aktiv;
			case GREEN:
				return Assets.gruen_aktiv;
			case CHEESE:
				return Assets.orange_aktiv;
			case BOMB_SQUARE:
				return Assets.bomb_square;
			case BOMB_HORIZONTAL:
				return Assets.bomb_horizontal;
			case BOMB_VERTICAL:
				return Assets.bomb_vertical;
			default:
			}
		} else {
			switch (type) {
			case YELLOW:
				return Assets.gelb_inaktiv;
			case BLUE:
				return Assets.blau_inaktiv;
			case RED:
				return Assets.rot_inaktiv;
			case GREEN:
				return Assets.gruen_inaktiv;
			case CHEESE:
				return Assets.orange_inaktiv;
			default:
			}
		}
		throw new Error ("Ungültiger Typ");
	}
	
}