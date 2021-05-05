package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;

/**
 * Ein klassischer Tetrisstein ohne weitere Funktionen.
 */
public class HorizontalBomb extends OneBlockStone {
	
	HorizontalBomb() {
		super(Asset.TEXTURE_HORIZONTALBOMB);
	}
	
	@Override
	public void detonate() {
		this.spielfeld.entferneReihe(this.mittelpunkt.getY());
		Asset.SOUND_BOOM.play();
	}
	
	@Override
	public Stone clone() {
		return new HorizontalBomb();
	}
}