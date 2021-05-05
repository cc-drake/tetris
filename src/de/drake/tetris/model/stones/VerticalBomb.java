package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;

/**
 * Ein klassischer Tetrisstein ohne weitere Funktionen.
 */
public class VerticalBomb extends OneBlockStone {
	
	VerticalBomb() {
		super(Asset.TEXTURE_VERTICALBOMB);
	}

	@Override
	public void detonate() {
		this.spielfeld.entferneSpalte(this.mittelpunkt.getX());
		Asset.SOUND_BOOM.play();
	}

	@Override
	public Stone clone() {
		return new VerticalBomb();
	}
}