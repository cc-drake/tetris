package de.drake.tetris.gfx;

import java.awt.image.BufferedImage;

import de.drake.tetris.util.Color;

public class Assets {
	
	public static BufferedImage gelb_aktiv, gelb_inaktiv, blau_aktiv, blau_inaktiv,
			rot_aktiv, rot_inaktiv, gruen_aktiv, gruen_inaktiv, orange_aktiv, orange_inaktiv,
			logo;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"),
				60, 35);
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
		Assets.logo = ImageLoader.loadImage("/textures/logo.png");
	}
	
	public static BufferedImage getAsset(final Color color, final boolean isActive) {
		if (isActive) {
			switch (color) {
			case GELB: return Assets.gelb_aktiv;
			case BLAU: return Assets.blau_aktiv;
			case ROT: return Assets.rot_aktiv;
			case GRÜN: return Assets.gruen_aktiv;
			case ORANGE: return Assets.orange_aktiv;
			}
		} else {
			switch (color) {
			case GELB: return Assets.gelb_inaktiv;
			case BLAU: return Assets.blau_inaktiv;
			case ROT: return Assets.rot_inaktiv;
			case GRÜN: return Assets.gruen_inaktiv;
			case ORANGE: return Assets.orange_inaktiv;
			}
		}
		throw new Error ("Ungültige Farbe");
	}
	
}