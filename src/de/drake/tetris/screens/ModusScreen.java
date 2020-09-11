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

import de.drake.tetris.screens.comp.ComponentFactory;
import de.drake.tetris.screens.comp.DateSpinner;
import de.drake.tetris.states.ModeState;

public class ModusScreen extends JScrollPane {
	
	/**
	 * Die Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public ModusScreen(final ActionListener listener) {
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			JPanel topPanel = new JPanel();
			topPanel.setBackground(Color.black);
//			topPanel.setLayout(new GridLayout(1, 1));//TODO
			topPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.ipadx = 0;//TODO
			c.ipady = 0;
			c.insets = new Insets(0, 0, 0, 0);
			contentPanel.add(topPanel, BorderLayout.CENTER);
			
//Links und Rechts Spalten mit Glue einfügen, damit die Einstellungen mittig zentriert dargestellt werden
				c.gridy = 0;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.fill = GridBagConstraints.VERTICAL;
				c.weightx = 1.;
				c.weighty = 0.;
				
				c.gridx = 0;
				topPanel.add(Box.createGlue(), c);
				c.gridx = 5;
				topPanel.add(Box.createGlue(), c);
				
//In der obersten Zeile Struts einfügen, die eine einheitliche Mindestbreite der Spalten sicherstellen
				c.gridy = 0;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.;
				c.weighty = 1.;
				
				c.gridx = 1;
				topPanel.add(Box.createHorizontalStrut(500), c);//TODO
				c.gridx = 2;
				topPanel.add(Box.createHorizontalStrut(500), c);
				c.gridx = 3;
				topPanel.add(Box.createHorizontalStrut(500), c);
				c.gridx = 4;
				topPanel.add(Box.createHorizontalStrut(500), c);
				
//In der letzten Zeile Glue einfügen, damit die Einstellungen mittig zentriert dargestellt werden
				c.gridx = 0;
				c.gridwidth = 6;
				c.gridheight = 1;
				c.fill = GridBagConstraints.VERTICAL;
				c.weightx = 0.;
				c.weighty = 1.;
				
				c.gridy = 5;
				topPanel.add(Box.createGlue(), c);
				
//Beginn des Inhalts
				c.gridwidth = 1;
				c.gridheight = 1;
				c.fill = GridBagConstraints.NONE;
				c.weightx = 0.;
				c.weighty = 0.;
				
//Erste Inhalts-Zeile - Labels
				c.gridy = 1;
				
				c.gridx = 1;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.solitaer, Color.green, 50), c);
				c.gridx = 2;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.race, Color.blue, 50), c);
				c.gridx = 3;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.combat, Color.red, 50), c);
				c.gridx = 4;
				topPanel.add(ComponentFactory.createLabel(
						ModeState.cheese, Color.yellow, 50), c);
				
				
//Zweite Inhalts-Zeile - Timelimit
				c.gridy = 2;
				
				c.gridx = 1;
				DateSpinner timelimit1 = new DateSpinner(
						"Zeitlimit (mm:ss)", Color.white, 20);
				topPanel.add(timelimit1, c);
				
				c.gridx = 2;
				DateSpinner timelimit2 = new DateSpinner(
						"Zeitlimit (mm:ss)", Color.white, 20);
				topPanel.add(timelimit2, c);
				
				c.gridx = 3;
				DateSpinner timelimit3 = new DateSpinner(
						"Zeitlimit (mm:ss)", Color.white, 20);
				topPanel.add(timelimit3, c);
				
				c.gridx = 4;
				DateSpinner timelimit4 = new DateSpinner(
						"Zeitlimit (mm:ss)", Color.white, 20);
				topPanel.add(timelimit4, c);
				
//Dritte Inhaltszeile - Modusspezifische Einstellungen
				
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Color.black);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(bottomPanel, BorderLayout.SOUTH);
			
				bottomPanel.add(ComponentFactory.createButton(ModeState.back, listener));
				
//				Box settingsBox = new Box(BoxLayout.Y_AXIS);
//				settingsBox.setBackground(Color.black);
//				topPanel.add(settingsBox);
//				
//					settingsBox.add(Box.createVerticalGlue());
//				
//					JPanel titlePanel = new JPanel();
//					titlePanel.setBackground(Color.black);
//					titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//					titlePanel.setLayout(new GridLayout(1, 4));
//					settingsBox.add(titlePanel);
//					
//						JLabel solitaerLabel = new JLabel(ModeState.solitaer);
//						solitaerLabel.setHorizontalAlignment(SwingConstants.CENTER);
//						solitaerLabel.setForeground(Color.lightGray);
//						solitaerLabel.setFont(new Font("Serif", Font.BOLD, 50));
//						titlePanel.add(solitaerLabel);
//						
//						JLabel raceLabel = new JLabel(ModeState.race);
//						raceLabel.setHorizontalAlignment(SwingConstants.CENTER);
//						raceLabel.setForeground(Color.lightGray);
//						raceLabel.setFont(new Font("Serif", Font.BOLD, 50));
//						titlePanel.add(raceLabel);
//						
//						JLabel combatLabel = new JLabel(ModeState.combat);
//						combatLabel.setHorizontalAlignment(SwingConstants.CENTER);
//						combatLabel.setForeground(Color.lightGray);
//						combatLabel.setFont(new Font("Serif", Font.BOLD, 50));
//						titlePanel.add(combatLabel);
//						
//						JLabel cheeseLabel = new JLabel(ModeState.cheese);
//						cheeseLabel.setHorizontalAlignment(SwingConstants.CENTER);
//						cheeseLabel.setForeground(Color.lightGray);
//						cheeseLabel.setFont(new Font("Serif", Font.BOLD, 50));
//						titlePanel.add(cheeseLabel);
//						
//					settingsBox.add(Box.createHorizontalStrut(20));
//					
//					JPanel timeLimitPanel = new JPanel();
//					timeLimitPanel.setBackground(Color.black);
//					timeLimitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//					timeLimitPanel.setLayout(new GridLayout(1, 4));
//					settingsBox.add(timeLimitPanel);
//					
//						JPanel timeLimitPanel_solitaer = new JPanel();
//						timeLimitPanel_solitaer.setBackground(Color.black);
//						timeLimitPanel_solitaer.setLayout(new FlowLayout(FlowLayout.CENTER));
//						timeLimitPanel.add(timeLimitPanel_solitaer);
//						
//							JLabel timeLimitLabel_solitaer = new JLabel("Zeitlimit:");
////							timeLimitLabel_solitaer.setHorizontalAlignment(SwingConstants.CENTER);
//							timeLimitLabel_solitaer.setForeground(Color.lightGray);
//							timeLimitLabel_solitaer.setFont(new Font("Serif", Font.PLAIN, 20));
//							timeLimitPanel_solitaer.add(timeLimitLabel_solitaer);
//					
//							JSpinner timeLimit_solitaer = new JSpinner();
//							timeLimit_solitaer.setForeground(Color.lightGray);
//	//						timeLimit_solitaer.setFont(new Font("Serif", Font.BOLD, 50));
//							timeLimitPanel_solitaer.add(timeLimit_solitaer);
//				
//					JButton start = new JButton(ModeState.solitaer);
//					start.setAlignmentX(Component.CENTER_ALIGNMENT);
//					start.addActionListener(this);
//					settingsBox.add(start);
//					
//					JButton start2 = new JButton(ModeState.solitaer);
//					start2.setAlignmentX(Component.CENTER_ALIGNMENT);
//					start2.addActionListener(this);
//					settingsBox.add(start2);
//					
//					JButton start3 = new JButton(ModeState.solitaer);
//					start3.setAlignmentX(Component.CENTER_ALIGNMENT);
//					start3.addActionListener(this);
//					settingsBox.add(start3);
//					
//					settingsBox.add(Box.createVerticalGlue());
				
	}
	
}
