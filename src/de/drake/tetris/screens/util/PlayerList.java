package de.drake.tetris.screens.util;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.screens.PlayerScreen;

public class PlayerList extends JPanel {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private final Vector<PlayerConfig> playerConfigs = new Vector<PlayerConfig>();
	
	private final JList<PlayerConfig> list = new JList<PlayerConfig>(this.playerConfigs);
	
	public PlayerList(final PlayerScreen screen) {
		super();
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		super.setBackground(Config.COLOR_BACKGROUND);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new PlayerCellRenderer());
		list.getSelectionModel().addListSelectionListener(screen);
		super.add(list);
	}

	public void addPlayer(final PlayerConfig player) {
		this.playerConfigs.add(player);
		this.list.updateUI();
		this.list.setSelectedValue(player, true);
	}

	public void removePlayer(final PlayerConfig player) {
		int index = this.list.getSelectedIndex();
		this.playerConfigs.remove(player);
		this.list.clearSelection();
		this.list.updateUI();
		if (index < this.playerConfigs.size()) {
			this.list.setSelectedIndex(index);
		} else {
			this.list.setSelectedIndex(this.playerConfigs.size() - 1);
		}
	}
	
	public void selectFirstPlayer() {
		if (this.playerConfigs.size() > 0) {
			this.list.setSelectedIndex(0);
		}
	}

	public Vector<PlayerConfig> getPlayerConfigs() {
		return this.playerConfigs;
	}

	public PlayerConfig getSelectedPlayer() {
		return this.list.getSelectedValue();
	}
	
	//Aktualisiert die Anzeige der JList, wenn Spielernamen geändert, verlängert oder verkürzt werden.
	public void paintComponent(Graphics g) {
		this.list.updateUI();
		super.paintComponent(g);
	}

}