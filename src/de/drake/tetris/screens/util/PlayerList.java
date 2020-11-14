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
	
	private final Vector<PlayerConfig> players = new Vector<PlayerConfig>();
	
	private final JList<PlayerConfig> list;
	
	public PlayerList(final PlayerScreen screen) {
		super();
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		super.setBackground(Config.COLOR_BACKGROUND);
		this.list = new JList<PlayerConfig>(this.players);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new PlayerCellRenderer());
		list.getSelectionModel().addListSelectionListener(screen);
		super.add(list);
	}

	public void addPlayer() {
		PlayerConfig player = new PlayerConfig("Spieler " + (this.players.size() + 1));
		this.players.add(player);
		this.list.updateUI();
		this.list.setSelectedValue(player, true);
	}

	public void removePlayer() {
		int index = this.list.getSelectedIndex();
		if (index == -1)
			return;
		this.players.remove(index);
		this.list.clearSelection();
		this.list.updateUI();
		if (index < this.players.size()) {
			this.list.setSelectedIndex(index);
		} else {
			this.list.setSelectedIndex(this.players.size() - 1);
		}
	}

	public Vector<PlayerConfig> getPlayers() {
		return this.players;
	}

	public PlayerConfig getSelectedPlayer() {
		return this.list.getSelectedValue();
	}
	
	//Aktualisiert die Anzeige der JList, wenn Spielernamen ge�ndert, verl�ngert oder verk�rzt werden.
	public void paintComponent(Graphics g) {
		this.list.updateUI();
		super.paintComponent(g);
	}

}