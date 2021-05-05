package de.drake.tetris.model.stones;

import de.drake.tetris.assets.Asset;

/**
 * Ein klassischer Tetrisstein ohne weitere Funktionen.
 */
public class SquareBomb extends OneBlockStone {
	
	SquareBomb() {
		super(Asset.TEXTURE_SQUAREBOMB);
	}

	@Override
	public void detonate() {
		spielfeld.entferne3x3(this.mittelpunkt);
		Asset.SOUND_BOOM.play();
	}

	@Override
	public Stone clone() {
		return new SquareBomb();
	}
}