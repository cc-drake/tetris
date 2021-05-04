package de.drake.tetris.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.controller.states.PlayerState;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.view.screens.util.ComponentFactory;
import de.drake.tetris.view.screens.util.PlayerConfigTable;
import de.drake.tetris.view.screens.util.PlayerList;

public class PlayerScreen extends JScrollPane implements ListSelectionListener {
	
	private final PlayerList playerList;
	
	private final HashMap<PlayerConfig, PlayerConfigTable> playerConfigTables = new HashMap<PlayerConfig, PlayerConfigTable>();
	
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
			this.topPanel.setBackground(Config.COLOR_BACKGROUND);
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
						"Hinzufügen", PlayerState.ADD_PLAYER, listener), c);
				
				c.gridy = 4;
				
				this.topPanel.add(ComponentFactory.createButton(
						"Entfernen", PlayerState.REMOVE_PLAYER, listener), c);
				
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
			bottomPanel.setBackground(Config.COLOR_BACKGROUND);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(PlayerState.START, listener));
				bottomPanel.add(ComponentFactory.createButton(PlayerState.BACK, listener));
		
		if (PlayerConfig.playerConfigs == null || PlayerConfig.playerConfigs.size() == 0) {
			this.addPlayer();
		} else {
			for (PlayerConfig config : PlayerConfig.playerConfigs) {
				this.addPlayer(config);
			}
		}
		this.playerList.selectFirstPlayer();
	}

	public void addPlayer(final PlayerConfig playerConfig) {
		PlayerConfigTable table = new PlayerConfigTable(playerConfig);
		this.playerConfigTables.put(playerConfig, table);
		this.topPanel.add(table, this.optionsConstraints);
		this.playerList.addPlayer(playerConfig);
	}
	
	public void addPlayer() {
		this.addPlayer(new PlayerConfig("Spieler " + (this.playerConfigTables.size() + 1), InputDevice.allInputdevices.get(0)));
	}

	public void removeSelectedPlayer() {
		PlayerConfig selectedPlayer = this.playerList.getSelectedPlayer();
		if (selectedPlayer == null)
			return;
		this.playerList.removePlayer(selectedPlayer);
		this.topPanel.remove(this.playerConfigTables.get(selectedPlayer));
		this.playerConfigTables.remove(selectedPlayer);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		PlayerConfig selectedPlayer = this.playerList.getSelectedPlayer();
		if (selectedPlayer == null) {
			this.playerList.selectFirstPlayer();
			return;
		}
		this.topPanel.setComponentZOrder(this.playerConfigTables.get(selectedPlayer), 0);
		this.repaint();
	}
	
	// Methode wird bei jedem Tick gerufen, um die Spielernamen zu aktualisieren
	public void applyPlayerConfigs() {
		PlayerConfig.playerConfigs = this.playerList.getPlayerConfigs();
		for (PlayerConfigTable table : this.playerConfigTables.values()) {
			table.applyPlayerConfig();
		}
	}
	
}