package de.drake.tetris.view.screens.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.drake.tetris.config.PlayerConfig;

class PlayerCellRenderer implements ListCellRenderer<PlayerConfig> {

	@Override
	public Component getListCellRendererComponent(
			JList<? extends PlayerConfig> list, PlayerConfig player, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel result = new JLabel();
		if (player.getName() == "") {
			result.setText("Spieler " + index);
		} else {
			result.setText(player.getName());
		}
		result.setOpaque(true);
		result.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		result.setForeground(Color.black);
		result.setBackground(Color.white);
		if (isSelected) {
			result.setForeground(Color.black);
			result.setBackground(Color.blue);
		}
		return result;
	}

}