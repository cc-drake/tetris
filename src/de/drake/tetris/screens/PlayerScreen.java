package de.drake.tetris.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.screens.util.ComponentFactory;
import de.drake.tetris.screens.util.PlayerList;
import de.drake.tetris.screens.util.PlayerOptionTable;
import de.drake.tetris.states.PlayerState;

public class PlayerScreen extends JScrollPane implements ListSelectionListener {
	
	private final PlayerList playerList;
	
	private final HashMap<Player, PlayerOptionTable> optionTables = new HashMap<Player, PlayerOptionTable>();
	
	private final JPanel topPanel;
	
	private final GridBagConstraints optionsConstraints;
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public PlayerScreen(final ActionListener listener) {
		
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			this.topPanel = new JPanel();
			this.topPanel.setBackground(Config.bgColor);
			this.topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 5, 0, 5);
			c.fill = GridBagConstraints.BOTH;
			contentPanel.add(this.topPanel, BorderLayout.CENTER);
				
				//In der obersten Zeile Struts einfügen, die eine einheitliche Mindestbreite der Spalten sicherstellen
				c.gridy = 0;
				
				c.gridx = 0;
				this.topPanel.add(Box.createHorizontalStrut(350), c);
				c.gridx = 1;
				this.topPanel.add(Box.createHorizontalStrut(350), c);
				
				//Spielerliste
				c.gridx = 0;
				
				c.gridy = 1;
				this.topPanel.add(ComponentFactory.createLabel("Spieler", Color.green), c);
				
				c.gridy = 2;
				
				this.playerList = new PlayerList(this);
				this.topPanel.add(this.playerList, c);
				
				c.gridy = 3;
				
				this.topPanel.add(ComponentFactory.createButton(
						"Hinzufügen", PlayerState.addPlayer, listener), c);
				
				c.gridy = 4;
				
				this.topPanel.add(ComponentFactory.createButton(
						"Entfernen", PlayerState.removePlayer, listener), c);
				
				c.gridy = 5;
				
				this.topPanel.add(Box.createVerticalGlue(), c);
				
				//Einstellungen
				c.gridx = 1;
				
				c.gridy = 1;
				this.topPanel.add(ComponentFactory.createLabel("Einstellungen", Color.red), c);
				
				c.gridy = 2;
				c.gridheight = GridBagConstraints.REMAINDER;
				// Die PlayerOptionTables werden in der Methode valueChanged eingefügt
				this.optionsConstraints = (GridBagConstraints) c.clone();
				this.valueChanged(null);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Config.bgColor);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(PlayerState.start, listener));
				bottomPanel.add(ComponentFactory.createButton(PlayerState.back, listener));
	}

	public void addPlayer() {
		this.playerList.addPlayer();
	}

	public void removePlayer() {
		this.playerList.removePlayer();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		for (Player player : new HashSet<Player>(this.optionTables.keySet())) {
			if (!this.playerList.getPlayers().contains(player)) {
				this.topPanel.remove(this.optionTables.get(player));
				this.optionTables.remove(player);
			}
		}
		
		Player selectedPlayer = this.playerList.getSelectedPlayer();
		if (selectedPlayer == null)
			return;
		PlayerOptionTable table = this.optionTables.get(selectedPlayer);
		if (table == null) {
			table = new PlayerOptionTable(selectedPlayer);
				
			this.topPanel.add(table, this.optionsConstraints);
			this.optionTables.put(selectedPlayer, table);
		}
		this.topPanel.setComponentZOrder(table, 0);
	}

	public void initializePlayers() {
		Player.players = this.playerList.getPlayers();
		for (PlayerOptionTable table : this.optionTables.values()) {
			table.initializePlayer();
		}
	}
	
}