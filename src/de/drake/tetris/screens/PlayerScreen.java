package de.drake.tetris.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import de.drake.tetris.input.InputDevice;
import de.drake.tetris.screens.comp.ComponentFactory;
import de.drake.tetris.screens.comp.ListSpinner;
import de.drake.tetris.screens.comp.OptionTable;
import de.drake.tetris.states.PlayerState;

public class PlayerScreen extends JScrollPane {
	
	private static int optionSize = 15;
	
	private static Color optionColor = Color.white;
	
	private static Color optionBgColor = Color.darkGray;
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public PlayerScreen(final ActionListener listener) {
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			JPanel topPanel = new JPanel();
			topPanel.setBackground(Color.black);
			topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 5, 0, 5);
			c.fill = GridBagConstraints.BOTH;
			contentPanel.add(topPanel, BorderLayout.CENTER);
				
				//In der obersten Zeile Struts einfügen, die eine einheitliche Mindestbreite der Spalten sicherstellen
				c.gridy = 0;
				
				c.gridx = 0;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 1;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 2;
				topPanel.add(Box.createHorizontalStrut(280), c);
				c.gridx = 3;
				topPanel.add(Box.createHorizontalStrut(280), c);
				
				//Spieler 1
				c.gridx = 0;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Spieler 1", Color.green, PlayerScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_p1 = new OptionTable(
						PlayerScreen.optionColor, PlayerScreen.optionBgColor, PlayerScreen.optionSize);
				topPanel.add(options_p1, c);
					
					JSpinner input_p1 = new ListSpinner(InputDevice.allInputdevices.toArray(), 7);
					options_p1.addOption("Eingabegerät", input_p1);
				
				//Spieler 2
				c.gridx = 1;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Spieler 2", Color.red, PlayerScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_p2 = new OptionTable(
						PlayerScreen.optionColor, PlayerScreen.optionBgColor, PlayerScreen.optionSize);
				topPanel.add(options_p2, c);
				
					JSpinner input_p2 = new ListSpinner(InputDevice.allInputdevices.toArray(), 7);
					options_p2.addOption("Eingabegerät", input_p2);
				
				//Spieler 3
				c.gridx = 2;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Spieler 3", Color.blue, PlayerScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_p3 = new OptionTable(
						PlayerScreen.optionColor, PlayerScreen.optionBgColor, PlayerScreen.optionSize);
				topPanel.add(options_p3, c);
				
					JSpinner input_p3 = new ListSpinner(InputDevice.allInputdevices.toArray(), 7);
					options_p3.addOption("Eingabegerät", input_p3);
				
				//Spieler 4
				c.gridx = 3;
				
				c.gridy = 1;
				topPanel.add(ComponentFactory.createLabel(
						"Spieler 4", Color.yellow, PlayerScreen.optionBgColor, 50), c);
				
				c.gridy = 2;
				OptionTable options_p4 = new OptionTable(
						PlayerScreen.optionColor, PlayerScreen.optionBgColor, PlayerScreen.optionSize);
				topPanel.add(options_p4, c);
				
					JSpinner input_p4 = new ListSpinner(InputDevice.allInputdevices.toArray(), 7);
					options_p4.addOption("Eingabegerät", input_p4);
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Color.black);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(PlayerState.start, listener));
				bottomPanel.add(ComponentFactory.createButton(PlayerState.back, listener));
				
	}
	
}