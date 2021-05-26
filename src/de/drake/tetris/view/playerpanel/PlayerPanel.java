package de.drake.tetris.view.playerpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.JPanel;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;

/**
 * Ein Panel, welches alle Informationen zu einem Spieler anzeigt.
 */
public class PlayerPanel extends JPanel implements ComponentListener {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Color BGCOLOR = Color.getHSBColor(0f, 0f, 0.1f);
	
	public static final Color TEXTCOLOR = Color.lightGray;
	
	private final SpielfeldPanel spielfeld;
	
	private final PreviewPanel preview;
	
	private final InfoPanel info;
	
	private final Component rigidArea;
	
	public PlayerPanel(final Game game, final Player player) {
		this.spielfeld = new SpielfeldPanel(game, player);
		this.preview = new PreviewPanel(player);
		this.info = new InfoPanel(player);
		this.rigidArea = Box.createRigidArea(new Dimension(0, 0));
		
		this.setBackground(Color.black);
		this.setFocusable(false);
		this.addComponentListener(this);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
		c.weightx = 1.;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(this.spielfeld, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.;
		this.add(this.rigidArea, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(this.preview, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.;
		c.fill = GridBagConstraints.BOTH;
		this.add(this.info, c);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		int block_height, block_width;
		int previewsize = this.preview.getPreviewfelder();
		
		//Höhe und Breite eines Tetrisfeldes auf Basis des zur Verfügung stehenden Platzes ermitteln
		block_height = (this.getHeight() - 2) / Config.hoehe;
		block_width = (this.getWidth() - 4) / (Config.breite + previewsize + 1);
		double seitenverhaeltnis = ((double) Asset.SPRITE_WIDTH) / ((double) Asset.SPRITE_HEIGHT);
		if (((double) block_width) / block_height < seitenverhaeltnis) {
			block_height = (int) (block_width / seitenverhaeltnis);
		} else {
			block_width = (int) (block_height * seitenverhaeltnis);
		}
		
		//PreferredSize der jeweiligen Komponenten einstellen
		this.spielfeld.setPreferredSize(new Dimension(
				2 + Config.breite * block_width, 2 + Config.hoehe * block_height));
		this.spielfeld.setBlockDimension(block_width, block_height);
		this.preview.setPreferredSize(new Dimension(
				2 + previewsize * block_width, 2 + previewsize * block_height));
		this.preview.setBlockDimension(block_width, block_height);
		this.rigidArea.setPreferredSize(new Dimension(
				block_width, block_height));
		
		//Die Änderungen übernehmen
		this.revalidate();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
	
}