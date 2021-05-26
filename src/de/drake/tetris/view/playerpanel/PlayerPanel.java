package de.drake.tetris.view.playerpanel;

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
import de.drake.tetris.view.screens.GameScreen;

/**
 * Ein Panel, welches alle Informationen zu einem Spieler anzeigt.
 */
public class PlayerPanel extends JPanel implements ComponentListener {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private final SpielfeldPanel spielfeld;
	
	private final PreviewPanel preview;
	
	private final InfoPanel info;
	
	private final Component rigidArea;
	
	public PlayerPanel(final Game game, final Player player) {
		this.spielfeld = new SpielfeldPanel(game, player);
		this.preview = new PreviewPanel(player);
		this.info = new InfoPanel(player);
		this.rigidArea = Box.createRigidArea(new Dimension(0, 0));
		
		this.setBackground(GameScreen.BGCOLOR);
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
		Dimension blockSize = PlayerPanel.getBlockSize(this.getWidth(), this.getHeight());
		
		this.spielfeld.setBlockSize(blockSize);
		this.preview.setBlockSize(blockSize);
		
		this.spielfeld.setPreferredSize(PlayerPanel.getSpielfeldSize(blockSize));
		this.preview.setPreferredSize(PlayerPanel.getPreviewSize(blockSize));
		this.rigidArea.setPreferredSize(blockSize);
		
		this.revalidate();
	}
	
	private static Dimension getBlockSize(final int width, final int height) {
		int block_width = (width - 4) / (Config.breite + Config.getPreviewSize() + 1);
		int block_height = (height - 2) / Config.hoehe;
		double ratio = ((double) Asset.SPRITE_WIDTH) / ((double) Asset.SPRITE_HEIGHT);
		if (((double) block_width) / block_height < ratio) {
			block_height = (int) (block_width / ratio);
		} else {
			block_width = (int) (block_height * ratio);
		}
		return new Dimension(block_width, block_height);
	}
	
	private static Dimension getSpielfeldSize(final Dimension blockSize) {
		int spielfeld_width = 2 + Config.breite * blockSize.width;
		int spielfeld_height = 2 + Config.hoehe * blockSize.height;
		return new Dimension(spielfeld_width, spielfeld_height);
	}
	
	private static Dimension getPreviewSize(final Dimension blockSize) {
		int preview_width = 2 + Config.getPreviewSize() * blockSize.width;
		int preview_height = 2 + Config.getPreviewSize() * blockSize.height;
		return new Dimension(preview_width, preview_height);
	}
	
	public static Dimension getPlayerPanelSize(final int width, final int height) {
		Dimension blockSize = PlayerPanel.getBlockSize(width, height);
		Dimension spielfeldSize = PlayerPanel.getSpielfeldSize(blockSize);
		Dimension previewSize = PlayerPanel.getPreviewSize(blockSize);
		int panel_width = spielfeldSize.width + blockSize.width + previewSize.width;
		int panel_height = spielfeldSize.height;
		return new Dimension(panel_width, panel_height);
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