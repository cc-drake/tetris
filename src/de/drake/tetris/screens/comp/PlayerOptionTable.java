package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import de.drake.tetris.config.Player;
import de.drake.tetris.input.InputDevice;

public class PlayerOptionTable extends OptionTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Player player;
	
	private final JTextField name;

	public PlayerOptionTable(final Player player, final Color color, final Color bgcolor, final int size) {
		super(color, bgcolor, size);
		this.player = player;
		
		this.name = new JTextField(player.getName());
		super.addOption("Spielername", name);
	
		JSpinner inputType = new ListSpinner(InputDevice.allInputdevices.toArray(), 7);
		super.addOption("Eingabegerät", inputType);
		
		KeyInputField links = new KeyInputField(inputType);
		super.addOption("Links", links);
	}
	
	public void paintComponent(Graphics g) {
		if (this.name.getText().isEmpty()) {
			this.player.setName("Namenloser Spieler");
		} else {
			this.player.setName(this.name.getText());
		}
		super.paintComponent(g);
	}
	
}
